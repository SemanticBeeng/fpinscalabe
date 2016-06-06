package org.specs2.examples.functor

import org.fp.concepts._
import org.specs2.functor.functionlifting.{CatsSpec, ScalazSpec}
import org.specs2.ugbase.UserGuidePage

/**
  *
  */
object FunctorFunctionLifting extends UserGuidePage {

  def is = "Lift functions in functors".title ^ s2"""

  $functor function lifting makes sense for single argument functions
    in Scalaz ${ScalazSpec.is}

    in Cats ${CatsSpec.is}

"""
}
