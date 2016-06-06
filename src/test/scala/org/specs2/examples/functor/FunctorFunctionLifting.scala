package org.specs2.examples.functor

import org.fp._
import org.specs2.functor.functionlifting.{CatsSpec, ScalazSpec}
import org.specs2.ugbase.UserGuidePage

/**
  *
  */
object FunctorFunctionLifting extends UserGuidePage {

  def is = "Lift functions in functors".title ^ s2"""

  ${concepts.functor} ${concepts.functionLifting} makes sense for single argument functions

    * in ${resources.Scalaz} ${ScalazSpec.is}

    * in ${resources.Cats} ${CatsSpec.is}

"""
}
