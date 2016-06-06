package org.specs2.functor

import org.specs2.common.SourceType.{CatsSpec, ScalazSpec}
import org.specs2.functor.withcustommap.AmountExample
import org.specs2.functor.withcustommap.CatsSpec._
import org.specs2.functor.withcustommap.ScalazSpec._

/**
  *
  */
package object functionlifting {

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

    val timesTwo = (x: Int) => x * 2
    val amountTimesTwo = Functor[Amount].lift(timesTwo)

    amountTimesTwo(Few(1,2,3)) must_== Few(2,4,6)

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

    val timesTwo = (x: Int) => x * 2
    val amountTimesTwo = Functor[Amount].lift(timesTwo)

    amountTimesTwo(Few(1,2,3)) must_== Few(2,4,6)

  }
}
