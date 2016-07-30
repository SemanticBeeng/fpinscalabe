package org.specs2.guide.traverse

import org.fp._
import org.fp.studies.traverse.operators._
import org.specs2.ugbase.UserGuidePage

/**
  *
  */
object Traverse_UserGuidePage extends UserGuidePage {

  def is = "Traverse functors".title ^ s2"""

  ${concepts.traverseFunctor} ...
  Examples for a 'custom' ${concepts.traverseFunctor}

    * with ${resources.Scalaz.id} and ${resources.Cats.id} in ${custom.Spec.is} and ${dfault.Spec.is}

}

"""
}
