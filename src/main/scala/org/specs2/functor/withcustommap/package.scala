package org.specs2.functor

import org.specs2.common.SourceType.{CatsSpec, ScalazSpec}


/**
  *
  */
package object withcustommap {

  /**
    *
    */
  //package object common {

    /**
      *
      */
    trait AmountExample {

      sealed trait Amount[A]
      case class One[A](a: A) extends Amount[A]
      case class Couple[A](a: A, b: A) extends Amount[A]
      case class Few[A](a: A, b: A, c: A) extends Amount[A]
    }

  //}

  //import org.specs2.functor.withcustommap.common.AmountExample

  object ScalazSpec extends org.specs2.mutable.Specification with AmountExample with ScalazSpec {

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

    eg { Functor[Amount].map(One(6)) { x: Int => x * 7 } must_== One(42) }
    //todo eq { (One(6): Amount[Int]).map { x: Int => x * 7 } must_== One(42)}

  }

  //import org.specs2.functor.withcustommap.common.AmountExample

  object CatsSpec extends org.specs2.mutable.Specification with AmountExample with CatsSpec {

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

    eg { Functor[Amount].map(One(6)) { x: Int => x * 7 } must_== One(42) }
    //todo eq { (One(6): Amount[Int]).map { x: Int => x * 7 } must_== One(42)}

  }

}
