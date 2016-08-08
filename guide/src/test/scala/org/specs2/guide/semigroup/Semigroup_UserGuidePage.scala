package org.specs2.guide.semigroup

import org.fp._
import org.fp.studies.semigroup.operators._
import org.specs2.ugbase.UserGuidePage

/**
  *
  */
object Semigroup_UserGuidePage extends UserGuidePage {

  def is = "Semigroup functor example".title ^ s2"""

## Examples for a 'custom' ${concepts.semigroup} with ${resources.Scalaz.id} and ${resources.Cats.id} in

 * ${link(dfault.Spec1).hide}
 * ${link(dfault.Spec2).hide}
 * ${link(custom.Spec).hide}

}

"""
}
