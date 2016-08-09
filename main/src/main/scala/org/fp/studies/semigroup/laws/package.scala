package org.fp.studies.semigroup

import org.fp.concepts._
import org.fp.resources._
import org.fp.bookmarks._

import scalaz.scalacheck.ScalazProperties.semigroup

//
import org.specs2.specification.dsl.mutable.{TextDsl, AutoExamples}
import org.specs2.ScalaCheck

/**
  *
  * @see [[lawIdentity]], [[lawComposition]]
  */
package object laws {

  object Spec extends org.specs2.mutable.Spec with AutoExamples with TextDsl with ScalaCheck {

    s"$keyPoint The only law for is a $semigroup is $lawAssociativity".p
    s"see $lawIdentity"

    eg {
      /** in [[Scalaz]] */

      import scalaz.Semigroup
      import scalaz.std.anyVal._
      import scalaz.syntax.semigroup._

//      implicit object IntSemigroup extends Semigroup[Int] {
//        def append(f1: Int, f2: => Int): Int = f1 + f2
//      }

      import org.scalacheck.{Gen, Arbitrary}
      implicit val arbMyType: Arbitrary[Int] = Arbitrary(Gen.oneOf(1, 2, 3, 4, 5))
      prop { i : Int => semigroup.laws[Int] }
    }

    eg {
      /** in [[Cats]] */

      import cats.Semigroup
      import cats.std.all._
      import cats.kernel.laws.GroupLaws

      val rs1 = GroupLaws[Int].semigroup(Semigroup[Int])

      rs1.all.check
      success //@todo need to do better?
    }
  }
}
