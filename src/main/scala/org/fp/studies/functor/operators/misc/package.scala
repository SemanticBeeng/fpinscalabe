package org.fp.studies.functor.operators

import org.fp.concepts._
import org.fp.resources._
import org.fp.bookmarks._
//
import org.specs2.specification.dsl.mutable.{TextDsl, AutoExamples}

  /**
    *
    * @see [[operatorVoid]], [[operatorFproduct]]
    */
package object misc {

  /**
    * @see [[Scalaz]]
    */
  object ScalazSpec extends org.specs2.mutable.Spec with AutoExamples with TextDsl {

    s"$keyPoint ...".p
    eg {
      success
    }
  }

  /**
    * @see [[Cats]]
    */
  object CatsSpec extends org.specs2.mutable.Spec with AutoExamples with TextDsl {

    s"$keyPoint ...".p
    eg {
      success
    }
  }
}
