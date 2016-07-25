package org.fp.studies.kleisli

import org.fp.concepts._
import org.fp.resources._
import org.fp.bookmarks._
import org.fp.studies.kleisli.composition.Spec1.Catnip

//
import org.specs2.specification.dsl.mutable.{TextDsl, AutoExamples}

/**
  *
  * @see [[functorComposition]]
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
      s"When we were learning about the $monad laws, we said that the <=< function is just like $functionComposition, " +
      s"only instead of working for normal functions like a -> b, it works for $monadicFunction-s like a -> m b.".p

    eg { /** in [[Scalaz]] */

      import scalaz.Kleisli._

      import scalaz.std.option._
      import Catnip._

      val f = kleisli { (x: Int) => (x + 1).some }
      val g = kleisli { (x: Int) => (x * 100).some }

      s"There’s a special wrapper for a function of type A => F[B] called $KleisliArrow:".p

      s"We can then compose the functions using 'compose', which runs the right-hand side first:".p
      (4.some flatMap (f compose g).run) must_== Some(401)
      (4.some flatMap (f <==<    g).run) must_== Some(401)

      s"There’s also 'andThen', which runs the left-hand side first:".p
      (4.some flatMap (f andThen g).run) must_== Some(500)
      (4.some flatMap (f >=>     g).run) must_== Some(500)
      //@todo ((f >=>     g).run) =<< 4.some)  must_== Some(500)

      s"Both 'compose' and 'andThen' work like $functionComposition but note that they retain the monadic context.".p

      s"$KleisliArrow also has some interesting methods like $operator_lift, which allows you to lift a $monadicFunction " +
        s"into another $applicativeFunctor.".p
      import scalaz.std.list._

      val l = f.lift[List]
      (List(1, 2, 3) flatMap l.run) must_== List(Some(2), Some(3), Some(4))
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
      (List(1, 2, 3) >>= l.run) must_== List(Some(2), Some(3), Some(4))
    }
  }

  /**
    *
    */
  object Spec2 extends org.specs2.mutable.Spec with AutoExamples with TextDsl {

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

      val allCities1 = kleisli(continents) >==> countries >==> cities
      val allCities2 = kleisli(continents) >=> kleisli(countries) >=> kleisli(cities)

      allCities1("America") must_== allCities2("America")
      allCities1("Ameri") must_== List(Washington, NewYork)
      allCities1("Asi") must_== List(NewDehli, Calcutta)

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

    object SomeFunctions {
      // Some methods that take simple types and return higher-kinded types
      def str(x: Int): Option[String] = Some(x.toString)
      def toInt(x: String): Option[Int] = Some(x.toInt)
      def double(x: Int): Option[Double] = Some(x * 2)

    }

    s"$keyPoint $KleisliArrow is $functionComposition for $monad-s"

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
      val funky = kleisli(str _) >==> toInt >==> double

      oldSchool(1) must_== Some(2.0)
      funky(1)     must_== Some(2.0)
    }

    eg { /** [[Cats]] */

      //@todo
      success
    }

  }
}


