package org.specs2.examples.functor

import org.fp._
import org.fp.studies.functor.functionlifting.Spec
import org.specs2.ugbase.UserGuidePage

/**
  *
  */
object FunctorFunctionLifting extends UserGuidePage {

  def is = "Functor function lifting".title ^ s2"""

  ${concepts.functor} ${concepts.functionLifting} makes sense for single argument functions

    * in ${resources.Scalaz.id} ${Spec.is}

"""
}
