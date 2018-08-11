package org.specs2.thirdparty.blogs.stackoverflow

import org.fp.concepts._
import org.fp.resources._
import org.fp.bookmarks._
import org.specs2.specification.dsl.mutable.{AutoExamples, TextDsl}

import scala.language.higherKinds

//
import shapeless._
import shapeless.poly._
import shapeless.record._
import shapeless.ops.record._
import shapeless.ops.hlist.{Mapper,ToTraversable}
import shapeless.tag._

/**
  *
  * @see [[typeAlias]], [[implicitConversion]]
  */

/**
  * #resource [[caseClassAttributesWithShapeless]]
  */
class CaseClassAttributesWithShapeless extends org.specs2.mutable.Spec with AutoExamples with TextDsl {

  "A Stack with limited capacity can either be:".p

  eg {
    final case class Message(id: Int, title: String, body: String)

    trait ToAttributes[T] {
     def toAttributes(v: T): Seq[String]
    }

    object Attributes {
     object symbolName extends Poly1 {
       implicit def atTaggedSymbol[T] = at[Symbol with Tagged[T]](_.name)
     }

     implicit def familyFormat[T, Repr <: HList, KeysRepr <: HList, MapperRepr <: HList]
     (
        implicit gen: LabelledGeneric.Aux[T, Repr],
        keys: Keys.Aux[Repr, KeysRepr],
        mapper: Mapper.Aux[symbolName.type, KeysRepr, MapperRepr],
        traversable: ToTraversable.Aux[MapperRepr, List, String]
      ): ToAttributes[T] =
       new ToAttributes[T] {
         def toAttributes(v: T): Seq[String] = keys().map(symbolName).toList.toSeq
       }

     def toAttributes[T](v: T)(implicit c: ToAttributes[T]): Seq[String] = c.toAttributes(v)
    }

    import Attributes._
    val message = Message(10, "foo", "bar")
    val attributes = toAttributes(message)

    attributes must_== List("id", "title", "bodyss")
  }
}
