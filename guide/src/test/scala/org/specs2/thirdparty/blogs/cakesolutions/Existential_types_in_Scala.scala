package org.specs2.thirdparty.blogs.cakesolutions

import org.fp.concepts._
import org.fp.bookmarks._
//
import org.specs2.ugbase.UserGuidePage
import org.fp.thirdparty.cakesolutions.Existential_types_in_Scala.snippets.{Snip01, Snip02, Snip03, Snip04, Snip05}
import org.specs2.common.SnippetHelper._
import org.specs2.execute.SnippetParams

import scala.language.existentials
import scala.language.higherKinds

/**
  * For [[org.fp.resources.cakeSolutions_ExistentialTypesInScala]]
  */
object Existential_types_in_Scala extends UserGuidePage {

def is = s"Existential types in Scala".title ^ s2"""

  ${ann_cakeSolutions_ExistentialTypesInScala_bkm1.md}

### Existential Types

Existential types ($existentialType) are like the normal $typeVariable-s we saw above, except the variable only shows up on the right:

${incl[Snip01]}

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

${incl[Snip02]}

Here we're using $pathDependentType in order to create a better interface for our `Existential` trait.
We could just have an empty trait but that would mean that we would need to case match `MkEx` whenever we wanted to access
its fields. However, the concept still holds and shares the properties we described above:

${snippet {
  import Snip02._

  val v1: Existential = MkEx("hello")

  val v2: Existential = MkEx(1: Int)

  case class User(name: String)
  val user = User("joe")
  val v3: Existential = MkEx(user)

}}

We could think of `MkEx` as a type eraser: it doesn't matter what type of data we choose to put into `MkEx`, it will erase the type and always return `Existential`.
Time for a game, lets say you have some function that, when called, returns an `Existential`. What is the type of the inner value field?

The answer is `ex.Inner` of course. But what is `ex.Inner`? The answer to that is that we can't know. The original type defined in `MkEx` has been erased from compilation,
never to be seen again. Now that's not to say that we have lost all information about it. We know that it exists (we have a reference to it via `ex.Inner`), hence why it is called "existential",
but sadly that's pretty much all the information we know about it.
This means that, in its current form, `Existential` and `MkEx` are useless.

We can't pass `ex.value` anywhere expect where `ex.Inner` is required, but even `ex.Inner` is pretty bare bones with no properties for us to use: so once we accept it in a function,
what do we do with it? Well nothing right now, but we could add restrictions to the type, which in terms would allow us to do something with it without knowing what it is.

*And this is where Existential types shine*: they have the interesting property of unifying different types into a single one with shared restrictions.
These restrictions could be anything: from upper bounds to type classes or even a combination.

To show this we will be creating a type-safe wrapper around an unsafe Java library.

Let's say you have a Java library that contains the following signature:

${snippet{
  trait Code {
    trait Statement
    def bind(objs: Object*): Statement
  }
}}

This signature isn't special, many SQL Java libraries have a method similar to it.
Normally you would define some string with many question marks (?) followed by a call to a `bind` method that would bind the
passed objects to the question marks (hopefully sanitizing the input in the process). Like so:

SELECT * FROM table WHERE a = ? AND b = ?;

The problem though is that `Object` is a very general thing. Obviously, if we pass `bind(user)`, where user is some class
we defined, that wouldn't work. However, it would compile and crash at runtime. Additionally, a lot of these Java libraries are,
well, for Java, so certain scala types won't work either (eg: BigInt, Int, List).

In fact, you could think of `Object` as an $existentialType.

The problem is that it is too wide, it encompasses ALL types.
This all means that we need to somehow "restrict" the number of types that are allowed to go into bind.
This new bind, we shall call it `safeBind` will:

 * Convert types to their Java counterpart (BigInt -> Long, Int -> Integer)
 * Fail to compile any nonsensical types (`User`, `Profile`)

We're not going to be making the length of parameters passed safe (that would require a lot more than $existentialType-s).
Nor are we checking that the types passed match the expected types in the SQL query.

`safeBind` will be defined something like so:

${snippet{
  trait Code {
    trait AnyAllowedType {
      def toObject: Any
    }
    trait Statement

    def bind(args: Any*): Statement

    def safeBind(columns: AnyAllowedType*): Statement =
      bind(columns.map(_.toObject): _*)
  }
}}

Note that `safeBind` doesn't look that much different that bind except for `AnyAllowedType`.
But before we can talk about `AnyAllowedType`, we need to define what types are allowed. For this we will use a $typeClass,
called `AllowedType`, since they lend themselves well for defining a set of types unrelated types that have a specific functionality.

This $typeClass is defined as:

${incl[Snip03]}

The stuff in the $companionObject are just helper functions. Now, lets define some types that will be allowed through:

${incl[Snip04]}

This gives us our filter and converter: we can only call `AllowedType[A].toObject(a)` if A implements our $typeClass.

Great.

Now we need to define `AnyAllowedType`.
As we saw above, we want `AnyAllowedType` to behave somewhat like `Object`, but for our small set of types.
We can achieve this using an $existentialType but with an evidence for our `AllowedType` $typeClass:

${incl[Snip05]}

This $existentialType is similar to the Existential we defined before, except that we are now asking for an evidence for `AllowedType[A]`
and capturing it as part of the trait.

$keyPoint This means that, unlike before, we can't pass any random type anymore:

${snippet{
  //8<--
  import Snip04.AllowedType._
  import Snip05._

  import java.time.Instant
  //8<--

  MkAnyAllowedType("Hello"): AnyAllowedType
  MkAnyAllowedType(1: Int): AnyAllowedType
  MkAnyAllowedType(Instant.now()): AnyAllowedType

  case class User(name: String)
  val user: User = User("joe")

  // won't compile since we don't have an AllowedType instance for User
  // MkAnyAllowedType(user): AnyAllowedType
}}

Now we have our `AnyAllowedType` defined. Let's see it in action by defining `safeBind`.

${snippet{

  //8<--
  trait Code {
    import Snip04.AllowedType._
    import Snip05._
    trait Statement
  //8<--

    def bind(args: Any*): Statement

    def safeBind(any: AnyAllowedType*): Statement =
      bind(any.map(ex => ex.evidence.toObject(ex.value)):_*)
  //8<--
  }
  //8<--
}}

Note that we still don't know what the type of `ex.value` is (just like before).
However, we do know that it is the same type as the evidence `ex.evidence`.
That means that all functions, that are part of the evidence (ie: $typeClass), match the type of `ex.value`!

$keyPoint So our knowledge of `ex.value` has expanded from, "all we know is that it exists" to "we know it exists AND that it implements the $typeClass `AllowedType`".

Finally, when we go to use safeBind, we do as such:
And it works!

${snippet{

  //8<--
  trait Code {
    import Snip04.AllowedType._
    import Snip05._
    trait Statement
    import java.time.Instant

    def bind(args: Any*): Statement

    def safeBind(any: AnyAllowedType*): Statement =
      bind(any.map(ex => ex.evidence.toObject(ex.value)):_*)
  //8<--

    safeBind(MkAnyAllowedType(1), MkAnyAllowedType("Hello"), MkAnyAllowedType(Instant.now()))

    // Does not compile, no instance of AllowedType for User
    // safeBind(MkAnyAllowedType(1), MkAnyAllowedType(user)) //
  //8<--
  }
  //8<--
}}

Now we are essentially done, we just need to wrap our values in `MkAnyAllowedType` and the compiler will do the rest (or yell).

However, there are some extra tweaks we can make to make our interface better.

##### Making an `AllowedType` instance for `AnyAllowedType`

You many have noticed that it is awkward to call functions in `ex.evidence`

  `ex.evidence.toObject(ex.value)`

We can make this better by creating an instance for `AllowedType`:

${snippet{
  //8<--
  trait Code {
    import Snip03._
    import Snip04.AllowedType._
    import Snip05._
  //8<--

    object AnyAllowedType {
      implicit val anyAllowedInst: AllowedType[AnyAllowedType] =
        AllowedType.instance(ex => ex.evidence.toJavaType(ex.value))
    }

    // Now we can simply do
    val ex: AnyAllowedType = MkAnyAllowedType("Hello")
      // @todo AllowedType[AnyAllowedType].toObject(ex)
  //8<--
  }
  //8<--
}}

##### Using $implicitConversion-s to avoid wrapping

Having to do this manual wrapping can become old fast:

  `safeBind(MkAnyAllowedType(1), MkAnyAllowedType("Hello"), …)`


We can actually avoid it by using an $implicitConversion:

${snippet{
  //8<--
  trait Code{
    import Snip03._
    import Snip04.AllowedType._
    import Snip05._
  //8<--
    object AnyAllowedType {
      implicit def anyAllowedToAny[A: AllowedType](a: A): AnyAllowedType =
        MkAnyAllowedType(a)
    }
  }
}}

Now we can simply call:

  `safeBind(1, "Hello", Instant.now(), true, …)`

And passing `user` will fail, like before.

##### Generalize AnyAllowedType

When we created `AnyAllowedType`, we made it for the $typeClass `AllowedType`.
Does this mean that we need to make a new `AnyX` for every X $typeClass we have?
Nope, we do not. We can generalize `AnyAllowedType` to work for ANY $typeClass.

This would require a simple modification:

${snippet{
  trait Code {
    // data TCBox tc = forall a. tc a => TCBox a
    sealed trait TCBox[TC[_]] {
      type T
      val value: T
      val evidence: TC[T]
    }

    private case class MkTCBox[A, TC[_]](value: A)(implicit val evidence: TC[A])
      extends TCBox[TC] {
      type T = A
    }
  }
}}

Now, instead of hard-coding it to `AllowedType`, we take the $typeClass as a `type TC[_]`.
We still take a `TC[A]` implicitly in `MkTCBox` along with the value. Note though that `TC[_]` isn't $existentialType, nor $phantomType,
it is just a common $typeVariable. A `TCBox[TC]` is, essentially, a "TypeClass Box" for the typeclass "TC".

Our $implicitConversion can also be translated:

${snippet{
  //8<--
  sealed trait TCBox[TC[_]] {
    type T
    val value: T
    val evidence: TC[T]
  }

  case class MkTCBox[A, TC[_]](value: A)(implicit val evidence: TC[A])
    extends TCBox[TC] {
    type T = A
  }
  //8<--

  object TCBox {

    def apply[T, TC[_]](value: T)(implicit ev: TC[T]): TCBox[TC] =
      MkTCBox(value)

    /**
     * Allows for case matching with evidence
     */
    def unapply[TC[_]](t: TCBox[TC]): Option[(t.T, TC[t.T])] =
      Some(t.value -> t.evidence)

    // If `A` implements the type class `TC`, then `A` can be wrapped by
    // `TCBox[TC]` if it is expected. This allows for automatic lifting of
    // types into TCBox.
    implicit def anyToBox[TC[_], A: TC](v: A): TCBox[TC] = TCBox(v)
  }

  //8<--
    import Snip03._
    import Snip04.AllowedType._
    import Snip05._
  //8<--

  object Example {
    def bind(objs: Object*): Unit = ()

    type AnyAllowedType = TCBox[AllowedType]
    // @todo
//    def toObj(t: AnyAllowedType) = AllowedType[AnyAllowedType].toObject(t)
//
//    def bindT(objs: AnyAllowedType*): Unit =
//      bind(objs.map(toObj):_*)
//
//    def f = bindT(123, "Hello", true)
  }
}}

Finally, a simple $typeAlias type `AnyAllowedType = TCBox[AllowedType]` would make everything we have written before keep working.

##### Conclusion

$existentialType-s don't seem that useful when first encountered, but they can be quite powerful when mixed with the correct restrictions.

The use case we presented is one of the simpler uses but they can be as complex or as simple as your use case requires them to be.

S: you can find all the code we just wrote for TCBox here: https://gist.github.com/pjrt/269ddd1d8036374c648dbf6d52fb388f
"""
}
