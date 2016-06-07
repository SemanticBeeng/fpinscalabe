package org.fp.studies.functor

import org.specs2.common.SourceType.{CatsSpecific, ScalazSpecific}

/**
  *
  */
package object functioncomposition {

  /**
    *
    */
  object ScalazSpec extends org.specs2.mutable.Spec with ScalazSpecific {

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
  object CatsSpec extends org.specs2.mutable.Spec with CatsSpecific {

    //@todo
//    import org.specs2.matcher.{ShouldExpectations=>_,_}
//    import org.specs2.matcher.{MustExpectations1=>_,_}
    //import org.specs2.matcher.{Expectable=>_,_}
    import cats.syntax.functor._
    import cats.std.function._

    val inc = (x: Int) => x + 1
    val timesTwo = (x: Int) => x * 2

    (inc map timesTwo)(3) must_== 8

//    val h = ((x: Int) => x + 1) map {_ * 7}
//    h(1) must_== 15
  }

}
