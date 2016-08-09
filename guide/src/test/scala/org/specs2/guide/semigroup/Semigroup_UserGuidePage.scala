package org.specs2.guide.semigroup

import org.fp._
//
import org.fp.studies.semigroup._
import org.specs2.ugbase.UserGuidePage

/**
  *
  */
object Semigroup_UserGuidePage extends UserGuidePage {

  def is = "Semigroup functor example".title ^ s2"""

## Examples for a 'default' ${concepts.semigroup} with ${resources.Scalaz.id} and ${resources.Cats.id}

 * ${link(operators.dfault.Spec1).hide}
 * ${link(operators.dfault.Spec2).hide}

## Examples for a 'custom' ${concepts.semigroup} with ${resources.Scalaz.id} and ${resources.Cats.id}

 * ${link(operators.custom.Spec).hide}

## Examples for a ${concepts.semigroupLaws} with ${resources.Scalaz.id} and ${resources.Cats.id}

 * ${link(laws.Spec).hide}

}

"""
}
