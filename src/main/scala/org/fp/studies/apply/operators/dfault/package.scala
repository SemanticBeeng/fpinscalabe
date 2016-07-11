package org.fp.studies.apply.operators

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

    s"$keyPoint The $applyFunctor $operatorApply can have a few equivalent forms:"
    s"$bookmarks $ann_ApplicativeExtractsFunction2"

    eg { /** in [[Scalaz]] */

      s"Option as $applyFunctor".p

      import scalaz.syntax.applicative._
      import scalaz.std.option._
      import scalaz.syntax.std.option._

      9.some <*> {(_: Int) + 3}.some must_== Some(12)

      3.some <*> { 9.some <*> {(_: Int) + (_: Int)}.curried.some } must_== Some(12)

      3.some <*> { 9.some <*> {(_: Int) + (_: Int)}.curried.some } must_== Some(12)

      s"Another thing I found in 7.0.0-M3 is a new notation that extracts values from containers and apply them to a single function.".p

      s"This is actually useful because for one-function case, we no longer need to put it into the container. " +
        s"I am guessing that this is why Scalaz 7 does not introduce any operator from $applicativeFunctor itself. " +
        s"Whatever the case, it seems like we no longer need Pointed or <$$>.".p

      ^(3.some, 5.some) {_ + _} must_== 8.some

      ^(3.some, scalaz.std.option.none[Int]) {_ + _} must_== scalaz.std.option.none[Int]

      s"The new ^(f1, f2) {...} style is not without the problem though. It doesn’t seem to handle $applicativeFunctor-s that takes two type " +
        s"parameters like Function1, Writer, and Validation. There’s another way called $applicativeBuilder, which apparently was the way " +
        s"it worked in Scalaz 6, got deprecated in M3, but will be vindicated again because of ^(f1, f2) {...}’s issues. " +
        s"Here’s how it looks:".p

      (3.some |@| 5.some) {_ + _} must_== 8.some

      //@todo: elaborate on this point

      val times = {(_: Int) * (_:Double)}

      ^(2.some, Some(3.2))(times) must_== Some(6.4)
      //@todo (Some(1) |@| Some(2)) (times) must_== Some(3)

    }

    eg {
      /** in [[Cats]] */
      //@todo
      success
    }

    eg { /** in [[Scalaz]] */

      s"List as $applyFunctor".p
      s"Lists (actually the list $typeConstructor, []) are $applyFunctor-s. What a surprise!".p

      import scalaz.syntax.applicative._
      import scalaz.std.list._
      //import scalaz.syntax.std.list._

      List(1, 2, 3) <*> List((_: Int) * 0, (_: Int) + 100, (x: Int) => x * x) must_== List(0, 0, 0, 101, 102, 103, 1, 4, 9)

      List(3, 4) <*> { List(1, 2) <*> List({(_: Int) + (_: Int)}.curried, {(_: Int) * (_: Int)}.curried) } must_== List(4, 5, 5, 6, 3, 4, 6, 8)

      (List("ha", "heh", "hmm") |@| List("?", "!", ".")) {_ + _} must_== List("ha?", "ha!", "ha.", "heh?", "heh!", "heh.", "hmm?", "hmm!", "hmm.")

      ^(List(1, 2), List(3)) { _ * _ } must_== List(3, 6)

      (List(1, 2) |@| List(3)) { _ * _ } must_== List(3, 6)
    }

    eg {
      /** in [[Cats]] */
      //@todo
      success
    }

    s"$keyPoint Applying $applicativeFunctor $operatorLHS and $operatorRHS to extract a projection:"
    eg {
      /** in [[Scalaz]] */

      import scalaz.syntax.applicative._
      import scalaz.std.option._
      import scalaz.syntax.std.option._

      1.some <* 2.some must_== Some(1)

      scalaz.std.option.none <* 2.some must_== None

      1.some *> 2.some must_== Some(2)

      scalaz.std.option.none *> 2.some must_== None

    }

    eg { /** in [[Cats]] */

      //@todo
      success
    }

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
      s"It doesn't introduce an operator, but it introduces point method ($operatorPoint) and its symbolic alias η to all data types."

    s"$bookmarks: $ann_ApplicativeAsTypeConstructor"

    eg { /** in [[Scalaz]] */

      import scalaz.std.list._
      import scalaz.std.option._
      import scalaz.syntax.applicative._

      1.point[List] must_== List(1)
      1.point[List] map {_ + 2} must_== List(3)
      1.η[List] map {_ + 2} must_== List(3)

      1.point[Option] must_== Some(1)
      1.point[Option] map {_ + 2} must_== Some(3)
      1.η[Option] map {_ + 2} must_== Some(3)
    }

    eg { /** in [[Cats]] */

      import cats.std.list._
      import cats.std.option._
      import cats.syntax.applicative._

      1.pure[List] must_== List(1)
      1.pure[List] map {_ + 2} must_== List(3)
      //@todo 1.η[List] map {_ + 2} must_== List(3)

      1.pure[Option] must_== Some(1)
      1.pure[Option] map {_ + 2} must_== Some(3)
      //@todo 1.η[Option] map {_ + 2} must_== Some(3)
    }

    s"I can’t really express it in words yet, but there’s something cool about the fact that $constructor is abstracted out.".p

  }
}
