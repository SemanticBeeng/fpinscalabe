package org.specs2.examples.typeclass

import org.fp._
import org.fp.studies.typeclass.tagging.Spec
import org.specs2.ugbase.UserGuidePage

/**
  *
  */
object TypeTagging_UserGuidePage extends UserGuidePage {

  def is = "Tagging types".title ^ s2"""

  ${concepts.functor}s enable ${concepts.functorComposition}

    * in ${resources.Scala.id} and ${resources.Simulacrum.id} : ${Spec.is}

"""
}
