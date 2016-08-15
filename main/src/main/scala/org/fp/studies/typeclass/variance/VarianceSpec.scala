package org.fp.studies.typeclass.variance

import org.fp.bookmarks._
import org.fp.concepts._
import org.fp.resources._

//
import org.specs2.specification.Snippets
import scala.reflect.runtime.universe._
import shapeless.test.illTyped

trait Includes {

  type defs = Defs
  val defsInclude: String = showRaw(reify {

trait Defs {
  trait A1
  trait A2 extends A1
  trait B1
  trait B2 extends B1

  def f(a : A1) : B1 = new B1{}
  def g(a : A2) : B2 = new B2{}

  val fv1 : Function1[A1, B1] = f
  var fv2 : (A1) => B1 = f

  val gv1 : Function1[A2, B2] = g
  var gv2 : (A2) => B2 = g
}
    defs = new Defs{}

}.tree)
}

/**
  *
  */
object VarianceSpec extends org.specs2.Specification with Snippets with Includes {

  def is = s"Variance example".title ^ s2"""

### Functions are types

  s"$defsInclude"

  ${snippet{

//    val t = reify {
//      val a = new A1{}
//    }.tree
//
//    println("++++++++++++++++++++++++++++++++++++++++++++++++++" + showRaw(t))
//    println("++++++++++++++++++++++++++++++++++++++++++++++++++" + showRaw(t))


    val obj = Includes#Defas
    val mirror = runtimeMirror(obj.getClass.getClassLoader)
    val insMirror = mirror reflect obj
    val originType = insMirror.symbol.typeSignature

    typeOf[(A1) => B1] =:= originType must_== true
    typeOf[A1] <:< typeOf[A2] must_== true

    illTyped("""
      gv2 = fv1
      """)
    illTyped("""
      fv2 = gv1
      """)
  }}
  """
}
