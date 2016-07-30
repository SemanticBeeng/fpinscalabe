package org.specs2.guide.kleisli

import org.fp._
import org.fp.studies.kleisli.composition._

//
import org.specs2.ugbase.UserGuidePage

/**
  *
  */
object Kleisli_UserGuidePage extends UserGuidePage {

  def is = "Kleisli example".title ^ s2"""

## Examples for a ${concepts.KleisliArrow} ${concepts.functionComposition} with ${resources.Scalaz.id} and ${resources.Cats.id}

 * ${link(Spec1)}
 * ${link(Spec2)}
 * ${link(Spec3)}
 * ${link(Spec4)}
 * ${link(Spec5)}


"""
}
