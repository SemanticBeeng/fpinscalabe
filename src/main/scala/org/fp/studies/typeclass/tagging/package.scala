package org.fp.studies.typeclass

import org.fp.concepts._
import org.fp.resources._
import org.fp.bookmarks._
//
import org.specs2.specification.dsl.mutable.{AutoExamples, TextDsl}

/**
  * @see [[taggedType]]
  */
package object tagging {


    /**
      * @see [[Scalaz]]
      * @see [[ann_newType]]
      */
    object ScalazSpec extends org.specs2.mutable.Spec with TextDsl with AutoExamples {

      s"$keyPoint ...:".p
      eg {
        type Tagged[U] = { type Tag = U }
        type @@[T, U] = T with Tagged[U]

//        case class KiloGram(value: Double)
//        import scalaz._
//        import scalaz.Tag
//        import scalaz.Tags._
//
//        sealed trait KiloGram
//        def KiloGram[A](a: A): A @@ KiloGram = Tag[A, KiloGram](a)
//        val mass = KiloGram(20.0)

        success
      }
    }

    /**
      * @see [[Cats]]
      */
    object CatsSpec extends org.specs2.mutable.Spec with TextDsl with AutoExamples {

      s"$keyPoint ... ".p
      eg {
        success
      }
    }
}
