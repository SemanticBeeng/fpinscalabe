package org.specs2.guide.semigroup

import org.fp._
import org.fp.studies.semigroup.operators._
import org.specs2.ugbase.UserGuidePage

/**
  *
  */
object Semigroup_UserGuidePage extends UserGuidePage {

  def is = "Semigroup functor example".title ^ s2"""

  ${concepts.semigroup} ....
  Examples for a 'custom' ${concepts.semigroup}

    * with ${resources.Scalaz.id} and ${resources.Cats.id} in ${custom.Spec.is} and ${dfault.Spec.is}

}

"""
}
