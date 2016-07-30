package org.specs2.examples.applicative

import org.fp._
import org.fp.studies.applicative.operators._
import org.specs2.ugbase.UserGuidePage

/**
  *
  */
object Applicative_UserGuidePage extends UserGuidePage {

  def is = "Custom applicative".title ^ s2"""

  ${concepts.applicativeFunctor} mapping (of a function) preserves the type/shape of the ${concepts.applicativeFunctor}
  Examples for a 'custom' ${concepts.applicativeFunctor}

    * with ${resources.Scalaz.id} and ${resources.Cats.id} in ${custom.Spec.is} and ${dfault.Spec.is}

}

"""
}
