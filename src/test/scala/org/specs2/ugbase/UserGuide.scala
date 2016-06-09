package org.specs2.ugbase

import org.specs2.examples.functor.{FunctorMiscOperators, FunctorFunctionComposition, FunctorFunctionLifting, FunctorsMapping}
import org.specs2.examples.monoid.MonoidAppend
import org.specs2.examples.typeclass.{TypeTagging, TypeclassDefinition}

//import org.specs2.execute.SnippetParams

object UserGuide extends UserGuidePage {

  //implicit val snippetParams = SnippetParams(evalCode = true)
  def is = "Functional Programming in Scala - by Example".title ^ s2"""

 $specs2 is a library for writing executable software specifications in Scala.
  In this user guide, you will find:

  ## Typeclass examples

  ${link(TypeclassDefinition).hide}
  ${link(TypeTagging).hide}

  ## Functor examples
  ${link(FunctorsMapping).hide}
  ${link(FunctorFunctionLifting).hide}
  ${link(FunctorFunctionComposition).hide}
  ${link(FunctorMiscOperators).hide}

  ## Applicative examples

  ## Monoid examples
  ${link(MonoidAppend).hide}
"""

}
