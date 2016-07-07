package org.fp.studies.functor.operators

import org.fp.concepts._
import org.fp.resources._
import org.fp.bookmarks._
//
import org.specs2.specification.dsl.mutable.{TextDsl, AutoExamples}

  /**
    *
    * @see [[operatorVoid]], [[operatorFproduct]]
    */
package object misc {

  /**
    * @see [[Scalaz]]
    */
  object Spec extends org.specs2.mutable.Spec with AutoExamples with TextDsl {

    s"$keyPoint The $operatorFproduct pairs a value with the result of applying a function to that value.".p
    s"$bookmarks: $ann_FProductEx1, $ann_FProductEx2"

    eg { /** in [[Scalaz]] */
      // 8<--
      import scalaz.std.list._
      import scalaz.syntax.functor._
      // 8<--
      val len: String => Int = _.length

      List("a",      "aa",      "b",       "ccccc").fproduct(len).toMap must_==
       Map("a" -> 1, "aa" -> 2, "b" ->  1, "ccccc" -> 5)
    }

    eg { /** in [[Cats]] */
      // 8<--
      import cats.std.list._
      import cats.syntax.functor._
      // 8<--
      val len: String => Int = _.length

      List("a",       "aa",      "b",       "ccccc").fproduct(len).toMap must_==
        Map("a" -> 1, "aa" -> 2, "b" ->  1, "ccccc" -> 5)
    }

    s"$keyPoint The $operatorVoid transforms F[A] into a F[Unit].".p
    eg { /** in [[Scalaz]] */
      // 8<--
      import scalaz.Functor
      import scalaz.std.option._
      // 8<--

      Functor[Option].void(Some(1)) must_== Some(())
    }

    eg { /** in [[Cats]] */
      // 8<--
      import cats.Functor
      import cats.std.option._
      // 8<--

      Functor[Option].void(Some(1)) must_== Some(())
    }

    s"$keyPoint The $operatorAs transforms F[A] into a F[B].".p
    eg { /** in [[Scalaz]] */
      import scalaz.std.list._
      import scalaz.syntax.functor._
      // 8<--

      List("a", "b", "c").as("-") must_== List("-", "-", "-")
    }

    s"$keyPoint The $operatorAs transforms F[A] into a F[B].".p
    eg { /** in [[Cats]] */
      import cats.std.list._
      import cats.syntax.functor._
      // 8<--

      List("a", "b", "c").as("-") must_== List("-", "-", "-")
    }
  }
}
