package org.specs2.ugbase

import org.specs2.guide.arrow.Arrow_UserGuidePage
import org.specs2.guide.applicative.Applicative_UserGuidePage
import org.specs2.guide.apply.Apply_UserGuidePage
import org.specs2.guide.functor._
import org.specs2.guide.kleisli.Kleisli_UserGuidePage
import org.specs2.guide.monoid.Monoid_UserGuidePage
import org.specs2.guide.traverse.Traverse_UserGuidePage
import org.specs2.guide.typeclass.{Variance_UserGuidePage, TypeTagging_UserGuidePage, TypeclassDefinition_UserGuidePage}

//import org.specs2.execute.SnippetParams
//
import org.specs2.thirdparty._

object UserGuide extends UserGuidePage {

  //implicit val snippetParams = SnippetParams(evalCode = true)
  def is = "Functional Programming in Scala - by Example".title ^ s2"""

$specs2 is a library for writing executable software specifications in Scala.
In this user guide, you will find:

## Typeclass examples

 * ${link(TypeclassDefinition_UserGuidePage)}
 * ${link(TypeTagging_UserGuidePage)}
 * ${link(Variance_UserGuidePage)}

## Functor examples

 * ${link(FunctorsMapping_UserGuidePage)}
 * ${link(FunctorFunctionLifting_UserGuidePage)}
 * ${link(FunctorFunctionComposition_UserGuidePage)}
 * ${link(FunctorMiscOperators_UserGuidePage)}
 * ${link(FunctorsLaws_UserGuidePage)}

## Apply examples

 * ${link(Apply_UserGuidePage)}

## Applicative examples

 * ${link(Applicative_UserGuidePage)}

## Arrow examples

 * ${link(Arrow_UserGuidePage)}

## Monoid examples

 * ${link(Monoid_UserGuidePage)}

## Traverse examples

 * ${link(Traverse_UserGuidePage)}

## Kleisli examples

 * ${link(Kleisli_UserGuidePage)}

## Monad examples
 * tbd

## Blogs
 * ${link(blogs.herdingcats.Checking_laws_with_Discipline)}
 * ${link(blogs.scalac.Overview_of_free_monad_in_cats)}

## Presentations
 * ${link(presentations.flatten_your_code.basics.Part01)}
 * ${link(presentations.flatten_your_code.basics.Part02)}
 * ${link(presentations.flatten_your_code.basics.Part03)}
 * ${link(presentations.flatten_your_code.basics.Part04)}
 * ${link(presentations.flatten_your_code.basics.Part05)}
 * ${link(presentations.flatten_your_code.basics.Part06)}
 * ${link(presentations.flatten_your_code.basics.Part07)}
 * ${link(presentations.flatten_your_code.basics.Part08)}
 * ${link(presentations.flatten_your_code.basics.Part09)}
 * ${link(presentations.flatten_your_code.basics.Part10)}
 * ${link(presentations.flatten_your_code.basics.Part11)}
 * ${link(presentations.flatten_your_code.basics.Part12)}
 * ${link(presentations.flatten_your_code.basics.Part13)}
 * ${link(presentations.flatten_your_code.basics.Part14)}

 * ${link(org.fp.studies.fp.DisjunctionSpec)}
 * ${link(org.fp.studies.fp.ValidationSpec)}
"""

}
