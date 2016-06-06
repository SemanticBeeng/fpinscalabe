package org.specs2.functor

import org.specs2.common.SourceType.{CatsSpecific, ScalazSpecific}


/**
  *
  */
package object withcustommap {

  /**
    * Source https://hyp.is/GnoRUCvdEea3T6skHMRxAw/archive.is/O43Km
    */
  trait AmountExample {

    sealed trait Amount[A]
    case class One[A](a: A) extends Amount[A]
    case class Couple[A](a: A, b: A) extends Amount[A]
    case class Few[A](a: A, b: A, c: A) extends Amount[A]
  }

  trait AmountExample_FunctorScalaz extends AmountExample with ScalazSpecific {

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

  trait AmountExample_FunctorCats extends AmountExample with CatsSpecific {

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
    *
    */
  object ScalazSpec extends org.specs2.mutable.Specification with AmountExample_FunctorScalaz with ScalazSpecific {

    import scalaz.Functor

    eg { Functor[Amount].map(One(6)) { x: Int => x * 7 } must_== One(42) }

//    import org.specs2.matcher.{ShouldExpectations =>_,_}
//    import org.specs2.matcher.{MustExpectations1 =>_,_}
//    import org.specs2.matcher.{_ =>_}
//    eq { (One(6): Amount[Int]).map { x: Int => x * 7 } must_== One(42)}

  }

  /**
    *
    */
  object CatsSpec extends org.specs2.mutable.Specification with AmountExample_FunctorCats with CatsSpecific {

    import cats.Functor

    eg { Functor[Amount].map(One(6)) { x: Int => x * 7 } must_== One(42) }
    //todo eq { (One(6): Amount[Int]).map { x: Int => x * 7 } must_== One(42)}

  }

}
