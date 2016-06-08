package org.fp.studies.monoid

import org.fp.concepts._
import org.fp.resources._
import org.fp.bookmarks._
//
import org.specs2.specification.dsl.mutable.{AutoExamples, TextDsl}

/**
  * @see [[monoid]]
  */
package object basic {


  /**
    * @see [[Scalaz]]
    */
  object ScalazSpec extends org.specs2.mutable.Spec with TextDsl with AutoExamples {

    s"$keyPoint Use $monoid-s to abstract over combining things of a same kind (using the explicit syntax):".p
    eg {
      import scalaz._, std.anyVal._

      Monoid[Int].append(10,20) must_== 30
    }

    s"$keyPoint Use $monoid-s to abstract over combining things of a same kind (using the sugar syntax):".p
    eg {
      import scalaz._, syntax.semigroup._, std.anyVal._

      10 |+| 20 must_== 30
    }

  }

  /**
    * @see [[Cats]]
    */
  object CatsSpec extends org.specs2.mutable.Spec with TextDsl with AutoExamples {

    s"$keyPoint Use $monoid-s to abstract over combining things of a same kind (using the explicit syntax):".p
    eg {
/*@todo
      import cats.Monoid
      import cats.std.anyVal._
      import cats.syntax.m

      Monoid[Int].append(10,20) must_== 30
*/
      success
    }

    s"$keyPoint Use $monoid-s to abstract over combining things of a same kind (using the sugar syntax):".p
    eg {
/*@todo
      import cats._, syntax.semigroup._, std.anyVal._

      10 |+| 20 must_== 30
*/
      success
    }
  }

}
