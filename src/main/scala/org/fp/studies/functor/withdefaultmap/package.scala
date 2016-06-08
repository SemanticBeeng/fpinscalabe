package org.fp.studies.functor

import org.specs2.common.SourceType.{ScalazSpecific, CatsSpecific}
import org.specs2.specification.dsl.mutable.{AutoExamples, TextDsl}
import org.fp.bookmarks._

/**
  *
  */
package object withdefaultmap {

  /**
    *
    */
  object ScalazSpec extends org.specs2.mutable.Spec with TextDsl with AutoExamples with ScalazSpecific {

    // 8<--
    import scalaz.{Functor, std}
    import std.list._
    import std.option._


    val len: String => Int = _.length
    // 8<--

    /**
      * Source https://github.com/scalaz/scalaz/blob/series/7.3.x/example/src/main/scala/scalaz/example/FunctorUsage.scala#L35-L41
      */
    "Scalaz examples for [[Functor]]s like Option and List ".p
    eg { Functor[Option].map(Some("adsf"))(len)           must_== Some(4)   }
    eg { Functor[Option].map(None)(len)                   must_== None      }

    eg { Functor[List]  .map(List("qwer", "adsfg"))(len)  must_== List(4,5) }
    eg { Functor[List].  map(List(1, 2, 3))(_ * 2)        must_== List(2, 4, 6)}

  }

  /**
    * @see [[ann_FunctionSyntax]]
    */
  object CatsSpec extends org.specs2.mutable.Spec with TextDsl with AutoExamples with CatsSpecific {

    // 8<--
    import cats.Functor
    import cats.std.list._
    import cats.std.option._
    val len: String => Int = _.length
    // 8<--

    /**
      * Source
      */
    "Cats examples for [[Functor]]s like Option and List ".p
    eg { Functor[Option].map(Some("adsf"))(len)           must_== Some(4)   }
    eg { Functor[Option].map(None)(len)                   must_== None      }

    eg { Functor[List]  .map(List("qwer", "adsfg"))(len)  must_== List(4,5) }
    eg { Functor[List].  map(List(1, 2, 3))(_ * 2)        must_== List(2, 4, 6)}
  }

}