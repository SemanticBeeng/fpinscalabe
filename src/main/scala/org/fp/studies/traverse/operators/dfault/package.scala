package org.fp.studies.traverse.operators

import org.fp.concepts._
import org.fp.resources._
import org.fp.bookmarks._

import scala.language.higherKinds

//
import org.specs2.specification.dsl.mutable.AutoExamples

/**
  *
  */
package object dfault {

  object Spec extends org.specs2.mutable.Spec with AutoExamples {

    s"$keyPoint The $traverseFunctor's $operatorSequence turns F[G[A] into G[F[A]] given that there exists " +
      s"an implementation of $traverseFunctor[F], and of $applicativeFunctor[G] (Option and List happen to satisfy both). " +
      s"This is like turning the structure 'inside-out'"

    s"$bookmarks $ann_TraverseUsage"

    eg { /** in [[Scalaz]] */

      import scalaz._

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

      import cats._
      import cats.std.list._
      import cats.std.option._
      import cats.syntax.traverse._

      val list1: List[Option[Int]] = List(Some(1), Some(2), Some(3), Some(4))
      //@todo list1.sequence must_== Some(List(1,2,3,4))
      //@todo list1.sequence.sequence must_== list1
      success
    }

    //@todo: finish using the example from Runar [[ann_Traverse]]
  }
}
