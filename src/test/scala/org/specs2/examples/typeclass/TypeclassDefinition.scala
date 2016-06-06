package org.specs2.examples.typeclass

import org.fp._
import org.specs2.typeclass.definition.{SimulacrumSpec, ScalaSpec}
import org.specs2.ugbase.UserGuidePage

/**
  *
  */
object TypeclassDefinition extends UserGuidePage {

  def is = "Composing functions through functors".title ^ s2"""

  ${concepts.functor}s enable ${concepts.functionComposition}

    * in ${resources.Scala} ${ScalaSpec.is}

    * in ${resources.Simulacrum} ${SimulacrumSpec.is}

"""
}
