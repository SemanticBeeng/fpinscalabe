package org.fp.studies.functor

import org.specs2.common.SourceType.{CatsSpecific, ScalazSpecific}
import org.specs2.specification.dsl.mutable.{TextDsl, AutoExamples}

/**
  *
  * [[org.fp.concepts.functorComposition]]
  *
  */
package object composition {

  /**
    * Note the [[org.fp.resources.Scalaz]] dual syntax for function composition: 'map and '∘'
    */
  object ScalazSpec extends org.specs2.mutable.Spec with AutoExamples with TextDsl with ScalazSpecific {


    eg {
      import scalaz.{std, syntax}
      import std.function._
      import syntax.functor._

      val inc = (x: Int) => x + 1
      val timesTwo = (x: Int) => x * 2

      (inc ∘ timesTwo)(3) must_== 8
    }

    eg {
      import scalaz.{std, syntax}
      import std.function._
      import syntax.functor._

      val func1 = (x: Int) => x.toDouble
      val func2 = (y: Double) => y * 2
      val func3 = func1 map func2

      val res: Double = func3(1)
      res must_== 2.0
    }

    "Given any Functor F[_] and any Functor G[_] we can compose the two Functors to create a new Functor on F[G[_]]:".p
    eg {
      import scalaz.Functor
      import scalaz.std
      import std.option._
      import std.list._

      val listOpt = Functor[List] compose Functor[Option]
      listOpt.map(List(Some(1), None, Some(3)))(_ + 1) must_== List(Some(2), None, Some(4))
    }
  }

  /**
    *
    */
  object CatsSpec extends org.specs2.mutable.Spec with AutoExamples with TextDsl with CatsSpecific {


    eg {
      import cats.syntax.functor._
      import cats.std.function._

      val inc = (x: Int) => x + 1
      val timesTwo = (x: Int) => x * 2

      val res: Int = (inc map timesTwo) (3)
      res must_== 8
    }

    eg {
      import cats.syntax.functor._
      import cats.std.function._

      val func1 = (x: Int) => x.toDouble
      val func2 = (y: Double) => y * 2
      val func3 = func1 map func2

      val res: Double = func3(1)
      res must_== 2.0
    }

    "Given any Functor F[_] and any Functor G[_] we can compose the two Functors to create a new Functor on F[G[_]]:".p
    eg {

      import cats.Functor
      import cats.std.option._
      import cats.std.list._

      val listOpt = Functor[List] compose Functor[Option]
      listOpt.map(List(Some(1), None, Some(3)))(_ + 1) must_== List(Some(2), None, Some(4))
    }
  }

}
