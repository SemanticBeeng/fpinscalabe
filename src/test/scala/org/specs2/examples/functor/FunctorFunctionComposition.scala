package org.specs2.examples.functor

import org.fp._
import org.specs2.functor.functioncomposition.{CatsSpec, ScalazSpec}
import org.specs2.ugbase.UserGuidePage

/**
  *
  */
object FunctorFunctionComposition extends UserGuidePage {

  def is = "Composing functins through functors".title ^ s2"""

  ${concepts.functor}s enable ${concepts.functionComposition}

    * in ${resources.Scalaz} ${ScalazSpec.is}

    * in ${resources.Cats} ${CatsSpec.is}

"""
}
