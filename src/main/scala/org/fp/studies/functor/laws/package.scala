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

    s"$keyPoint Mapping Identity function leaves the $functor unchanged ".p
    s"see $lawIdentity"
    eg {
      import scalaz.Functor
      import scalaz.syntax.functor._
      import scalaz.std.list._

      val identity: Int => Int = x => x

      import org.scalacheck.Arbitrary

      import org.scalacheck.Arbitrary
      val anyList:List[Int] = Arbitrary.arbitrary[List[Int]].sample.get
      anyList map identity must_== anyList
    }

    s"$keyPoint Mapping a composed function on a $functor is same as the mapping the functions one by one ".p
    s"see $lawComposition"
    eg {
      import scalaz.Functor
      //import scalaz.syntax.functor._
      import scalaz.std.list._

      val f = (_: Int) * 3
      val g = (_: Int) + 1

      import org.scalacheck.Arbitrary
      val anyList:List[Int] = Arbitrary.arbitrary[List[Int]].sample.get

      //@note g compose f and not the other way around
      Functor[List].map(anyList)(g compose f) must_== Functor[List].map(anyList)(f).map(g)
    }

  }

  /**
    * @see [[Cats]]
    */
  object CatsSpec extends org.specs2.mutable.Spec with AutoExamples with TextDsl {

    s"$keyPoint Mapping Identity function leaves the $functor unchanged ".p
    s"see $lawIdentity"
    eg {
      import cats.syntax.functor._
      import cats.std.list._

      import org.scalacheck.Arbitrary
      val anyList:List[Int] = Arbitrary.arbitrary[List[Int]].sample.get
      anyList map identity must_== anyList
    }
  }
}