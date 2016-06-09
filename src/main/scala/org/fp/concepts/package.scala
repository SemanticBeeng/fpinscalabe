package org.fp

/**
  *
  */
package object concepts {

  val keyPoint = "$$key point$$>>"
  val bookmarks = "$$bookmarks$$>>"

  //
  val selfType = "self-type"
  /**
    * @todo https://hyp.is/iJIsfC2GEeam8q-I4gf9FA/archive.is/KAdCc
    */
  val structuralType = "structural type"

  //
  val typeClass = "[type class]"
  val taggedType = "[tagged type]"
  val higherKindedType = "[higher-Kinded type]"

  //
  val functor = "[functor]"
  val functorComposition = "[functor composition]"
  /**
    * @todo related to [[operatorLift]]
    */
  val functionLifting = "[function lifting]"

  /**
    * @todo "fmap" in Haskell
    */
  val operatorMap = "[map operator]"
  val operatorLift = "[lift operator]"
  val operatorVoid = "[void operator]"
  val operatorFproduct = "[fproduct operator]"

  //
  val applicative = "[applicative]"

  //
  val monoid = "[monoid]"
  val operatorAppend = "[append operator]"
}

package object rules {
  //import org.fp.concepts._
  //import org.specs2.s2

  //@todo val functionApplicationPreservesShape = s2""" $functor mapping (of a function) preserves the type/shape of the $functor"""
}