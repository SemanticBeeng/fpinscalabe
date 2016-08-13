package org.specs2.ugbase

import org.specs2._
import org.specs2.execute._
import org.specs2.specification.core.Fragments
import org.specs2.matcher.MatchResult
import org.specs2.specification.{Forms, Snippets}
import org.specs2.form.Card
//
import org.scalacheck.Properties

/**
 * base class for creating specs2 user guide pages.
 */
abstract class UserGuidePage extends Specification with UserGuideVariables with Snippets with Forms with ScalaCheck {

  implicit def snippetParams[T]: SnippetParams[T] = defaultSnippetParameters[T].copy(evalCode = true)

  override def map(fs: =>Fragments) = super.map(fs.compact)

  def check(properties: Properties): Result = {
    properties.properties.foldLeft(Success(): Result) { case (result, (name, p)) =>
      val r = AsResult(p :| name)
      val message = s"${result.message}|+ $name: $r\n  "

      if (r.isSuccess) Success(message)
      else             Failure(message)
    }.mapMessage(_.trim)
  }

  def check[T](m: MatchResult[T]): Result =
    AsResult(m)

  def run(specification: Specification) =
    "\n" + org.specs2.runner.TextRunner.run(specification).output
}

abstract class UserGuideCard extends Card with UserGuideVariables with Forms

