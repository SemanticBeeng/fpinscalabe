package org.specs2.thirdparty.blogs.scalac

import org.fp.concepts._
import org.fp.resources._
import org.fp.bookmarks._

import scala.language.higherKinds

//
import org.specs2.ugbase.UserGuidePage
import org.specs2.common.SnippetHelper._
import org.specs2.execute.SnippetParams

import org.fp.thirdparty.scalac.Overview_of_free_monads_in_cats.snippets.{API01, API02}

/**
  *
  * @see [[freeMonad]], [[coProduct]]
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

These are just simple case classes. Type A will be the type that $freeMonad will be working on.
That means if we use a $operator_flatMap we will have to provide a function of type `A => Instruction[B]`.

${incl[API01]}

There are also two classes that will be used. It’s a part of simplified domain model.

${snippet {
   /**/
   case class Position(x: Double, y: Double, heading: Degree)
   case class Degree(private val d: Int) {
     val value = d % 360
   }
}}

At this point we have defined only our actions with some additional types, but it does not compute anything, it’s abstract.

## Creating the DSL

DSL is a Domain Specific Language. It contains functions that refer to the domain. Such functions make the code more readable
and expressive. To make it easier to use I’ve created some helper methods that will create $freeMonad-s wrapping our actions,
by lifting `Instruction[A]` into `Free[Instruction, A]`.

${incl[API02]}

These methods are used to write the application and make it stateless by taking a position as a parameter and not storing it inside the function.

## The program

Now we can easily use our DSL. Having the methods in place we can use them in a $forComprehension to build a program description.
Let’s say we want to get from `point (0,0)` to `point (10,10)`. The code looks like this:

${snippet{
    /**/
    // 8<--
    import API02._
    import API02.Logo._
    import org.specs2.matcher.{EitherBaseMatchers => _, _}

    // 8<--
    import cats.free.Free

    val program: (Position => Free[Instruction, Position]) = {
      start: Position =>
        for {
          p1 <- forward(start, 10)
          p2 <- API02.right(p1, Degree(90))
          p3 <- forward(p2, 10)
        } yield p3
    }
}}

""".stripMargin

  implicit override def snippetParams[T]: SnippetParams[T] = defaultSnippetParameters[T].copy(evalCode = true).offsetIs(-4)
}
