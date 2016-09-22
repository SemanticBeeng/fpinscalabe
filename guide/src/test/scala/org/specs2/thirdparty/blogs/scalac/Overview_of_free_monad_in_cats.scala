package org.specs2.thirdparty.blogs.scalac

import org.fp.concepts._
import org.fp.resources._

import scala.language.higherKinds

//
import org.specs2.execute.Snippet._
import org.specs2.execute.SnippetParams
import org.specs2.ugbase.UserGuidePage

/**
  *
  * @see [[coProduct]]
  */

/**
  *
  */
object Overview_of_free_monad_in_cats extends UserGuidePage {
  def is = s"Overview of free monad in cats".title ^ s2"""

## Putting the Free monad to work

We gonna implement basic instructions from LOGO language - the famous “Turtle”.
We will implement movement in a 2 dimensional space.

### Creating set of instructions (AST)

Ok, let’s say we have some set of instructions that we want to use in order to create program.
Using the LOGO example I would like to implement basic functionality like moving forward and backward,
rotating left or right and showing the current position.
To do that I’ve created a few simple classes to represent those actions.

These are just simple case classes. Type A will be the type that Free Monad will be working on. That means if we use a flatMap we will have to provide a function of type A => Instruction[B].

${snippet{
    /**/
    // 8<--
    case class Position(x: Double, y: Double, heading: Degree)
    case class Degree(private val d: Int) {
      val value = d % 360
    }
    // 8<--

    object Logo {
      sealed trait Instruction[A]
      case class Forward(position: Position, length: Int) extends Instruction[Position]
      case class Backward(position: Position, length: Int) extends Instruction[Position]
      case class RotateLeft(position: Position, degree: Degree) extends Instruction[Position]
      case class RotateRight(position: Position, degree: Degree) extends Instruction[Position]
      case class ShowPosition(position: Position) extends Instruction[Unit]
    }
}}

There are also two classes that will be used. It’s a part of simplified domain model.

${snippet {
   /**/
   case class Position(x: Double, y: Double, heading: Degree)
   case class Degree(private val d: Int) {
     val value = d % 360
   }
}}

""".stripMargin

  implicit override def snippetParams[T]: SnippetParams[T] = defaultSnippetParameters[T].copy(evalCode = true).offsetIs(-4)
}
