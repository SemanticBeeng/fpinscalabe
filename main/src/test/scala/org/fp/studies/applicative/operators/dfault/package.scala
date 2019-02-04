package org.fp.studies.applicative.operators

import org.fp.concepts._
import org.fp.resources._
import org.fp.bookmarks._

import scala.language.higherKinds

//
import org.specs2.specification.dsl.mutable.{TextDsl, AutoExamples}

/**
  *
  */
package object dfault {

  object Spec extends org.specs2.mutable.Spec with AutoExamples with TextDsl {

    s"$keyPoint $applyFunctor-s $operator_compose"

    s"$bookmarks: ..."

    eg { /** in [[Scalaz]] */

      import scalaz.std.list._
      import scalaz.std.option._
      import scalaz.syntax.applicative._

      //@todo
      success
    }

    eg { /** in [[Cats]] */

      import cats.instances.list._
      import cats.instances.option._
      import cats.syntax.applicative._

      //@todo
      success
    }

    eg { /** in [[Cats]] */
      /**
        * #hypis https://hyp.is/Beb4XihFEemvIjvPB2-mig/typelevel.org/cats/typeclasses/applicative.html
        */

      import cats.data.Nested
      import cats.Applicative
      import cats.implicits._
      import scala.concurrent.Future
      import scala.reflect.ClassTag

      import scala.concurrent.ExecutionContext.Implicits.global

      val x: Future[Option[Int]] = Future.successful(Some(5))
      val y: Future[Option[Char]] = Future.successful(Some('a'))

      val composed = Applicative[Future].compose[Option].map2(x, y)(_ + _)
      // composed: scala.concurrent.Future[Option[Int]] = Future(<not completed>)

      composed must beAnInstanceOf(implicitly[ClassTag[Future[Option[Int]]]])

      val nested = Applicative[Nested[Future, Option, ?]].map2(Nested(x), Nested(y))(_ + _)
      // nested: cats.data.Nested[scala.concurrent.Future,Option,Int] = Nested(Future(<not completed>))
      nested must beAnInstanceOf(implicitly[ClassTag[Nested[Future, Option, Int]]]) //#todo use '?'

      success
    }
  }
}
