package org.fp.studies.applicative.operators

import org.fp.concepts._
import org.fp.resources._
import org.fp.bookmarks._

import scala.language.higherKinds

//
import org.specs2.specification.dsl.mutable.{TextDsl, AutoExamples}

/**
  *
  */
package object dfault {

  object Spec extends org.specs2.mutable.Spec with AutoExamples with TextDsl {

    s"$keyPoint So far, when we were mapping functions over $functor-, we usually mapped functions that take only one parameter. " +
      s"But what happens when we $operatorMap a function like *, which takes two parameters, over a $functor?"

    s"$bookmarks $ann_ApplicativeMoreThanFunctor "

    eg { /** in [[Scala]] */

      val times = {(_: Int) * (_:Int)}
      /**
        * @doesnotcompile
        *
        *  List(1, 2, 3, 4) map times
        */

      s"We have to curry the function to $operatorMap and we get a list of partial functions:".p
      val partialFuncList = List(1, 2, 3, 4) map times.curried

      s"Now, we can map with the second parameter of 'times' to get the results.".p
      partialFuncList.map(f => f(9)) must_== List(9, 18, 27, 36)
    }

    s"$keyPoint So $applicativeFunctor extends another $typeClass Apply, and introduces $operatorPoint and its alias 'pure'." +
      s"LYAHFGG: " +
      s"  $operatorPoint should take a value of any type and return an $applicativeValue with that value inside it. … " +
      s"A better way of thinking about $operatorPoint would be to say that it takes a value and puts it in some sort " +
      s"of default (or pure) context—a minimal context that still yields that value."

    s"$keyPoint $Scalaz likes the name point instead of pure, and it seems like it’s basically a constructor that takes value A and returns F[A]. " +
      s"It doesn’t introduce an operator, but it introduces point method and its symbolic alias η to all data types."

    s"$bookmarks: $ann_ApplicativeAsTypeConstructor"

    eg { /** in [[Scalaz]] */

      import scalaz.std.list._
      import scalaz.std.option._
      import scalaz.syntax.applicative._

      1.point[List] must_== List(1)
      1.point[List] map {_ + 2} must_== List(3)

      1.point[Option] must_== Some(1)
      1.point[Option] map {_ + 2} must_== Some(3)
    }

    eg { /** in [[Cats]] */

      import cats.std.list._
      import cats.std.option._
      import cats.syntax.applicative._

      1.pure[List] must_== List(1)
      1.pure[List] map {_ + 2} must_== List(3)

      1.pure[Option] must_== Some(1)
      1.pure[Option] map {_ + 2} must_== Some(3)
    }

    s"I can’t really express it in words yet, but there’s something cool about the fact that constructor is abstracted out.".p

  }
}
