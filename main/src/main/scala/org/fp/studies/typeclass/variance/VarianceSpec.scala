package org.fp.studies.typeclass.variance

import org.fp.bookmarks._
import org.fp.concepts._
import org.fp.resources._

//
import org.specs2.specification.Snippets
import shapeless.test.illTyped

/**
  *
  */
object VarianceSpec extends org.specs2.Specification with Snippets{

  def is = s"Variance example".title ^ s2"""

### Functions are types

  ${snippet{

    trait A1
    trait A2 extends A1
    trait B1
    trait B2 extends B1

    def f(a : A1) : B1 = ???
    def g(a : A2) : B2 = ???

    val fv1 : Function1[A1, B1] = f
    var fv2 : (A1) => B1 = f

    val gv1 : Function1[A2, B2] = g
    var gv2 : (A2) => B2 = g

    illTyped("gv2 = fv1")
    illTyped("fv2 = gv1")
  }}
  """
}
