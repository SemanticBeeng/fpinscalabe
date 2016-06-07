package org.specs2.typeclass

import org.specs2.common.SnippetHelper.incl
import org.fp.bookmarks._

/**
  *
  */
package object definition {

  /**
    * @see [[org.fp.resources.Scala]] [[org.fp.resources.Scalaz]]
    * @see [[ann_makeYourOwnTypeClasses]]
    */
  object ScalaSpec extends org.specs2.Specification /*with Snippets*/ {

    def is = "Defining typeclasses manually".title ^ s"""

       Boiler plate code to define the type class related stuff manually

       ${incl[manual.Definitions]}

       And now usages to exemplify
       ${manual.Usages.is} //@todo this does not work ++++++++++++++++++++++++++++
      """
  }

  /**
    * @see [[org.fp.resources.Simulacrum]]
    */
  object SimulacrumSpec extends org.specs2.Specification {

    def is = "Defining typeclasses automatically".title ^ s"""

      ## The conventional steps of defining a modular typeclass in Scala used to look like:
       * Define typeclass contract trait Foo.
       * Define a companion object Foo with a helper method apply that acts like implcitly, and a way of defining Foo instances typically from a function.
       * Define FooOps class that defines unibary or binary operators.
       * Define FooSyntax trait that implicitly provides FooOps from a Foo instance.

       Frankly, these steps are mostly copy-paste boilerplate except for the first one. Enter Michael Pilquist (@mpilquist)’s simulacrum. simulacrum
       magically generates most of steps 2-4 just by putting @typeclass annotation. By chance, Stew O’Connor (@stewoconnor/@stew)’s #294 got merged,
       which refactors Cats to use it.

       ${incl[auto.Definitions]}

       And now usages to exemplify

       ${auto.Usages.is} //@todo this does not work ++++++++++++++++++++++++++++
      """
  }

}

