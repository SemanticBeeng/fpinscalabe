package org.fp.studies.typeclass

import org.fp.bookmarks._
import org.fp.concepts._
import org.specs2.specification.Snippets

//
import org.fp.studies.typeclass.definition._
import org.specs2.common.SnippetHelper.incl

/**
  *
  */
package object definition {

  /**
    * @see [[org.fp.resources.Scala]] [[org.fp.resources.Scalaz]]
    * @see [[ann_makeYourOwnTypeClasses]]
    */
  object ScalaSpec extends org.specs2.Specification with Snippets {

    def is = s"Defining $typeClass-es manually".title ^ s"""

### Boiler plate code to define the $typeClass related stuff manually

   ${incl[manual.Definitions]}

### And now usages to exemplify
   ${incl[manual.Usages]}
      """
  }

  /**
    * @see [[org.fp.resources.Simulacrum]]
    */
  object SimulacrumSpec extends org.specs2.Specification with Snippets {

    def is = s"Defining $typeClass-es automatically".title ^ s"""

### The conventional steps of defining a modular $typeClass in Scala used to look like:

 * Define $typeClass contract trait Foo.
 * Define a $companionObject Foo with a helper method apply that acts like implicitly, and a way of defining Foo instances typically from a function.
 * Define FooOps class that defines unary or binary operators.
 * Define FooSyntax trait that implicitly provides FooOps from a Foo instance.


 Frankly, these steps are mostly copy-paste boilerplate except for the first one. Enter Michael Pilquist (@mpilquist)’s simulacrum. simulacrum
 magically generates most of steps 2-4 just by putting @typeclass annotation. By chance, Stew O’Connor (@stewoconnor/@stew)’s #294 got merged,
 which refactors Cats to use it.

 ${incl[auto.Definitions]}

 And now usages to exemplify

 ${auto.Usages.is}
      """
  }

}

