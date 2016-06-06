package org.specs2.examples.functor

import org.fp.concepts._
import org.specs2.functor.withcustommap.{ScalazSpec, CatsSpec}
import org.specs2.ugbase.UserGuidePage

/**
  *
  */
object FunctorsWithCustomMap extends UserGuidePage {

  def is = "Creating custom functors".title ^ s2"""

  $functor mapping (of a function) preserves the type/shape of the $functor

    in Scalaz ${ScalazSpec.is}

    in Cats ${CatsSpec.is}

}

"""
}
