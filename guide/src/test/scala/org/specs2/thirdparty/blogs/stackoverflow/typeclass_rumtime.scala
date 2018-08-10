package org.specs2.thirdparty.blogs.stackoverflow

import org.fp.concepts._
import org.fp.resources._
import org.fp.bookmarks._

import scala.language.higherKinds

//
import org.specs2.ugbase.UserGuidePage
import shapeless._
import shapeless.poly._
import shapeless.record._
import shapeless.ops.record._
import shapeless.ops.hlist.{Mapper,ToTraversable}
import shapeless.tag._


final case class Message(id: Int, title: String, body: String)


/**
  *
  * @see [[typeAlias]], [[implicitConversion]]
  */

/**
  * #resource [[caseClassAttributesWithShapeless]]
  */
object typeclass_rumtime extends UserGuidePage {

  def is = s"How to get type class at runtime".title ^ s2"""

${snippet{

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

}}
"""
}
