package org.specs2.examples.functor

import org.fp.concepts._
import org.specs2.functor.withdefaultmap.{CatsSpec, ScalazSpec}
import org.specs2.ugbase.UserGuidePage

/**
  *
  */
object FunctorsWithDefaultMap extends UserGuidePage {

  def is = "Using functors with 'anything that has map'".title ^ s2"""

  $functor mapping (of a function) preserves the type/shape of the $functor

    in Scalaz ${ScalazSpec.is}

    in Cats ${CatsSpec.is}

"""
}
