package org.fp.studies.applicative.operators

import org.fp.concepts._
import org.fp.resources._
import org.fp.bookmarks._

import scala.language.higherKinds

//
import org.specs2.specification.dsl.mutable.AutoExamples

//

/**
  *
  */
package object custom {

  import org.fp.studies.functor.operators.withcustommap.AmountExample

  /**
    * @see [[Scalaz]]
    */
  object AmountExample_ApplicativeScalaz extends AmountExample {

    import scalaz.Applicative

    implicit val applicative: Applicative[Amount] = new Applicative[Amount] {

      def point[A](a: => A): Amount[A] = One(a)

      def ap[A, B](fa: => Amount[A])(f: => Amount[A => B]): Amount[B] =
        fa match {
          case One(a) =>
            f match {
              case One(g) => One(g(a))
              case Couple(g, _) => One(g(a))
              case Few(g, _, _) => One(g(a))
            }
          case Couple(a, b) =>
            f match {
              case One(g) => Couple(g(a), g(b))
              case Couple(g, _) => Couple(g(a), g(b))
              case Few(g, _, _) => Couple(g(a), g(b))
            }
          case Few(a, b, c) =>
            f match {
              case One(g) => Few(g(a), g(b), g(c))
              case Couple(g, _) => Few(g(a), g(b), g(c))
              case Few(g, h, i) => Few(g(a), h(b), i(c))
            }
        }
    }
  }

  /**
    * @see [[Cats]]
    */
  object AmountExample_ApplicativeCats extends AmountExample {

    import cats.Applicative

    implicit val applicative: Applicative[Amount] = new Applicative[Amount] {

      def pure[A](a: A): Amount[A] = One(a)

      def ap[A, B](f: Amount[A => B])(fa: Amount[A]): Amount[B] =

        fa match {
          case One(a) =>
            f match {
              case One(g) => One(g(a))
              case Couple(g, _) => One(g(a))
              case Few(g, _, _) => One(g(a))
            }
          case Couple(a, b) =>
            f match {
              case One(g) => Couple(g(a), g(b))
              case Couple(g, _) => Couple(g(a), g(b))
              case Few(g, _, _) => Couple(g(a), g(b))
            }
          case Few(a, b, c) =>
            f match {
              case One(g) => Few(g(a), g(b), g(c))
              case Couple(g, _) => Few(g(a), g(b), g(c))
              case Few(g, h, i) => Few(g(a), h(b), i(c))
            }
        }
    }
  }

  object Spec extends org.specs2.mutable.Spec with AutoExamples {

    s"$keyPoint The $applicativeFunctor $operatorApply can have a few equivalent forms:"
    s"$bookmarks $ann_ApplyStyle"
    eg {
      /** in [[Scalaz]] */

      import scalaz.syntax.applicative._
      import AmountExample_ApplicativeScalaz._

      ^(One(6): Amount[Int], One(7)) { _ * _ } must_== One(42)
      ((One(6): Amount[Int]) |@| One(7)) { _ * _ } must_== One(42)
    }

    eg { /** in [[Cats]] */
      import cats.syntax.applicative._
      import AmountExample_ApplicativeCats._

      //@todo
      success
    }

    s"$keyPoint The $applicativeFunctor $operatorApply applies the function from inside the second $functor:"
    s"$bookmarks $ann_ApplicativeExtractsFunction"
    eg {
      /** in [[Scalaz]] */

      import scalaz.syntax.applicative._
      import AmountExample_ApplicativeScalaz._

      (One(21): Amount[Int]) <*> One({ x: Int => x * 2 }) must_== One(42)

      /**
        * @doesnotcompile
        *
        * One({ x: Int => x * 2 }) <*> (One(21): Amount[Int]) must_== One(42)
        */
    }

    eg { /** in [[Cats]] */
      import cats.Applicative
      import cats.syntax.applicative._
      import AmountExample_ApplicativeCats._

      //@todo
      success
    }

    s"$keyPoint Applying $applicativeFunctor $operatorLHS and $operatorRHS to extract a projection:"
    eg {
      /** in [[Scalaz]] */

      import scalaz.syntax.applicative._
      import AmountExample_ApplicativeScalaz._

      (One(6): Amount[Int]) <* One(7) must_== One(6)

      (One(6): Amount[Int]) *> One(7) must_== One(7)
    }

    eg { /** in [[Cats]] */
      import cats.syntax.applicative._
      import AmountExample_ApplicativeCats._

      //@todo
      success
    }

    /**
      * [[ann_applicativeLaws]]
      * @todo
      */
    eg {
      import scalaz.Applicative
      import scalaz.std.option._
      import scalaz.syntax.applicative._

      //@todo
      success
    }

    s"$keyPoint $applicativeFunctor enable $operatorSequence to transform lists of $functor-s:"
    eg {
      /** in [[Scalaz]] */

      import scalaz.Applicative
      import scalaz.syntax.applicative._
      import AmountExample_ApplicativeScalaz._

      //Applicative[Amount].sequence(List(One(2), One(3), One(7))) must_== One(List(2,3,7))
      //List(One(2), One(3), One(7)).sequence must_== One(List(2,3,7))
      def sequenceA[F[_]: Applicative, A](list: List[F[A]]): F[List[A]] = list match {
        case Nil     => (Nil: List[A]).point[F]
        case x :: xs => (x |@| sequenceA(xs)) {_ :: _}
      }
      sequenceA[Amount, Int](List(One(2), One(3), One(7))) must_== One(List(2,3,7))
    }

    eg { /** in [[Cats]] */
      import cats.Applicative
      import cats.syntax.applicative._
      import AmountExample_ApplicativeCats._

      //@todo
      success
    }

  }

}
