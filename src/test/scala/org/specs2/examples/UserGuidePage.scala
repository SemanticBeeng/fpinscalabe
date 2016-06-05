package org.specs2.examples

import org.specs2._
import org.specs2.form.Card
import org.specs2.specification.core.Fragments
import org.specs2.specification.{Forms, Snippets}

/**
 * base class for creating specs2 user guide pages.
 */
abstract class UserGuidePage extends Specification with UserGuideVariables with Snippets with Forms {
  override def map(fs: =>Fragments) = super.map(fs.compact)
}

abstract class UserGuideCard extends Card with UserGuideVariables with Forms
