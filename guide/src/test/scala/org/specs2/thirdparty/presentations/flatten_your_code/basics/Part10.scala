package org.specs2.thirdparty.presentations.flatten_your_code.basics

import org.fp.concepts._
//
import org.specs2.ugbase.UserGuidePage
import org.specs2.common.SnippetHelper._
import org.fp.thirdparty.flatten_your_code.snippets.API07_2

/**
  *
  */
object Part10 extends UserGuidePage with API07_2  {

  def is = s"Flatten your code : basics, part 10".title ^ s2"""

Here's `FutureOption` again:

${incl[API07_2]}

Next ${link(Part08)}
  """
}
