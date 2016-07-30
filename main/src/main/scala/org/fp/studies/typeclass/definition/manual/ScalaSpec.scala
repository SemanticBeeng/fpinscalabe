package org.fp.studies.typeclass.definition.manual

import org.fp.bookmarks._
import org.fp.concepts._
import org.fp.studies.typeclass.definition.manual

//
import org.specs2.common.SnippetHelper._
import org.specs2.specification.Snippets


/**
  * @see [[org.fp.resources.Scala]] [[org.fp.resources.Scalaz]]
  * @see [[ann_makeYourOwnTypeClasses]]
  */
object ScalaSpec extends org.specs2.Specification with Snippets {

  def is = s"Defining $typeClass-es manually".title ^ s2"""

### Boiler plate code to define the $typeClass related stuff manually

   ${incl[manual.Definitions]}

### And now usages to exemplify
   ${incl[manual.Usages]}
      """
}
