package org.specs2.examples.typeclass

import org.fp._
import org.fp.studies.typeclass.tagging.{ScalazSpec, CatsSpec}
import org.specs2.ugbase.UserGuidePage

/**
  *
  */
object TypeTagging extends UserGuidePage {

  def is = "Tagging types".title ^ s2"""

  ${concepts.functor}s enable ${concepts.functorComposition}

    * in ${resources.Scala.id} ${ScalazSpec.is}

    * in ${resources.Simulacrum.id} ${CatsSpec.is}

"""
}
