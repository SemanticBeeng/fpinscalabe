package org.fp.thirdparty.cakesolutions.Existential_types_in_Scala.snippets

  //8<--
trait Snip05 {

  import Snip03._
  //8<--

  sealed trait AnyAllowedType {
    type A
    val value: A
    val evidence: AllowedType[A]
  }

  final case class MkAnyAllowedType[A0](value: A0)(implicit val evidence: AllowedType[A0])
    extends AnyAllowedType { type A = A0 }
  //8<--
}

object Snip05 extends Snip05
  //8<--
