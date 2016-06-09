package org.fp.studies.functor

import org.fp.concepts._
import org.fp.resources._
import org.fp.bookmarks._
//
import org.specs2.specification.dsl.mutable.{TextDsl, AutoExamples}

/**
  *
  * @see [[lawIdentity]], [[lawComposition]]
  */
package object laws {

  /**
    * @see [[Scalaz]]
    */
  object ScalazSpec extends org.specs2.mutable.Spec with AutoExamples with TextDsl {

    s"$keyPoint Mapping Identity function has leaves the $functor unchanged ".p
    s"see $lawIdentity"
    eg {
      import scalaz.Functor
      import scalaz.syntax.functor._
      import scalaz.std.list._

      val identity: Int => Int = x => x

      import org.scalacheck.Arbitrary
      import org.scalacheck.Prop.forAll

      import org.scalacheck.Arbitrary
      val anyList = Arbitrary.arbitrary[Int].sample
      anyList map identity must_== anyList
    }
  }

  /**
    * @see [[Cats]]
    */
  object CatsSpec extends org.specs2.mutable.Spec with AutoExamples with TextDsl {

    s"$keyPoint Mapping Identity function has leaves the $functor unchanged ".p
    s"see $lawIdentity"
    eg {
      import cats.syntax.functor._
      import cats.std.list._

      import org.scalacheck.Arbitrary
      val anyList = Arbitrary.arbitrary[Int].sample
      anyList map identity must_== anyList
    }
  }
}