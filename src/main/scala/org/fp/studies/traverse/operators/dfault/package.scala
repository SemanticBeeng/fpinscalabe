package org.fp.studies.traverse.operators

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

    s"$keyPoint The $traverseFunctor's $operatorSequence turns F[G[A] into G[F[A]] given that there exists " +
      s"an implementation of $traverseFunctor[F], and of $applicativeFunctor[G] (Option and List happen to satisfy both). " +
      s"This is like turning the structure 'inside-out'"

    s"$bookmarks $ann_TraverseUsage"

    eg { /** in [[Scalaz]] */

      import scalaz.Traverse
      import scalaz.std.list._
      import scalaz.std.option._

      val list1: List[Option[Int]] = List(Some(1), Some(2), Some(3), Some(4))
      // Explicit type declaration is needed to help inference
      Traverse[List].sequence(list1) must_== Some(List(1,2,3,4))
    }

    eg { /** in [[Cats]] */

      import cats._
      import cats.std.list._
      import cats.std.option._

      val list1: List[Option[Int]] = List(Some(1), Some(2), Some(3), Some(4))
      Traverse[List].sequence(list1) must_== Some(List(1,2,3,4))
    }

    s"$keyPoint Importing the $traverseFunctor syntax will enhance values which have an available $traverseFunctor instance:"

    eg { /** in [[Scalaz]] */

      import scalaz.std.list._
      import scalaz.std.option._

      import scalaz.syntax.traverse._

      val list1: List[Option[Int]] = List(Some(1), Some(2), Some(3), Some(4))
      // Explicit type declaration is needed to help inference
      list1.sequence must_== Some(List(1,2,3,4))
      list1.sequence.sequence must_== list1
    }

    eg { /** in [[Cats]] */

      import cats.std.list._
      import cats.std.option._
      import cats.syntax.traverse._

      val list1: List[Option[Int]] = List(Some(1), Some(2), Some(3), Some(4))
      //@todo list1.sequence must_== Some(List(1,2,3,4))
      //@todo list1.sequence.sequence must_== list1
      success
    }

    s"$keyPoint The $traverseFunctor's $operatorSequence maps a function over a structure through the effects of the " +
      s"inner $applicativeFunctor. You can think of this as combining a $operatorMap with a $operatorSequence. " +
      s"So when you find yourself calling fa.map(f).sequence, it can be replaced with just fa.traverse(f)::"
    /**
      * @todo determine if the [[operatorTraverse]] implementation is more economical in terms of intermediate data structures
      */

    eg { /** in [[Scalaz]] */

      import scalaz.std.list._
      import scalaz.std.option._
      import scalaz.syntax.traverse._

      val smallNumbers = List(1,2,3,4,5)
      val bigNumbers = List(10,20,30,40,50)
      val doubleSmall: Int => Option[Int] = x => if (x < 30) Some(x * 2) else None

      s"This multiples small numbers".p
      smallNumbers.traverse(doubleSmall) must_== Some(List(2,4,6,8,10))
      smallNumbers.traverse(doubleSmall) must_== smallNumbers.map(doubleSmall).sequence

      s"When we hit the 30, we get a None, which 'fails' the whole computation".p
      bigNumbers.traverse(doubleSmall) must_== scalaz.std.option.none[List[Int]]
      bigNumbers.traverse(doubleSmall) must_== bigNumbers.map(doubleSmall).sequence
    }

    eg { /** in [[Cats]] */

      import cats.std.list._
      import cats.std.option._
      import cats.syntax.traverse._

      val smallNumbers = List(1,2,3,4,5)
      val bigNumbers = List(10,20,30,40,50)
      val doubleSmall: Int => Option[Int] = x => if (x < 30) Some(x * 2) else None

      s"This multiples small numbers".p
      smallNumbers.traverse(doubleSmall) must_== Some(List(2,4,6,8,10))
      //@todo smallNumbers.traverse(doubleSmall) must_== smallNumbers.map(doubleSmall).sequence

      s"When we hit the 30, we get a None, which 'fails' the whole computation".p
      bigNumbers.traverse(doubleSmall) must_== scalaz.std.option.none[List[Int]]
      //@todo bigNumbers.traverse(doubleSmall) must_== bigNumbers.map(doubleSmall).sequence
    }

    /**
      * @todo: finish using the example from Runar [[ann_Traverse]]
      */

    s"$keyPoint Both $operatorSequence and $operatorTraverse come in 'Unapply' varieties: sequenceU and traverseU." +
      s" These are useful in the case when the scala compiler fails to infer an implicit $applicativeFunctor instance  for the 'inner' type. " +
      s"This will commonly happen when there is a 'Kind mismatch'. For example this happens with Validation, which is " +
      s"kind *,* -> * instead of the expected * -> * kind of an $applicativeFunctor, since the Validation type constructor takes two arguments instead of one."

    s"$bookmarks ... "

    eg { /** in [[Scalaz]] */

      import scalaz.{IList, NonEmptyList, ValidationNel}

      import scalaz.std.list._
      import scalaz.std.vector._
      import scalaz.std.anyVal._
      import scalaz.syntax.equal._      // for === syntax
      import scalaz.syntax.validation._ // for .success and .failure syntax
      import scalaz.syntax.traverse._

      val validations: Vector[ValidationNel[String,Int]] =
        Vector(1.success, "failure2".failureNel, 3.success, "failure4".failureNel)

      s"this would not compile:".p
      // val result = validations.sequence

      s"It gives you the perhaps hard to understand error:".p
      s"could not find implicit value for parameter ev: scalaz.Leibniz.===[scalaz.Validation[String,Int],G[B]".p

      s"These however work:".p
      val result: ValidationNel[String, Vector[Int]] = validations.sequenceU
      result must_== NonEmptyList("failure2","failure4").failure[Vector[Int]]

      val onlyEvenAllowed: Int => ValidationNel[String, Int] =
        x => if(x % 2 === 0) x.successNel else (x.toString + " is not even").failureNel

      val evens = IList(2,4,6,8)
      val notAllEvens = List(1,2,3,4)

      evens.traverseU(onlyEvenAllowed) must_== IList(2,4,6,8).success
      notAllEvens.traverseU(onlyEvenAllowed) must_== NonEmptyList("1 is not even", "3 is not even").failure
    }

    eg { /** in [[Cats]] */

      import cats._
      import cats.std.list._
      import cats.std.option._

      //@todo
      success
    }
  }
}
