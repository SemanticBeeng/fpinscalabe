package org.specs2.examples.functor

import org.fp._
import org.fp.studies.functor.functionlifting.{CatsSpec, ScalazSpec}
import org.specs2.ugbase.UserGuidePage

/**
  *
  */
object FunctorFunctionLifting extends UserGuidePage {

  def is = "Functor function lifting".title ^ s2"""

  ${concepts.functor} ${concepts.functionLifting} makes sense for single argument functions

    * in ${resources.Scalaz.id} ${ScalazSpec.is}

    * in ${resources.Cats.id} ${CatsSpec.is}

"""
}
