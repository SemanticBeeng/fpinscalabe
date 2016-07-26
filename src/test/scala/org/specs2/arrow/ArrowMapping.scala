package org.specs2.arrow

import org.fp._
import org.fp.studies.arrow.operators.dfault.Spec1
import org.specs2.ugbase.UserGuidePage

/**
  *
  */
object ArrowMapping extends UserGuidePage {

  def is = "Arrow example".title ^ s2"""

  ${concepts.Arrow} ....
  Examples for a ${concepts.Arrow} ${concepts.functionComposition}

    * with ${resources.Scalaz.id} and ${resources.Cats.id} in ${Spec1.is}

}

"""
}
