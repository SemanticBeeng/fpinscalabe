package org.fp.thirdparty.cakesolutions.Existential_types_in_Scala.snippets

  //8<--
trait Snip01 {
  //8<--
  case class SomeClass[T](value: T)

  type F[A] = SomeClass[A] // `A` appears on the left and right side, common case

  type FE = SomeClass[A] forSome { type A } // `A` appears only on the right, existential case
  //8<--
}

object Snip01 extends Snip01
  //8<--
