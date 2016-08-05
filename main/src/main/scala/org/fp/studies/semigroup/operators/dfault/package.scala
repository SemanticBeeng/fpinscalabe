package org.fp.studies.semigroup.operators

import org.fp.concepts._
import org.fp.resources._
import org.fp.bookmarks._

import scala.language.higherKinds
import scalaz.std

//
import org.specs2.specification.dsl.mutable.{TextDsl, AutoExamples}

/**
  *
  */
package object dfault {

  object Spec extends org.specs2.mutable.Spec with AutoExamples with TextDsl {

    s"$keyPoint Option[Int] forms a $semigroup"
    s"$bookmarks ..."

    eg { /** in [[Scalaz]] */

      s"Option as $semigroup".p

      import scalaz.Semigroup
      import scalaz.syntax.std.option._
      import scalaz.std.option._

      s"But needs to be defined in Scalaz".p
      implicit object IntSemigroup extends Semigroup[Int] {
        def append(f1: Int, f2: => Int): Int = f1 + f2
      }

      val f_some : Int => Option[Int] = { i => i.some }

      Semigroup[Option[Int]].append(1.some, 2.some) must_== 3.some
      Semigroup[Option[Int]].append(1.some, f_some(2))  must_== 3.some
    }

    eg {
      /** in [[Cats]] */
      //@todo
      success
    }
  }
}
