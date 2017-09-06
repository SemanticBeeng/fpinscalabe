package org.fp.thirdparty.cakesolutions.Existential_types_in_Scala.snippets

  //8<--
trait Snip02 {
  //8<--

  sealed trait Existential {
    type Inner
    val value: Inner
  }

  final case class MkEx[A](value: A) extends Existential { type Inner = A }
  //8<--
}
object Snip02 extends Snip02
  //8<--
