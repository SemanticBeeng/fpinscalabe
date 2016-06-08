package org.fp.studies.typeclass

import org.fp.concepts._
import org.fp.resources._
import org.fp.bookmarks._
//
import org.specs2.specification.dsl.mutable.{AutoExamples, TextDsl}

/**
  * @see [[taggedType]]
  */
package object tagging {


    /**
      * @see [[Scalaz]]
      */
    object ScalazSpec extends org.specs2.mutable.Spec with TextDsl with AutoExamples {

      s"$keyPoint ...:".p
      eg {
        success
      }
    }

    /**
      * @see [[Cats]]
      */
    object CatsSpec extends org.specs2.mutable.Spec with TextDsl with AutoExamples {

      s"$keyPoint ... ".p
      eg {
        success
      }
    }
}
