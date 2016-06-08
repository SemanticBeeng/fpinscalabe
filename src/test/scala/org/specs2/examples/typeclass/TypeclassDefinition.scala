package org.specs2.examples.typeclass

import org.fp._
import org.specs2.typeclass.definition.{SimulacrumSpec, ScalaSpec}
import org.specs2.ugbase.UserGuidePage

/**
  *
  */
object TypeclassDefinition extends UserGuidePage {

  def is = "Composing functions through functors".title ^ s2"""

  ${concepts.functor}s enable ${concepts.functorComposition}

    * in ${resources.Scala.id} ${ScalaSpec.is}

    * in ${resources.Simulacrum.id} ${SimulacrumSpec.is}

"""
}
