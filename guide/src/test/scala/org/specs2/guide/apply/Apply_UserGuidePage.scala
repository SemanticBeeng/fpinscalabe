package org.specs2.guide.apply

import org.fp._
import org.fp.studies.apply.operators._
import org.specs2.ugbase.UserGuidePage

/**
  *
  */
object Apply_UserGuidePage extends UserGuidePage {

  def is = "Apply functor example".title ^ s2"""

### ${concepts.applyFunctor} examples for a 'custom' ${concepts.applyFunctor} with ${resources.Scalaz.id} and ${resources.Cats.id} in
 * ${link(custom.CustomSpec)}
 * ${link(dfault.DefaultSpec)}

}

"""
}
