package org.specs2.thirdparty.presentations.flatten_your_code.basics

import org.fp.concepts._
//
import org.specs2.common.SnippetHelper._
import org.specs2.specification.core.Env
import org.specs2.ugbase.UserGuidePage
//
import org.fp.thirdparty.flatten_your_code.snippets.API07

/**
  *
  */
object Part07 extends UserGuidePage with API07 {

  implicit lazy val ee = Env().executionEnv
  implicit lazy val ec = ee.ec

  def is = s"Flatten your code : basics, part 7".title ^ s2"""

So we're dealing with `Future[Option[A]]` values. And we want to use them in $forComprehension.

We can't make the $forComprehension desugar differently.

So what we need is a `flatMap` operation, that treats these two containers as a single container, and that maps the inner value.

Let's make that! We'll create a new data structure that can get the A value out of a `Future[Option[A]]`, apply a function to it, and put it back.

### Exercise: Finish `FutureOption`

${incl[API07]}

So we can use it like this:

${snippet{
/**/
    // 8<--
    import scala.concurrent.Future
    import Code07._
    // 8<--

    val multiBoxedA = Future(Option(5))
    val multiBoxedB = Future(Option(3))

    // Here, `a` and `b` are Int!
    val result = for {
      a <- FutureOption(multiBoxedA)
      b <- FutureOption(multiBoxedB)
    } yield a + b

    // Then at the end, get the regular structure out of our FutureOption class
    val finalFuture: Future[Option[Int]] = result.contents
  }}

### Solution

${snippet{
/**/
    import scala.concurrent.Future

    case class FutureOption[A](contents: Future[Option[A]]) {
      def flatMap[B](fn: A => FutureOption[B]) = FutureOption {
        contents.flatMap {
          case Some(value) => fn(value).contents
          case None => Future.successful(None)
        }
      }

      def map[B](fn: A => B) = FutureOption {
        contents.map { option =>
          option.map(fn)
        }
      }
    }

    val multiBoxedA = Future(Option(5))
    val multiBoxedB = Future(Option(3))

    // Here, `a` and `b` are Int!
    val result = for {
      a <- FutureOption(multiBoxedA)
      b <- FutureOption(multiBoxedB)
    } yield a + b

    // Then at the end, get the regular structure out of our FutureOption class
    val finalFuture: Future[Option[Int]] = result.contents

    check(finalFuture must be_==(Some(8)).await)

  }}

Next ${link(Part06)}
  """
}
