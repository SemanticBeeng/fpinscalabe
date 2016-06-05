package org.specs2.examples.functor

import org.fp.concepts._
import org.specs2.common.SourceType.ScalazSpec
import org.specs2.examples.util.AmountExampleScalazSpec
import org.specs2.ugbase.UserGuidePage

/**
  *
  */
object FunctorsWithCustomMap extends UserGuidePage {

  def is = "Creating custom functors".title ^ s2"""

  $functor mapping (of a function) preserves the type/shape of the $functor: ${snippet{

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
    "Scalaz examples for custom [[Functor]]s".p

    eg { Functor[Amount].map(One(6)) { x: Int => x * 7 } must_== One(42) }
//    eq {
//      import org.specs2.matcher.{MustExpectations1=>_,ShouldExpectations=>_, _}
//      import org.specs2.matcher.{_=>_}
//      (One(6): Amount[Int]).map { x: Int => x * 7 } /*must_== One(42)*/
//    }
  }

}}

"""
}
