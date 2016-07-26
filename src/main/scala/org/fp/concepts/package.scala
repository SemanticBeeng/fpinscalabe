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
  val constructor = "[constructor]"
  val typeConstructor = "[type constructor]"
  val typeClass = "[type class]"
  val taggedType = "[tagged type]"
  val higherKindedType = "[higher-Kinded type]"

  //
  /**
    * See Functor.md
    */
  val functor = "[functor]"
  val biFunctor = "[bi functor]"
  val applyFunctor = "[apply functor]"
  val applicativeFunctor = "[applicative functor]"
  val applicativeBuilder = "[applicative builder]"
  val applicativeValue = "[applicative value]"
  val traverseFunctor = "[traverse]"
  val KleisliArrow = "[Kleisli arrow]"
  val KleisliFunction = "[Kleisli function]"

  /**
    * @todo related to [[operator_lift]]
    */
  val functionLifting = "[function lifting]"
  val functionComposition = "[function composition]"
  val functorComposition = "[functor composition]"

  /**
    * @todo "fmap" in Haskell
    */
  val operator_map = "[map operator]"
  val operator_mapK = "[mapK operator]"
  val operator_flatMap = "[flatMap operator]"
  val operator_lift = "[lift operator]"
  val operator_void = "[void operator]"
  val operator_Fproduct = "[fproduct operator]"
  val operator_Fpair = "[fpair operator]"
  val operator_as = "[as operator]"
  val operator_shift = "[>| operator]" //@todo what is the correct name?
  val operator_strengthL = "[strengthL operator]"
  val operator_strengthR = "[RightL operator]"
  val operator_point = "[pure/point operator]"
  val operator_apply = "[apply operator]"
  val operator_LHS = "[LHS operator]"
  val operator_RHS = "[RHS operator]"
  val operator_sequence = "[sequence operator]"
  val operator_traverse = "[traverse operator]"
  val operator_append = "[append operator]"
  //
  val operator_compose = "[compose operator]"
  val operator_<=< = "[<=< operator]" /** alias for [[operator_compose]]*/
  //
  val operator_composeK = "[composeK operator]"
  val operator_<==< = "[<==< operator]" /** alias for [[operator_composeK]]*/
  //
  val operator_andThen = "[andThen operator]"
  val operator_>=> = "[>=> operator]" /** alias for [[operator_andThen]]*/
  //
  val operator_andThenK = "[andThenK operator]"
  val operator_>==> = "[>==> operator]" /** alias for [[operator_andThenK]]*/
  //
  val operator_=<< = "[operator operator=<<]"
  val operator_local = "[operator local]" //@todo clarify

  /**
    *
    */
  val monadLaws = "[monad laws]"
  val lawIdentity = "[identity law]"
  val lawComposition = "[composition law]"
  val lawHomomorphism = "[homomorphism law]"
  val lawInterchange = "[interchange law]"
  val lawMapping = "[mapping law]" //@todo ??

  //
  val monad = "[monad]"
  val monadicStructure = "[monadic structure]"
  val monadicContext = "[monadic context]"
  val monadicFunction = "[monadic function]"
  //
  val monoid = "[monoid]"
}

