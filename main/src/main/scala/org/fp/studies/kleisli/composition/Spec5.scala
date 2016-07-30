// 8<---
package org.fp.studies.kleisli.composition

import org.fp.bookmarks._
import org.fp.concepts._
import org.fp.resources._

trait Spec5

/**
  *
  */
object Spec5 extends org.specs2.mutable.Specification with Spec5 {

  // 8<---
  case class Make(id: Int, name: String)

  case class Part(id: Int, name: String)

  import scalaz.NonEmptyList

  val part1 = Part(1, "Gear Box")
  val part2 = Part(2, "Clutch cable")

  /**
    *
    */
  object SomeFunctions1 {

    val make: (Int) => Make = (_) => Make(1, "Suzuki")

    val parts: Make => List[Part] = {
      case Make(1, _) => List(part1, part2)
    }
  }

  override def is = s2"""
     $keyPoint Today I’ll be exploring a few different ways in which you can compose programs.
     The examples that follow all deal with Vehicles - more specifically makes and parts:."

     $bookmarks ${ann_FunctionComposition2.is}

     So we have a function Int =>  Make and then a function : Make => List[Part].
     From set theory we know this implies we must have a function from Int => List[Part].
     This is nothing more than simple $functionComposition: ${eg { /** [[Scala]] */
    import SomeFunctions1._

    val f = parts compose make
    f(1) must_== List(part1, part2)
  }}

    Alternatively you can use $operator_andThen which works like $operator_compose, but with the arguments flipped: ${
    import SomeFunctions1._

    val g = make andThen parts
    g(1) must_== List(part1, part2)
  }

    Now we have a function make: Int => Option[Make] and a function parts: Make => Option[NonEmptyList[Part]].
    Based on our first example we should have a way to create a function from Int to Option[NonEmptyList[Part]].
    This isn’t immediately obvious however.
    """
  // 8<---
}