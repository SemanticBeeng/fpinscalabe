package org.specs2.kleisli

import org.fp._
import org.fp.studies.kleisli.composition._
import org.specs2.ugbase.UserGuidePage

/**
  *
  */
object KleisliMapping extends UserGuidePage {

  def is = "Kleisli example".title ^ s2"""

  ${concepts.KleisliArrow} ....
  Examples for a ${concepts.KleisliArrow} ${concepts.functionComposition}

    * with ${resources.Scalaz.id} and ${resources.Cats.id} in ${Spec1.is} and ${Spec2.is}

}

"""
}
