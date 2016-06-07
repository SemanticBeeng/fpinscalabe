package org.specs2.examples.functor

import org.fp._
import org.fp.studies.functor.withcustommap.{ScalazSpec, CatsSpec}
import org.specs2.ugbase.UserGuidePage

/**
  *
  */
object FunctorsWithCustomMap extends UserGuidePage {

  def is = "Creating custom functors".title ^ s2"""

  ${concepts.functor} mapping (of a function) preserves the type/shape of the ${concepts.functor}
  Examples for a 'custom' ${concepts.functor}

    * in ${resources.Scalaz.id} ${ScalazSpec.is}

    * in ${resources.Cats.id} ${CatsSpec.is}

  See ${"examples with functors that have a 'default map'" ~/ FunctorsWithDefaultMap}
}

"""
}
