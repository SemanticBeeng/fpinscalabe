package org.fp.studies.functor

import org.specs2.common.SourceType.{CatsSpecific, ScalazSpecific}
import org.specs2.specification.dsl.mutable.AutoExamples

/**
  *
  */
package object functioncomposition {

  /**
    * Note the [[org.fp.resources.Scalaz]] dual syntax for function composition: 'map and '∘'
    */
  object ScalazSpec extends org.specs2.mutable.Spec with AutoExamples with ScalazSpecific {

    import scalaz.{std, syntax}
    import std.AllInstances._
    import syntax.functor._

    eg {
      val inc = (x: Int) => x + 1
      val timesTwo = (x: Int) => x * 2

      (inc ∘ timesTwo)(3) must_== 8
    }

    eg {
      val func1 = (x: Int) => x.toDouble
      val func2 = (y: Double) => y * 2
      val func3 = func1 map func2

      func3(1) must_== 2.0
    }
  }

  /**
    *
    */
  object CatsSpec extends org.specs2.mutable.Spec with AutoExamples with CatsSpecific {

    import cats.syntax.functor._
    import cats.std.function._

    eg {
      val inc = (x: Int) => x + 1
      val timesTwo = (x: Int) => x * 2

      (inc map timesTwo)(3) must_== 8
    }

    eg {
      val func1 = (x: Int) => x.toDouble
      val func2 = (y: Double) => y * 2
      val func3 = func1 map func2

      func3(1) must_== 2.0
    }
  }

}
