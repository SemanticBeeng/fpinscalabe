package org.specs2.examples.functor

import org.fp._
import org.specs2.functor.withdefaultmap.{CatsSpec, ScalazSpec}
import org.specs2.ugbase.UserGuidePage

/**
  *
  */
object FunctorsWithDefaultMap extends UserGuidePage {

  def is = "Using functors with 'anything that has map'".title ^ s2"""

  ${concepts.functor} mapping (of a function) preserves the type/shape of the ${concepts.functor}

    Examples for "${concepts.functor}"-like ${concepts.higherKindedType}-s like Option and List (things that have a default map).
    Note that there is no need to declare a dedicated ${concepts.functor} but one is created ad-hoc.

    * in ${resources.Scalaz.id}  ${ScalazSpec.is}

    * in ${resources.Cats.id} ${CatsSpec.is}

  See ${"examples with custom functors" ~/ FunctorsWithCustomMap}

"""
}
