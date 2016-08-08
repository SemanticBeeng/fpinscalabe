package org.fp.studies.semigroup.operators

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

    s"$keyPoint Option[Int] forms a $semigroup"
    s"$bookmarks ..."

    eg { /** in [[Scalaz]] */

      import scalaz.Semigroup
      import scalaz.std.option._
      import scalaz.syntax.std.option._

      s"But needs to be defined in $Scalaz".p
      implicit object IntSemigroup extends Semigroup[Int] {
        def append(f1: Int, f2: => Int): Int = f1 + f2
      }

      val f_some : Int => Option[Int] = { i => i.some }

      Semigroup[Option[Int]].append(1.some, 2.some)     must_== 3.some
      Semigroup[Option[Int]].append(1.some, f_some(2))  must_== 3.some
      Semigroup[Option[Int]].append(1.some, None)       must_== 1.some
    }

    eg {
      /** in [[Cats]] */

      import cats.Semigroup
      import cats.std.option._
      import cats.syntax.option._

      s"But needs to be defined in $Cats as well".p
      implicit object IntSemigroup extends Semigroup[Int] {
        def combine(f1: Int, f2: Int): Int = f1 + f2
      }

      val f_some : Int => Option[Int] = { i => i.some }

      Semigroup[Option[Int]].combine(1.some, 2.some)     must_== 3.some
      Semigroup[Option[Int]].combine(1.some, f_some(2))  must_== 3.some
      Semigroup[Option[Int]].combine(1.some, None)       must_== 1.some
    }

    s"$keyPoint Map forms a $semigroup if the values form a $monoid."
    s"$bookmarks ..."

    eg { /** in [[Scalaz]] */

      import scalaz.std.map._
      import scalaz.std.anyVal._
      import scalaz.syntax.semigroup._

      s"So, maps with Int values form a $semigroup".p
      val map1 = Map(1 -> 9 , 2 -> 20)
      val map2 = Map(1 -> 100, 3 -> 300)

      val mergedMap: Map[Int, Int] = Map(1 -> 109, 2 -> 20, 3 -> 300)
      map1 |+| map2      must_== mergedMap
      map1.⊹(map2)       must_== mergedMap
      map1.mappend(map2) must_== mergedMap
    }

    eg {
      /** in [[Cats]] */

      import cats.std.map._
      //import cats.Anyva
      import cats.syntax.semigroup._

      s"So, maps with Int values form a $semigroup".p
      val map1 = Map(1 -> 9 , 2 -> 20)
      val map2 = Map(1 -> 100, 3 -> 300)

      val mergedMap: Map[Int, Int] = Map(1 -> 109, 2 -> 20, 3 -> 300)
      //@todo
//      map1 |+| map2      must_== mergedMap
//      map1.⊹(map2)       must_== mergedMap
//      map1.mappend(map2) must_== mergedMap
      success
    }
  }
}
