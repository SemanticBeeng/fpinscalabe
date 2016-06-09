package org.specs2.ugbase

import org.specs2.examples.functor.{FunctorFunctionComposition, FunctorFunctionLifting, FunctorsMappingWithCustomMap, FunctorsMappingWithDefaultMap}
//import org.specs2.execute.SnippetParams

object UserGuide extends UserGuidePage {

  //implicit val snippetParams = SnippetParams(evalCode = true)
  def is = "Functional Programming in Scala - by Example".title ^ s2"""

 $specs2 is a library for writing executable software specifications in Scala.
  In this user guide, you will find:

  ## Functor examples
  ${link(FunctorsMappingWithDefaultMap).hide}
  ${link(FunctorsMappingWithCustomMap).hide}
  ${link(FunctorFunctionLifting).hide}
  ${link(FunctorFunctionComposition).hide}

  ## Applicative examples
"""

}
