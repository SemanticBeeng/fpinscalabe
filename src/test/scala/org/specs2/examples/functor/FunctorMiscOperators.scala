package org.specs2.examples.functor

import org.fp._
import org.fp.studies.functor.operators.misc.Spec
import org.specs2.ugbase.UserGuidePage

/**
  *
  */
object FunctorMiscOperators extends UserGuidePage {

  def is = "Functor misc operators".title ^ s2"""

  A ${concepts.functor} has other operators besides ${concepts.operatorMap}: ${concepts.operatorVoid}, ${concepts.operatorFproduct}, ${concepts.operatorAs}
  See examples:

    * in ${resources.Scalaz.id} and ${resources.Cats.id}: ${Spec.is}

"""
}
