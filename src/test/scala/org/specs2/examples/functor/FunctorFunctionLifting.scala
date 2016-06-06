package org.specs2.examples.functor

import org.fp.concepts._
import org.specs2.ugbase.UserGuidePage

/**
  *
  */
object FunctorFunctionLifting extends UserGuidePage {

  def is = "Lift functions in functors".title ^ s2"""

  $functor function lifting makes sense for single argument functions: ${snippet{

    sealed trait Amount[A]
    case class One[A](a: A) extends Amount[A]
    case class Couple[A](a: A, b: A) extends Amount[A]
    case class Few[A](a: A, b: A, c: A) extends Amount[A]

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

}}

"""
}
