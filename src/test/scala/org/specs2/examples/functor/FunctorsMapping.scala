package org.specs2.examples.functor

import org.fp._
import org.fp.studies.functor.operators._
import org.specs2.ugbase.UserGuidePage

/**
  *
  */
object FunctorsMapping extends UserGuidePage {

  def is = "Custom functors".title ^ s2"""

  ${concepts.functor} mapping (of a function) preserves the type/shape of the ${concepts.functor}
  Examples for a 'custom' ${concepts.functor}

    * in ${resources.Scalaz.id} ${withcustommap.ScalazSpec.is}

    * in ${resources.Cats.id} ${withcustommap.CatsSpec.is}

  Examples for "${concepts.functor}"-like ${concepts.higherKindedType}-s (see Option and List aka 'things that have a default map').
  Note that there is no need to declare a dedicated ${concepts.functor} but one is created ad-hoc.

    * in ${resources.Scalaz.id} ${withdefaultmap.ScalazSpec.is}

    * in ${resources.Cats.id} ${withdefaultmap.CatsSpec.is}
}

"""
}
