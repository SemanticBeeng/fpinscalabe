package org.specs2.ugbase

import org.specs2._
import org.specs2.execute.{Snippet, SnippetParams}
import org.specs2.form.Card
import org.specs2.io.{FileSystem, FilePath}
import org.specs2.specification.core.Fragments
import org.specs2.specification.dsl.mutable.TextDsl
import org.specs2.specification.{Forms, Snippets}

import scala.reflect.ClassTag

/**
 * base class for creating specs2 user guide pages.
 */
abstract class UserGuidePage extends Specification with UserGuideVariables with Snippets with Forms /*with TextDsl */{

  implicit def snippetParams[T]: SnippetParams[T] = defaultSnippetParameters[T].copy(evalCode = true)

  override def map(fs: =>Fragments) = super.map(fs.compact)
}

abstract class UserGuideCard extends Card with UserGuideVariables with Forms

