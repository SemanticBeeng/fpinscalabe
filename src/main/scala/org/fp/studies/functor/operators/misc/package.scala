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

    s"$keyPoint The $operatorFproduct pairs a value with the result of applying a function to that value.".p
    eg {
      // 8<--
      import scalaz.std.list._
      import scalaz.syntax.functor._

      val len: String => Int = _.length
      // 8<--

      List("a",      "aa",      "b",       "ccccc").fproduct(len).toMap must_==
       Map("a" -> 1, "aa" -> 2, "b" ->  1, "ccccc" -> 5)
    }
  }

  /**
    * @see [[Cats]]
    */
  object CatsSpec extends org.specs2.mutable.Spec with AutoExamples with TextDsl {

    s"$keyPoint The $operatorFproduct pairs a value with the result of applying a function to that value.".p
    eg {
      // 8<--
      import cats.std.list._
      import cats.syntax.functor._

      val len: String => Int = _.length
      // 8<--

      List("a",       "aa",      "b",       "ccccc").fproduct(len).toMap must_==
        Map("a" -> 1, "aa" -> 2, "b" ->  1, "ccccc" -> 5)
    }
  }
}
