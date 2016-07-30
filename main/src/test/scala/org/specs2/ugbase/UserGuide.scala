package org.specs2.ugbase

import org.specs2.arrow.Arrow_UserGuidePage
import org.specs2.guide.applicative.Applicative_UserGuidePage
import org.specs2.guide.apply.Apply_UserGuidePage
import org.specs2.guide.functor._
import org.specs2.guide.kleisli.Kleisli_UserGuidePage
import org.specs2.guide.monoid.Monoid_UserGuidePage
import org.specs2.guide.traverse.Traverse_UserGuidePage
import org.specs2.guide.typeclass.{TypeTagging_UserGuidePage, TypeclassDefinition_UserGuidePage}

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
    ${link(FunctorsLaws_UserGuidePage).hide}

    ## Apply examples

    ${link(Apply_UserGuidePage).hide}

    ## Applicative examples

    ${link(Applicative_UserGuidePage).hide}

    ## Arrow examples

    ${link(Arrow_UserGuidePage).hide}

    ## Monoid examples

    ${link(Monoid_UserGuidePage).hide}

    ## Traverse examples

    ${link(Traverse_UserGuidePage).hide}

    ## Kleisli examples

    ${link(Kleisli_UserGuidePage).hide}

    ## Monad examples
    ## link(Monad_UserGuidePage).hide

    """

}
