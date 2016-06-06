package org.specs2.functor

import org.specs2.common.SourceType.{CatsSpecific, ScalazSpecific}

/**
  *
  */
package object functioncomposition {

  /**
    *
    */
  object ScalazSpec extends org.specs2.mutable.Specification  with ScalazSpecific {

    import scalaz.{std, syntax}
    import std.AllInstances._
    import syntax.functor._

    val inc = (x: Int) => x + 1
    val timesTwo = (x: Int) => x * 2

    (inc ∘ timesTwo)(3) must_== 8

  }

  /**
    *
    */
  object CatsSpec extends org.specs2.mutable.Specification with CatsSpecific {

    //@todo
//    import cats.{std, syntax}
//    //import std.AllInstances._
//    import syntax.functor._
//
//    val inc = (x: Int) => x + 1
//    val timesTwo = (x: Int) => x * 2
//
//    (inc ∘ timesTwo)(3) must_== 8
    success
  }

}
