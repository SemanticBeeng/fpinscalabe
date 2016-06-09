package org.fp.studies.functor.operators

import org.fp.concepts._
import org.fp.resources._
import org.fp.bookmarks._
//
import org.specs2.specification.dsl.mutable.AutoExamples

/**
  *
  * @see [[operatorMap]]
  *
  */
package object withcustommap {

  /**
    * @see [[ann_FunctionComposition]]
    */
  trait AmountExample {

    sealed trait Amount[A]
    case class One[A](a: A) extends Amount[A]
    case class Couple[A](a: A, b: A) extends Amount[A]
    case class Few[A](a: A, b: A, c: A) extends Amount[A]
  }

  /**
    * @see [[Scalaz]]
    */
  trait AmountExample_FunctorScalaz extends AmountExample {

    import scalaz.Functor

    implicit val functor: Functor[Amount] =
      new Functor[Amount] {
        def map[A, B](fa: Amount[A])(f: A => B): Amount[B] =
          fa match {
            case One(a) => One(f(a))
            case Couple(a, b) => Couple(f(a), f(b))
            case Few(a, b, c) => Few(f(a), f(b), f(c))
          }
      }

    //todo
//    private val one: Functor[Amount[Int]] = One(6)
//    private val intToInt: (Int) => Int = { x: Int => x * 7 }
//    one map intToInt
  }

  /**
    * @see [[Cats]]
    */
  trait AmountExample_FunctorCats extends AmountExample {

    import cats.Functor

    implicit val functor: Functor[Amount] =
      new Functor[Amount] {
        def map[A, B](fa: Amount[A])(f: A => B): Amount[B] =
          fa match {
            case One(a) => One(f(a))
            case Couple(a, b) => Couple(f(a), f(b))
            case Few(a, b, c) => Few(f(a), f(b), f(c))
          }
      }
  }

  /**
    * @see [[Scalaz]]
    */
  object ScalazSpec extends org.specs2.mutable.Spec with AutoExamples with AmountExample_FunctorScalaz {

    s"$keyPoint Explicit conversion to $functor applies here:"
    eg {
      import scalaz.Functor

      Functor[Amount].map(One(6)) { x: Int => x * 7 } must_== One(42)
    }

    s"$keyPoint Implicit conversion to $functor applies here:"
    eq {
      import scalaz.syntax.functor._

      (One(6): Amount[Int]) map { x: Int => x * 7 } must_== One(42)
    }

  }

  /**
    *  @see [[Cats]]
    */
  object CatsSpec extends org.specs2.mutable.Spec with AutoExamples with AmountExample_FunctorCats {

    s"$keyPoint Explicit conversion to $functor applies here:"
    eg {
      import cats.Functor

      Functor[Amount].map(One(6)) { x: Int => x * 7 } must_== One(42)
    }

    s"$keyPoint Implicit conversion to $functor applies here:"
    eq {
      import cats.syntax.functor._

      (One(6): Amount[Int]) map { x: Int => x * 7 } must_== One(42)
    }
  }
}
