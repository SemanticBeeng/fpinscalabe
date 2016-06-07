package org.fp.studies.functor

import org.specs2.common.SourceType.{CatsSpecific, ScalazSpecific}
import org.fp.studies.functor.withcustommap.{AmountExample_FunctorScalaz, AmountExample_FunctorCats}
import org.fp.bookmarks._


/**
  * @see [[ann_FunctionLifting]]
  */
package object functionlifting {

  /**
    *
    */
  object ScalazSpec extends org.specs2.mutable.Spec with AmountExample_FunctorScalaz with ScalazSpecific {

    import scalaz.Functor

    val timesTwo = (x: Int) => x * 2
    val amountTimesTwo = Functor[Amount].lift(timesTwo)

    amountTimesTwo(Few(1,2,3)) must_== Few(2,4,6)

  }

  /**
    *
    */
  object CatsSpec extends org.specs2.mutable.Spec with AmountExample_FunctorCats with CatsSpecific {

    import cats.Functor

    val timesTwo = (x: Int) => x * 2
    val amountTimesTwo = Functor[Amount].lift(timesTwo)

    amountTimesTwo(Few(1,2,3)) must_== Few(2,4,6)
  }
}
