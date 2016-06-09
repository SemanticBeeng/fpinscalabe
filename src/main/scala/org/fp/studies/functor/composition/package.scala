package org.fp.studies.functor

import org.fp.concepts._
import org.fp.resources._
import org.fp.bookmarks._
//
import org.specs2.specification.dsl.mutable.{TextDsl, AutoExamples}

/**
  *
  * @see [[functorComposition]]
  */
package object composition {

  /**
    * @see [[Scalaz]]
    * Note the [[org.fp.resources.Scalaz]] dual syntax for function composition: 'map and '∘'
    */
  object ScalazSpec extends org.specs2.mutable.Spec with AutoExamples with TextDsl {

    s"$keyPoint Compose a function f : A => B with g : B => C by first lifting into a $functor:".p
    eg {

      val inc = (x: Int) => x + 1
      val timesTwo = (x: Int) => x * 2

      val func1 = (x: Int) => x.toDouble
      val func2 = (y: Double) => y * 2

      import scalaz.{std, syntax}
      import std.function._
      import syntax.functor._

      s"Function1 can be thought as infinite $operatorMap from the domain to the range. ".p
      s"bookmarks: $ann_Function1asMap".p
      ((inc map timesTwo) (3): Int) must_== 8

      "Order is opposed to regular 'compose'".p
      ((inc compose timesTwo) (3): Int) must_== 7

      val func3 = func1 map func2
      (func3(1) : Double) must_== 2.0
    }

    s"$keyPoint Compose a $functor-s $operatorMap with the ${Scala.id} collection map function:".p
    eg {
      val func1 = (x: String) => x.length
      val func2 = (y: Int) => y > 0

      import scalaz.{std, syntax}
      import std.function._
      import syntax.functor._

      val func3 = func1 map func2

      (func3("abc") : Boolean) must_== true

      List("abc", "", "def") map func1 map func2 must_== List(true, false, true)
      List("abc", "", "def") map func3           must_== List(true, false, true)
    }

    s"$keyPoint Compose any two $functor-s F[_] and G[_] to create a new $functor F[G[_]]:".p
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
    * @see [[Cats]]
    */
  object CatsSpec extends org.specs2.mutable.Spec with AutoExamples with TextDsl {

    s"$keyPoint Compose a function f : A => B with g : B => C by first lifting into a $functor:".p
    eg {

      val inc = (x: Int) => x + 1
      val timesTwo = (x: Int) => x * 2

      val func1 = (x: Int) => x.toDouble
      val func2 = (y: Double) => y * 2

      import cats.syntax.functor._
      import cats.std.function._

      s"Function1 can be thought as infinite $operatorMap from the domain to the range. ".p
      s"bookmarks: $ann_Function1asMap".p
      ((inc map timesTwo) (3): Int) must_== 8

      "Order is opposed to regular 'compose'".p
      ((inc compose timesTwo) (3): Int) must_== 7

      val func3 = func1 map func2
      (func3(1) : Double) must_== 2.0
    }

    s"$keyPoint Compose a $functor-s $operatorMap with the ${Scala.id} collection map function:".p
    eg {
      val func1 = (x: String) => x.length
      val func2 = (y: Int) => y > 0

      import cats.syntax.functor._
      import cats.std.function._

      val func3 = func1 map func2

      (func3("abc") : Boolean) must_== true

      List("abc", "", "def") map func1 map func2 must_== List(true, false, true)
      List("abc", "", "def") map func3           must_== List(true, false, true)
    }

    s"$keyPoint Compose any two $functor-s F[_] and G[_] to create a new $functor F[G[_]]:".p
    eg {

      import cats.Functor
      import cats.std.option._
      import cats.std.list._

      val listOpt = Functor[List] compose Functor[Option]

      listOpt.map(List(Some(1), None, Some(3)))(_ + 1) must_== List(Some(2), None, Some(4))
    }
  }

}
