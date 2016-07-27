package org.fp.studies.kleisli

import org.fp.concepts._
import org.fp.resources._
import org.fp.bookmarks._

//
import org.specs2.specification.dsl.mutable.{TextDsl, AutoExamples}

/**
  *
  * @see [[functionComposition]]
  */
package object composition {

  /**
    *
    */
  object Spec1 extends org.specs2.mutable.Spec with AutoExamples with TextDsl {

    object Catnip {

      implicit class IdOp[A](val a: A) extends AnyVal {
        def some: Option[A] = Some(a)
      }
      def none[A]: Option[A] = None
    }

    s"$keyPoint Composing monadic functions" +
      s"LYAHFGG:" +
      s"When we were learning about the $monadLaws, we said that the $$operator_<=< function is just like $functionComposition, " +
      s"only instead of working for normal functions like a -> b, it works for $monadicFunction-s like a -> m b.".p

    eg { /** in [[Scalaz]] */

      import scalaz.Kleisli._

      import scalaz.std.option._
      import Catnip._

      val f = kleisli { (x: Int) => (x +   1).some }
      val g = kleisli { (x: Int) => (x * 100).some }

      s"There’s a special wrapper for a function of type A => F[B] called $KleisliArrow:".p

      s"We can then compose the functions using $operator_compose, which runs the right-hand side first:".p
      (4.some flatMap (f compose g).run) must_== Some(401)
      (4.some flatMap (f <==<    g).run) must_== Some(401)

      s"There’s also $operator_andThen, which runs the left-hand side first:".p
      (4.some flatMap (f andThen g).run)       must_== Some(500)
      (4.some flatMap (f >=>     g).run)       must_== Some(500)
                      (f >=>     g) =<< 4.some must_== Some(500)

      s"Both $operator_compose and $operator_andThen work like $functionComposition but note that they retain the $monadicContext.".p

      s"$KleisliArrow also has some interesting methods like $operator_lift, which allows you to lift a $monadicFunction " +
        s"into another $applicativeFunctor.".p
      import scalaz.std.list._
      //import scalaz.std.function._

      val l = f.lift[List]

      (List(1, 2, 3) flatMap l.run) must_== List(Some(2), Some(3), Some(4))
      //@todo (l =<< List(1, 2, 3))         must_== List(Some(2), Some(3), Some(4))
    }

    s"$bookmarks $ann_Kleisli"
    eg { /** in [[Cats]] */

      import cats.data.Kleisli
      import cats.syntax.flatMap._

      import cats.std.option._
      import Catnip._

      val f = Kleisli { (x: Int) => (x + 1).some }
      val g = Kleisli { (x: Int) => (x * 100).some }

      s"There’s a special wrapper for a function of type A => F[B] called $Kleisli:".p

      s"We can then compose the functions using 'compose', which runs the right-hand side first:".p
      (4.some >>= (f compose g).run) must_== Some(401)

      s"There’s also 'andThen', which runs the left-hand side first:".p
      (4.some >>= (f andThen g).run) must_== Some(500)

      s"Both 'compose' and 'andThen' work like $functionComposition but note that they retain the monadic context.".p

      s"$Kleisli also has some interesting methods like $operator_lift, which allows you to lift a $monadicFunction " +
        s"into another $applicativeFunctor.".p

      import cats.std.list._

      val l = f.lift[List]

      (List(1, 2, 3) flatMap l.run) must_== List(Some(2), Some(3), Some(4))
      (List(1, 2, 3) >>=     l.run) must_== List(Some(2), Some(3), Some(4))
    }
  }

  /**
    *
    */
  object Spec2 extends org.specs2.mutable.Spec with AutoExamples with TextDsl {

    s"$keyPoint $KleisliArrow is $functionComposition for $monad-s"

    object SomeFunctions {

      s"Some functions that take simple types and return higher-kinded types".p

      def str(x: Int): Option[String] = Some(x.toString)
      def toInt(x: String): Option[Int] = Some(x.toInt)
      def double(x: Int): Option[Double] = Some(x * 2)

      s"".p
      def opt(x: Int): Option[String] = Some(x.toString)
      def list(x: String) = List(x)
      def optToList[T](o: Option[T]) = o.toList
    }
    s"$bookmarks $ann_KleisliArrow2".p
    eg {
      /** [[Scalaz]] */

      import scalaz.Kleisli._
      import scalaz.std.option._

      import SomeFunctions._

      s"Lets compose those functions Ye Olde Way".p
      def oldSchool(i: Int) =
        for (x <- str(i);
             y <- toInt(x);
             z <- double(y))
          yield z

      s"And compose those functions $KleisliArrow way".p
      val funky = kleisli(str) >==> toInt >==> double

      oldSchool(1) must_== Some(2.0)
      funky(1)     must_== Some(2.0)
    }

    eg { /** [[Cats]] */

      //@todo
      success
    }

    eg {
      /** [[Scalaz]] */

      import scalaz.Kleisli._

      import SomeFunctions._

      s"now we can $operator_compose opt and list using optToList".p
      //@todo val multi = (kleisli(opt _) compose optToList) >=> list
      success
    }

    eg { /** [[Cats]] */

      //@todo
      success
    }

    object World {

      case class Continent(name: String, countries: List[Country] = List.empty)

      case class Country(name: String, cities: List[City] = List.empty)

      case class City(name: String, isCapital: Boolean = false, inhabitants: Int = 20)

      val Washington = City("Washington", isCapital = true, inhabitants = 9000000)
      val NewYork = City("New York", isCapital = false, inhabitants = 11000000)

      val NewDehli = City("New Dehli", isCapital = false, inhabitants = 20000000)
      val Calcutta = City("Calcutta", isCapital = false, inhabitants = 30000000)

      val data: List[Continent] = List(
        Continent("Europe"),
        Continent("America",
          List(
            Country("USA", List(Washington, NewYork))
          )
        ),
        Continent("Asia",
          List(Country("India", List(NewDehli, Calcutta))
          )
        )
      )

      def continents(name: String): List[Continent] =
        data.filter(k => k.name.contains(name))

      def countries(continent: Continent): List[Country] = continent.countries

      def cities(country: Country): List[City] = country.cities

      import scala.util.Try

      def save(cities: List[City]): Try[List[City]] =
        Try {
          var saved = List[City]()
          cities.foreach(c => saved = saved.::(c))
          saved
        }

      def inhabitants(c: City): Int = c.inhabitants
    }

    s"$keyPoint Examples of using the variations of the $operator_andThen,  " +
      s"either starting with a $KleisliArrow and following with functions of the form A => M[B] " +
      s"or following with adequate $KleisliArrow."

    s"$bookmarks $ann_KleisliArrow1".p
    eg {
      /** [[Scalaz]] */

      import scalaz.Kleisli._
      import scalaz.std.list._

      import World._

      s" Some operator have aliases: " +
        s" $$operator_>==> is alias for $operator_andThenK " +
        s" $$operator_>=>  is alias for $operator_andThen " +
        s" " +
        s" The same applies to $functionComposition with " +
        s" $$operator_<==< and $operator_composeK " +
        s" $$operator_<=<  and $operator_compose".p

      val allCities1 = kleisli(continents) >==>         countries  >==>         cities
      val allCities2 = kleisli(continents) >=>  kleisli(countries) >=>  kleisli(cities)
      val allCities3 = kleisli(cities)     <==<         countries <==<          continents
      val allCities4 = kleisli(cities)     <=<  kleisli(countries) <=<  kleisli(continents)

      allCities1("America") must_== allCities2("America")
      allCities1("Ameri")   must_== List(Washington, NewYork)
      allCities1("Asi")     must_== List(NewDehli, Calcutta)

      allCities2("America") must_== allCities3("America")
      allCities2("America") must_== allCities4("America")

      s" $$operator=<< takes a $monadicStructure compatible with the $KleisliFunction" +
        s"as its parameter and $operator_flatMap-s the function over this parameter.".p
      (allCities1 =<< List("Amer", "Asi")) must_== List(Washington, NewYork, NewDehli, Calcutta)

      s"With $operator_map we can map a function B => C over a $KleisliFunction of the structure A => M[B]".p
      val cityInhabitants = allCities1 map inhabitants
      cityInhabitants =<< List("Amer", "Asi") must_== List(9000000, 11000000, 20000000, 30000000)

      s"with $operator_mapK you can map a $KleisliFunction into another $monadicStructure, e.g. provide a function M[A] => N[B]".p
      val getAndSave = allCities1 mapK save
      //import scalaz.Success
      //@todo clarify getAndSave("America") must_== Success(allCities1("America").reverse)

      s"$operator_local can be used to prepend a $KleisliFunction of the form A => M[B] with a function of the form AA => A, " +
        s"resulting in a $KleisliFunction of the form AA => M[B]".p
      def index(i: Int) = data(i).name
      val allCitiesByIndex = allCities1 local index

      allCitiesByIndex(1) must_== List(Washington, NewYork)
    }

    eg {
      /** [[Cats]] */

      //@todo
      success
    }

    object Scalaz_FilesOp {

      import java.io.File
      import scala.io.Source
      import scalaz.Kleisli
      import scalaz.Kleisli._
      import scalaz.std.list._

      val files: String => List[File] = { (dir) =>
        new File(dir).listFiles().toList
      }

      def lengths: File => List[Int] = { (f) =>
          if (f.isDirectory)
            List(0)
          else
            Source.fromFile(f).getLines().toList.map(l => l.length())
        }

      /**
        * @todo better understand the point of [[ann_KleisliArrow4]]
        */
      s"$bookmarks $ann_KleisliArrow4, $ann_KleisliArrow5".p

      def lengthsK: Kleisli[List, File, Int] = kleisli((f: File) => {
        if (f.isDirectory)
          lengthsK =<< f.listFiles().toList
        else
          Source.fromFile(f).getLines().toList.map(l => l.length())
      })

        val lineLengths        =  kleisli(files) >==> lengths
        val etcLineLengths     = (kleisli(files) >==> lengths) <==< ((dir: String) => List("/etc/" + dir))
        val networkLineLengths = (kleisli(files) >==> lengths)  =<< List("network")
      }

      s"$keyPoint ..."
      s"$bookmarks $ann_KleisliArrow3".p
      eg {
        /** [[Scalaz]] */

        import scalaz.Kleisli._
        import scalaz.std.list._
        import Scalaz_FilesOp._

        import scala.util.Try

        /* @todo solve NullPointerException
        val dirs = kleisli(files) =<< List("/etc/network")
        println(dirs)

        val lengthsC = Try {
          networkLineLengths
        }
        println(lengthsC)
        interfacesLineLengths(0) must beGreaterThan 10
        */
        success
      }

      eg {
        /** [[Cats]] */

        //@todo
        success
      }
    }

  /**
    *
    */
  object Spec3 extends org.specs2.mutable.Spec with AutoExamples with TextDsl {

      object SomeFunctions {
        def a = (value: Int) => value * 2

        def b = (value: Int) => value + 1

        def ab = a.andThen(b)
      }

      s"$keyPoint $KleisliArrow composition is to $operator_flatMap as function composition is to $operator_map."

      s"$bookmarks $ann_KleisliArrow6".p
      eg {
        /** [[Scalaz]] */

        import scalaz.std.option._
        import scalaz.syntax.std.option._

        import SomeFunctions._

        s"Using $operator_map to achieve $functionComposition for regular functions.".p
        1.some.map(a).map(b)  must_== 3.some
        1.some.map(ab)        must_== 3.some

        import scalaz.Kleisli
        import scalaz.Kleisli._

        val ak = Kleisli( (value: Int) => a(value).some )
        val bk = Kleisli( (value: Int) => b(value).some )
        val abk = ak.andThen(bk)

        s"Using $operator_map and $operator_flatMap to achieve $functionComposition for $KleisliArrow-s.".p
        1.some.flatMap(ak.run).flatMap(bk.run)  must_== 3.some
        1.some.map(abk).get                     must_== 3.some
      }

      eg {
        /** [[Cats]] */

        import cats.std.option._
        import cats.syntax.option._

        import SomeFunctions._

        s"Using $operator_map to achieve $functionComposition for regular functions.".p
        1.some.map(a).map(b)  must_== 3.some
        1.some.map(ab)        must_== 3.some

        import cats.data.Kleisli

        val ak = Kleisli( (value: Int) => a(value).some )
        val bk = Kleisli( (value: Int) => b(value).some )
        val abk = ak.andThen(bk)

        s"Using $operator_map and $operator_flatMap to achieve $functionComposition for $KleisliArrow-s.".p
        1.some.flatMap(ak.run).flatMap(bk.run)  must_== 3.some
        1.some.map(abk.run).get                 must_== 3.some
      }
  }

  /**
    *
    */
  object Spec4 extends org.specs2.mutable.Spec with AutoExamples with TextDsl {

    case class Make(id: Int, name: String)
    case class Part(id: Int, name: String)

    object SomeFunctions1 {

      val make: (Int) => Make = (_) => Make(1, "Suzuki")

      val parts: Make => List[Part] = {
        case Make(1, _) => List(Part(1, "Gear Box"), Part(2, "Clutch cable"))
      }
    }

    s"$keyPoint Today I’ll be exploring a few different ways in which you can compose programs. " +
      s"The examples that follow all deal with Vehicles - more specifically makes and parts:."

    s"$bookmarks $ann_FunctionComposition2".p
    eg {
      /** [[Scala]] */

      import SomeFunctions1._

      s"So we have a function Int =>  Make and then a function : Make => List[Part]. " +
        s"From set theory we know this implies we must have a function from Int => List[Part]. " +
        s"This is nothing more than simple function composition:".p

      val f = parts compose make
      f(1) must_== List(Part(1, "Gear Box"), Part(2, "Clutch cable"))

      s"Alternatively you can use $operator_andThen which works like $operator_compose, but with the arguments flipped:".p
      val g = make andThen parts
      g(1) must_== List(Part(1, "Gear Box"), Part(2, "Clutch cable"))
    }

    eg {
      /** [[Scalaz]] */

      import scalaz.std.option._


      //@todo
      success
    }

    eg {
      /** [[Cats]] */

      import cats.std.option._
      import cats.syntax.option._

      //@todo
      success
    }
  }
}


