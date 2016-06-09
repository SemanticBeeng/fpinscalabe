package org.fp.studies.functor

import org.fp.concepts._
import org.fp.resources._
import org.fp.bookmarks._
import org.specs2.specification.dsl.mutable.{AutoExamples, TextDsl}

//
import org.fp.studies.functor.mapping.withcustommap.{AmountExample_FunctorScalaz, AmountExample_FunctorCats}

/**
  *
  * [[functorComposition]]
  *
  * @see [[ann_FunctionLifting]]
  */
package object functionlifting {

  /**
    * @see [[Scalaz]]
    */
  object ScalazSpec extends org.specs2.mutable.Spec with AutoExamples with TextDsl with AmountExample_FunctorScalaz {

    s"$keyPoint Explicit $functionLifting".p
    eg {
      import scalaz.Functor

      val timesTwo = (x: Int) => x * 2
      val amountTimesTwo = Functor[Amount].lift(timesTwo)

      amountTimesTwo(Few(1,2,3)) must_== Few(2,4,6)
    }
  }

  /**
    * @see [[Cats]]
    */
  object CatsSpec extends org.specs2.mutable.Spec with AutoExamples with TextDsl with AmountExample_FunctorCats {

    s"$keyPoint Explicit $functionLifting".p
      eg {
      import cats.Functor

      val timesTwo = (x: Int) => x * 2
      val amountTimesTwo = Functor[Amount].lift(timesTwo)

      amountTimesTwo(Few(1,2,3)) must_== Few(2,4,6)
    }
  }
}
