package org.specs2.ugbase

import org.specs2.examples.functor.{FunctorMiscOperators_UserGuidePage, FunctorFunctionComposition_UserGuidePage, FunctorFunctionLifting_UserGuidePage, FunctorsMapping_UserGuidePage}
import org.specs2.examples.monoid.Monoid_UserGuidePage
import org.specs2.examples.typeclass.{TypeTagging_UserGuidePage, TypeclassDefinition_UserGuidePage}

//import org.specs2.execute.SnippetParams

object UserGuide extends UserGuidePage {

  //implicit val snippetParams = SnippetParams(evalCode = true)
  def is = "Functional Programming in Scala - by Example".title ^ s2"""

 $specs2 is a library for writing executable software specifications in Scala.
  In this user guide, you will find:

  ## Typeclass examples

  ${link(TypeclassDefinition_UserGuidePage).hide}
  ${link(TypeTagging_UserGuidePage).hide}

  ## Functor examples
  ${link(FunctorsMapping_UserGuidePage).hide}
  ${link(FunctorFunctionLifting_UserGuidePage).hide}
  ${link(FunctorFunctionComposition_UserGuidePage).hide}
  ${link(FunctorMiscOperators_UserGuidePage).hide}

  ## Applicative examples

  ## Monoid examples
  ${link(Monoid_UserGuidePage).hide}
"""

}
