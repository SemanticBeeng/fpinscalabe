package org.specs2.examples.apply

import org.fp._
import org.fp.studies.apply.operators._
import org.specs2.ugbase.UserGuidePage

/**
  *
  */
object Apply_UserGuidePage extends UserGuidePage {

  def is = "Apply functor example".title ^ s2"""

  ${concepts.applyFunctor} ....
  Examples for a 'custom' ${concepts.applyFunctor}

    * with ${resources.Scalaz.id} and ${resources.Cats.id} in ${custom.Spec.is} and ${dfault.Spec.is}

}

"""
}
