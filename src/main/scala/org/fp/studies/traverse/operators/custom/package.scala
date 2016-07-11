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
package object custom {

  object Spec extends org.specs2.mutable.Spec with AutoExamples with TextDsl {

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

    s"$keyPoint The $operatorTraverse comes in a form, traverseS, that allows traversing a structure with with a function while " +
      s"carrying a state through the computation."

    s"$bookmarks .. "

    eg { /** in [[Scalaz]] */

      import scalaz._

      import scalaz.State._
      import scalaz.std.list._
      import scalaz.std.option._
      import scalaz.std.anyVal._
      import scalaz.syntax.equal._      // for === syntax
      import scalaz.syntax.traverse._

      s"The state stores the last seen Int, returns whether of not the current was a repeat".p
      val checkForRepeats: Int => State[Option[Int], Boolean] = { next =>
        for {
          last <- get
          _ <- put(scalaz.std.option.some(next))
        } yield last === scalaz.std.option.some(next)
      }

      val nonRepeating = List(1,2,3,4)
      val repeating = List(1,2,3,3,4)

      s"Traverse the lists with None as the starting state, we get back a list of Booleans meaning this element was a repeat of the previous".p

      val res1: List[Boolean] = nonRepeating.traverseS(checkForRepeats).eval(None)
      val res2: List[Boolean] = repeating.traverseS(checkForRepeats).eval(None)

      Tag.unwrap(res1.foldMap(Tags.Disjunction(_))) must_== false
      Tag.unwrap(res2.foldMap(Tags.Disjunction(_))) must_== true

      //@todo can this be done in a more economical in terms of intermediate data structure?

      s"Here's a variation of above which might be a bit of a head scratcher, but this works because a $monoid gives rise to an $applicativeFunctor." +
        s"Because Boolean is not a * -> * $typeConstructor, we need traverseU instead of $operatorTraverse to find the $applicativeFunctor.".p

      //@todo: understand the role of this function; does not seem used
      import scalaz.Applicative.monoidApplicative

      Tag.unwrap(res1.traverseU(Tags.Disjunction(_))) must_== false
      Tag.unwrap(res2.traverseU(Tags.Disjunction(_))) must_== true
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
