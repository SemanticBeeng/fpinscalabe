package org.fp.studies.functor

import org.fp.concepts._
import org.fp.resources._
import org.fp.bookmarks._
//

import org.scalacheck.Arbitrary
import org.scalacheck.Gen._
import org.scalacheck._

//
import org.specs2.specification.dsl.mutable.{TextDsl, AutoExamples}

/**
  *
  * @see [[operatorVoid]], [[operatorFproduct]]
  */
package object laws {

  /**
    * @see [[Scalaz]]
    */
  object ScalazSpec extends org.specs2.mutable.Spec with AutoExamples with TextDsl {

    s"$keyPoint Mapping Identity function has leaves the $functor unchanged ".p
    eg {
      import scalaz.syntax.functor._
      import scalaz.std.list._

      val anyList = Arbitrary {
        listOf(oneOf(1, 2, 3, listOf(oneOf('a', 'b, 'c'))))
      }
      anyList map identity must_== anyList
    }
  }

  /**
    * @see [[Cats]]
    */
  object CatsSpec extends org.specs2.mutable.Spec with AutoExamples with TextDsl {

    s"$keyPoint Mapping Identity function has leaves the $functor unchanged ".p
    eg {
      import cats.syntax.functor._
      import cats.std.list._

      val anyList = Arbitrary {
        listOf(oneOf(1, 2, 3, listOf(oneOf('a', 'b, 'c'))))
      }
      anyList map identity must_== anyList
    }
  }
}