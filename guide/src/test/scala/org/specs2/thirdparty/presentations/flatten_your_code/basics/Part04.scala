package org.specs2.thirdparty.presentations.flatten_your_code.basics

import org.fp.concepts._
import org.fp.resources._
import org.fp.bookmarks._

//
import org.specs2.common.SnippetHelper._
import org.specs2.ugbase.UserGuidePage
//
import org.fp.thirdparty.flatten_your_code.snippets.API04

/**
  *
  */
object Part04 extends UserGuidePage with API04 {

  def is = s"Flatten your code : basics, part 4".title ^ s2"""

In practice, not all of your functions will always return the same type.
Here, `getUser` and `validateEmail` now return `Option` instead of `\/`.

${incl[API04]}

In a $forComprehension, we need all the containers to be the same.

So we must think about a suitable container that can express everything that we need to express.
In this case, that would probably be `\/`. We can upgrade `Option` to `\/` by specifying a left side, for when the `Option` is a `None`.

This is done with the method `toRightDisjunction`:

${snippet{
/**/
    import scalaz.{ \/-, -\/ }
    import scalaz.syntax.std.option._

    Some(5).toRightDisjunction("Left side!")  must_==  \/-(5)
    None.toRightDisjunction("Left side!")     must_== -\/("Left side!")
  }}

There's a symbolic method for this as well: `\/>`

${snippet{
// 8<--
    import scalaz.{ \/, \/- }
    import scalaz.syntax.std.option._
// 8<--
    Some(5) \/> "Left side!"                  must_== \/-(5)
  }}

### Exercise

Write our usual program with a $forComprehension, using 'toRightDisjunction' or '\/>'

If you're entirely not interested in error messages, you can also decide to 'downgrade' the `\/` values to `Option`.
There's a 'toOption' method on `\/` for that.

### Bonus exercise

Write the program again, but now downgrading `\/` to `Option`.

    """
}
