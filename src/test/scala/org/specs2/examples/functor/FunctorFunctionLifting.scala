package org.specs2.examples.functor

import org.specs2.common.SourceType.ScalazSpec
import org.specs2.examples.util.AmountExampleScalazSpec
import org.specs2.ugbase.UserGuidePage

/**
  *
  */
object FunctorFunctionLifting extends UserGuidePage {

  def is = "Lift functions in functors".title ^ s2"""

  [[Functor]] function lifting makes sense for single argument functions: ${snippet{

  /**
   *
   */
  class ScalazSpecification extends AmountExampleScalazSpec
                            with org.specs2.specification.dsl.mutable.TextDsl with ScalazSpec {

    import scalaz.Functor

    /**
      * Source
      * //https://hyp.is/hI15eiZ4EeaiWYOVMxLWcA/archive.is/O43Km
      */
    override def is = s2"""
      "Scalaz examples for custom [[Functor]]s"

      ${
        //val inc = (x: Int) => x + 1
        val timesTwo = (x: Int) => x * 2
        val amountTimesTwo = Functor[Amount].lift(timesTwo)

        amountTimesTwo(Few(1,2,3)) must_== Few(2,4,6)
      }
      """
  }

}}

"""
}
