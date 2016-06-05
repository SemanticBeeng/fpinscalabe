package org.specs2.examples.util

/**
  *
  */
trait AmountExample {

  sealed trait Amount[A]
  case class One[A](a: A) extends Amount[A]
  case class Couple[A](a: A, b: A) extends Amount[A]
  case class Few[A](a: A, b: A, c: A) extends Amount[A]

}

trait AmountExampleScalazSpec extends org.specs2.mutable.Specification with AmountExample {

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


}