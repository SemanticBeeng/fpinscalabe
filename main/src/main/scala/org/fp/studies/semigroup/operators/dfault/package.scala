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

    s"$keyPoint ..."
    s"$bookmarks .."

    eg { /** in [[Scalaz]] */

      s"Option as $semigroup".p

      import scalaz.std.option._
      import scalaz.syntax.semigroup._
      import scalaz.syntax.std.option._

      success
    }

    eg {
      /** in [[Cats]] */
      //@todo
      success
    }
  }
}
