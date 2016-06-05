package org.specs2.examples.functor

//import org.specs2._
import org.specs2.common.SourceType.ScalazSpec
import org.specs2.ugbase.UserGuidePage

/**
  *
  */
object FunctorsWithCustomMap extends UserGuidePage {

  def is = "Creating custom functors".title ^ s2"""

  [[Functor]] mapping (of a function) preserves the type/shape of the [[Functor]]: ${snippet{

  /**
   *
   */
  class ScalazSpecification extends org.specs2.mutable.Spec
                            with org.specs2.specification.dsl.mutable.TextDsl with ScalazSpec {

    // 8<--
    import scalaz.Functor


    // 8<--
    sealed trait Amount[A]
    case class One[A](a: A) extends Amount[A]
    case class Couple[A](a: A, b: A) extends Amount[A]
    case class Few[A](a: A, b: A, c: A) extends Amount[A]

    implicit val functor: Functor[Amount] =
      new Functor[Amount] {
        def map[A, B](fa: Amount[A])(f: A => B): Amount[B] =
          fa match {
            case One(a) => One(f(a))
            case Couple(a, b) => Couple(f(a), f(b))
            case Few(a, b, c) => Few(f(a), f(b), f(c))
          }
      }

    /**
      * Source
      * //https://hyp.is/hI15eiZ4EeaiWYOVMxLWcA/archive.is/O43Km
      */
    "Scalaz examples for custom [[Functor]]s".p

    eg { Functor[Amount].map(One(6)) { x: Int => x * 7 } must_== One(42) }
//    eq {
//      import org.specs2.matcher.{MustExpectations1=>_,ShouldExpectations=>_, _}
//      import org.specs2.matcher.{_=>_}
//      (One(6): Amount[Int]).map { x: Int => x * 7 } /*must_== One(42)*/
//    }
  }

}}

"""
}
