package org.specs2.examples.applicative

import org.fp._
import org.fp.studies.applicative.operators._
import org.specs2.ugbase.UserGuidePage

/**
  *
  */
object ApplicativeMapping extends UserGuidePage {

  def is = "Custom applicative".title ^ s2"""

  ${concepts.applicative} mapping (of a function) preserves the type/shape of the ${concepts.applicative}
  Examples for a 'custom' ${concepts.applicative}

    * in ${resources.Scalaz.id} and ${resources.Cats.id}: ${withcustommap.Spec.is}

}

"""
}
