package org.fp.studies.applicative.operators

import org.fp.concepts._
import org.fp.resources._
import org.fp.bookmarks._
import org.specs2.specification.dsl.mutable.AutoExamples

//

/**
  *
  */
package object withcustommap {

  import org.fp.studies.functor.operators.withcustommap.AmountExample

  /**
    * @see [[scalaz.Scalaz]]
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
    * @see [[scalaz.Scalaz]]
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

    s"$keyPoint Implicit conversion to $applicative applies here:"
    eg {  /** in [[Scalaz]] */

      import scalaz.syntax.applicative._
      import AmountExample_ApplicativeScalaz._

      (One(21): Amount[Int]) <*> One({ x: Int => x * 2 }) must_== One(42)

      (One(6): Amount[Int]) <* One(7) must_== One(6)

      (One(6): Amount[Int]) *> One(7) must_== One(7)
    }

//    eg { /** in [[Cats]] */
//      import cats.syntax.applicative._
//      import AmountExample_ApplicativeCats._
//
//      (One(21): Amount[Int]) <*> One({ x: Int => x * 2 }) must_== One(42)
//
//      (One(6): Amount[Int]) <* One(7) must_== One(6)
//
//      (One(6): Amount[Int]) *> One(7) must_== One(7)
//    }

  }

}
