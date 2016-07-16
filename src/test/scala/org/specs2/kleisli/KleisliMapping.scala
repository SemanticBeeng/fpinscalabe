package org.specs2.kleisli

import org.fp._
import org.fp.studies.kleisli.composition._
import org.specs2.ugbase.UserGuidePage

/**
  *
  */
object KleisliMapping extends UserGuidePage {

  def is = "Kleisli example".title ^ s2"""

  ${concepts.Kleisli} ....
  Examples for a ${concepts.Kleisli} ${concepts.functionComposition}

    * with ${resources.Scalaz.id} and ${resources.Cats.id} in $Spec.is}

}

"""
}
