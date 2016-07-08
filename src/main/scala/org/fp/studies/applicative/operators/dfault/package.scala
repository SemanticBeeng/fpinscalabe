package org.fp.studies.applicative.operators

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

    s"$keyPoint The $applicativeFunctor $operatorSequence turns F[G[A] into G[F[A]] given that there exists an implementation of " +
      s"$traverseFunctor[F], and of $applicativeFunctor[G] (Option and List happen to satisfy both).:"
    s"$bookmarks $ann_TraverseUsage"

    eg { /** in [[Scalaz]] */

      import scalaz._

      import scalaz.std.list._
      import scalaz.std.option._

      val list1: List[Option[Int]] = List(Some(1), Some(2), Some(3), Some(4))
      Traverse[List].sequence(list1) must_== Some(List(1,2,3,4))

      /**
        * @doesnotcompile
        *
        * Traverse[List].sequence(List(Some(1), Some(2), Some(3))) must_== Some(List(1, 2, 3))
        */
    }

    eg { /** in [[Cats]] */

      import cats._
      import cats.std.list._
      import cats.std.option._

      val list1: List[Option[Int]] = List(Some(1), Some(2), Some(3), Some(4))
      Traverse[List].sequence(list1) must_== Some(List(1,2,3,4))

      /**
        * @doesnotcompile
        *
        * Traverse[List].sequence(List(Some(1), Some(2), Some(3))) must_== Some(List(1, 2, 3))
        */
    }
  }
}
