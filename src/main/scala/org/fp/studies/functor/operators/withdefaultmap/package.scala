package org.fp.studies.functor.operators

import org.fp.concepts._
import org.fp.resources._
import org.fp.bookmarks._
//
import org.specs2.specification.dsl.mutable.{AutoExamples, TextDsl}


/**
  *
  * [[operatorMap]]
  *
  */
package object withdefaultmap {

  /**
    * @see [[Scalaz]]
    */
  object ScalazSpec extends org.specs2.mutable.Spec with TextDsl with AutoExamples {

    s"$keyPoint Examples for $functor-s like Option and List ".p
    s"$bookmarks: $ann_functorsOptionAndList"
    eg {
      import scalaz.{Functor, std}

      val len: String => Int = _.length

      import std.option._
      Functor[Option].map(Some("adsf"))(len)           must_== Some(4)
      Functor[Option].map(None)(len)                   must_== None

      import std.list._
      Functor[List]  .map(List("qwer", "adsfg"))(len)  must_== List(4,5)
      Functor[List]  .map(List(1, 2, 3))(_ * 2)        must_== List(2, 4, 6)
    }
  }

  /**
    * @see [[Cats]]
    * @see [[ann_FunctionSyntax]]
    */
  object CatsSpec extends org.specs2.mutable.Spec with TextDsl with AutoExamples {

    s"$keyPoint Examples for $functor-s like Option and List ".p
    s"$bookmarks: $ann_functorsOptionAndList"
    eg {
      import cats.{Functor, std}
      val len: String => Int = _.length

      import std.option._
      Functor[Option].map(Some("adsf"))(len)           must_== Some(4)
      Functor[Option].map(None)(len)                   must_== None

      import std.list._
      Functor[List]  .map(List("qwer", "adsfg"))(len)  must_== List(4,5)
      Functor[List]  .map(List(1, 2, 3))(_ * 2)        must_== List(2, 4, 6)
    }
  }
}
