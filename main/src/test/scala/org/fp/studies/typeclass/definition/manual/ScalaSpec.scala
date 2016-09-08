package org.fp.studies.typeclass.definition.manual

import org.fp.bookmarks._
import org.fp.concepts._
import org.fp.resources._
import org.specs2.ScalaCheck
import org.specs2.common.CheckedSpec
import org.specs2.specification.dsl.mutable.TextDsl

//
import org.fp.studies.typeclass.definition.manual
import org.specs2.common.SnippetHelper._
import org.specs2.specification.Snippets


/**
  * @see [[org.fp.resources.Scala]] [[org.fp.resources.Scalaz]]
  * @see [[ann_makeYourOwnTypeClasses]]
  */
object ScalaSpec extends org.specs2.Specification with Snippets with ScalaCheck with CheckedSpec {

  def is = s2"""Defining $typeClass-es manually

### Boiler plate code to define the $typeClass related stuff manually

   ${incl[manual.Definitions]}

### And now usages to exemplify

  Here’s how we can define $typeClassInstance-s for `Int`:
  ${snippet{
    // 8<--
    import Definitions._
    // 8<--

    implicit val intCanTruthy: CanTruthy[Int] = CanTruthy.fromTruthy({
      case 0 => false
      case _ => true
    })

    import CanTruthy.ops._
    10.truthy must_== false

  }.offsetIs(-4)}

  Next is for `List[A]`:
  ${snippet{
    // 8<--
    import Definitions._
    // 8<--

    implicit def listCanTruthy[A]: CanTruthy[List[A]] = CanTruthy.fromTruthy({
      case Nil => false
      case _ => true
    })

    import CanTruthy.ops._
    List("foo").truthy must_== true
  }.offsetIs(-4)}

  It looks like we need to treat `Nil` specially because of the nonvariance.
  ${snippet {
    // 8<--
    import Definitions._
    // 8<--

    implicit val nilCanTruthy: CanTruthy[scala.collection.immutable.Nil.type] = CanTruthy.fromTruthy(_ => false)

    import CanTruthy.ops._
    check(Nil.truthy must_== 2)

  }.offsetIs(-4)}

  And for `Boolean` using identity:
  ${snippet{
    // 8<--
    import Definitions._
    // 8<--

    implicit val booleanCanTruthy: CanTruthy[Boolean] = CanTruthy.fromTruthy(identity)

    import CanTruthy.ops._
    false.truthy must_== false

  }.offsetIs(-4)}

  Using `CanTruthy` $typeClass, let’s define `truthyIf` like LYAHFGG
  <i>Now let’s make a function that mimics the if statement, but that works with YesNo values.</i>
  To delay the evaluation of the passed arguments, we can use pass-by-name:
  ${snippet{
    // 8<--
    import Definitions._
    // 8<--

    import CanTruthy.ops._
    def truthyIf[A: CanTruthy, B, C](cond: A)(ifyes: => B)(ifno: => C) =
      if (cond.truthy) ifyes
      else ifno

    //@todo "Duplicate but good to see what needs to be in scope"

    implicit def listCanTruthy[A]: CanTruthy[List[A]] = CanTruthy.fromTruthy({
      case Nil => false
      case _ => true
    })

    //@todo Duplicate but good to see what needs to be in scope
    implicit val booleanCanTruthy: CanTruthy[Boolean] = CanTruthy.fromTruthy(identity)

    truthyIf(Nil: List[String]) { "YEAH!" } { "NO!" } must_== "NO!"
    truthyIf(2 :: 3 :: 4 :: Nil) { "YEAH!" } { "NO!" } must_== "YEAH!"
    truthyIf(true) { "YEAH!" } { "NO!" } must_== "YEAH!"

  }.offsetIs(-4)}
  """.stripMargin
}
