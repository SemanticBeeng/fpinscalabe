package org.fp.studies.kleisli

import org.fp.concepts._
import org.fp.resources._
import org.fp.bookmarks._

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

    import scala.util.Try

    class Data {

      case class Continent(name: String, countries: List[Country] = List.empty)
      case class Country(name: String, cities: List[City] = List.empty)
      case class City(name: String, isCapital: Boolean = false, inhabitants: Int = 20)

      private val Washington = City("Washington")
      private val NewYork = City("New York")

      private val NewDehli = City("New Dehli")
      private val Calcutta = City("Calcutta")

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

      var saved = List[City]()
      def save(cities: List[City]): Try[Unit] =
        Try {
          cities.foreach(c => saved = saved.::(c))
        }

      def inhabitants(c: City): Int = c.inhabitants
    }

    s"$keyPoint Examples of using the variations of the $operator_andThen,  " +
      s"either starting with a $KleisliArrow and following with functions of the form A => M[B] " +
      s"or following with adequate $KleisliArrow."

    eg { /** [[Scalaz]] */

      import scalaz.Kleisli._
      import scalaz.std.list._
      val d = new Data

      s" Some operator have aliases: " +
        s" $$operator_>==> is alias for $operator_andThenK " +
        s" $$operator_>=>  is alias for $operator_andThen " +
        s" " +
        s" The same applies to $functionComposition with " +
        s" $$operator_<==< and $operator_composeK " +
        s" $$operator_<=<  and $operator_compose".p

      val allCities = kleisli(d.continents) >==> d.countries >==> d.cities
      val allCities2 = kleisli(d.continents) >=> kleisli(d.countries) >=> kleisli(d.cities)

      allCities("America") must_== allCities2("America")
    }

    eg { /** [[Cats]] */

      success
    }
  }
}
