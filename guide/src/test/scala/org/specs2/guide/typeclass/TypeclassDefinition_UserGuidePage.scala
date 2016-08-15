package org.specs2.guide.typeclass

import org.fp._
import org.fp.studies.typeclass.definition.manual.{SimulacrumSpec, ScalaSpec}
import org.fp.studies.typeclass.variance.VarianceSpec

//
import org.specs2.ugbase.UserGuidePage

/**
  *
  */
object TypeclassDefinition_UserGuidePage extends UserGuidePage {

  def is = s"Defining type classes".title ^ s2"""

## How ${concepts.functor}s enable ${concepts.functorComposition}

 * with ${resources.Scala.md} in ${link(ScalaSpec)} and ${link(VarianceSpec)}
 * with ${resources.Simulacrum.md} in ${link(SimulacrumSpec)}

"""
}
