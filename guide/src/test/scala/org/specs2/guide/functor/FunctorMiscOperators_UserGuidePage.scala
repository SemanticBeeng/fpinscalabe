package org.specs2.guide.functor

import org.fp._
import org.fp.studies.functor.operators.misc.Spec
import org.specs2.ugbase.UserGuidePage

/**
  *
  */
object FunctorMiscOperators_UserGuidePage extends UserGuidePage {

  def is = "Functor misc operators".title ^ s2"""

  A ${concepts.functor} has other operators besides ${concepts.operator_map}: ${concepts.operator_void}, ${concepts.operator_Fproduct}, ${concepts.operator_as}
  See examples:

    * in ${resources.Scalaz.id} and ${resources.Cats.id}: ${Spec.is}

"""
}
