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

    s"$keyPoint ... "

    s"$bookmarks .. "

    eg { /** in [[Scalaz]] */

      import scalaz._

      import scalaz.std.list._
      import scalaz.std.option._

      //@todo
      success
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