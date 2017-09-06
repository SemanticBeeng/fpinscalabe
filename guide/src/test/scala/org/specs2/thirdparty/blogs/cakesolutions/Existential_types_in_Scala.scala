package org.specs2.thirdparty.blogs.cakesolutions

import org.fp.concepts._
import org.fp.bookmarks._

import org.specs2.ugbase.UserGuidePage
import org.fp.thirdparty.cakesolutions.Existential_types_in_Scala.snippets.Snip01

import org.specs2.common.SnippetHelper._
import org.specs2.execute.SnippetParams

import scala.language.existentials

/**
  * For [[org.fp.resources.cakeSolutions_ExistentialTypesInScala]]
  */
object Existential_types_in_Scala extends UserGuidePage {

def is = s"Existential types in Scala".title ^ s2"""

  ${ann_cakeSolutions_ExistentialTypesInScala_bkm1.md}

### Existential Types

Existential types ($existentialType) are like the normal $typeVariable-s we saw above, except the variable only shows up on the right:

${incl[Snip01.type]}

Now `A` only appears on the right side, which means that the final type, `F`, will not change regardless of what `A` is.
For example:

${snippet{
  import Snip01._

  val v1: FE = SomeClass("hello")
  val v2: FE = SomeClass(1: Int)

  case class User(name: String)
  val user = User("joe")
  val v3: FE = SomeClass(user)
}}

Side note: if A were to only appear on the left side, then A would be a $phantomType.
We aren't covering those in this article though, so let's ignore them.

Like before, let's now expand it. Beware, this expansion isn't as clean as the previous one:

${snippet{
  sealed trait Existential {
    type Inner
    val value: Inner
  }

  final case class MkEx[A](value: A) extends Existential { type Inner = A }
}}

Here we're using $pathDependentType in order to create a better interface for our `Existential` trait.
We could just have an empty trait but that would mean that we would need to case match `MkEx` whenever we wanted to access
its fields. However, the concept still holds and shares the properties we described above:

  """
}
