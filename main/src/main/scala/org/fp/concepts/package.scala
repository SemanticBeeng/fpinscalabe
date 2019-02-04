package org.fp

/**
  *
  */
package object concepts {

  val keyPoint = "$$key point$$"
  val bookmarks = "$$bookmarks$$"

  /**
    * About functions
    */
  /**
    * A function of a [[higherKindedType]]: `apply`  takes a [[typeParameter]]
    *
    * #todo https://typelevel.org/cats/datatypes/functionk.html
    * _"https://hyp.is/I0BaVPV3EeiGuZuiqzEnfg/typelevel.org/cats/datatypes/functionk.html"_
    * "interpreters for [[freeMonad]] and [[freeApplicative]]s are represented as [[functionK]] instances"
    *
    * @see [[functionLambda]]
    * @see [[functionValue]]
    * @see [[naturalTransformation]]
    * @see [[parametricPolymorphicFunction]]
    * @see [[functorK]]
    */
  val functionK = s"[functionK]"
  val functionLambda = "[function lambda]"
  val functionValue = "[function value]"
  val functionComposition = "[function composition]"
  val functionOverloading = "[function overloading]"
  val functionLifting = "[function lifting]"
  val partialFunction = "[partial function]"
  val functionPartialApplication = "[function partial application]"

  /**
    * About types
    */
  val typeClass = "[type class]"
  val typeVariable = "[type variable]"
  val typeAlias = "[type alias]"
  val existentialType = "[existential type (fp)]"
  val phantomType = "[phantom type (fp)]"

  val typeClassInstance = "[type class instance]"

  /**
    * [[org.fp.resources.impl.Resource]]
    * https://en.wikipedia.org/wiki/Currying
    *
    * [[org.fp.bookmarks.ann_stackOverflow_typeLambdaAndBenefits_bkm1]]
    */
  val typeCurrying = "[type currying]"
  val selfType = "[self type]"

  /**
    * [[org.fp.resources.impl.Resource]]
    * https://hyp.is/iJIsfC2GEeam8q-I4gf9FA/archive.is/KAdCc
    */
  val structuralType = "[structural type]"

  val pathDependentType = "[path dependent type]"

  val taggedType = "[tagged type]"

  val constructor = "[constructor]"
  val typeConstructor = "[type constructor]"

  /**
    * [[resources.typeLevel_TypeParamsAndMembers]]
    *
    * @see [[higherKindedType]]
    * @see [[typeMember]]
    *
    */
  val typeParameter = "[type parameter]"

  /**
    * [[org.fp.resources.impl.Resource]]
    *
    * @see [[higherKindedType]]
    * @see [[typeParameter]]
    */
  val typeMember = "[type member]"

  val typeKind = "[type kind (fp)]"

  /**
    * [[resources.so_typeLambdaAndBenefits]]
    *
    * @see [[typeConstructor]]
    * @see [[higherKindedType]]
    * @see [[typeMember]]
    * @see [[typeParameter]]
    * @see [[structuralType]]
    * @see [[typeCurrying]]
    * @see [[partialFunction]]
    * @see [[functionPartialApplication]]
    *
    */
  val typeLambda = "[type lambda]"

  val typeProjection = "[type projection (fp)]"

    /**
    * [[org.fp.resources.impl.Resource]]
    * https://hyp.is/SEDtCJLOEeejFN-_hdbysw/stackoverflow.com/questions/8736164/what-are-type-lambdas-in-scala-and-what-are-their-benefits
    */
  val higherKindedType = "[higher-Kinded type]"

  val implicitConversion = "[implicit conversion]"

  /**
    * about polymorphism
    */

   val ad_hocPolymorphism = "[ad-hoc polymorphism]"
   val polymorphicLambdaValue = "[polymorphic lambda value]"
   val polymorphicFunctionType = "[polymorphic function type]"
   /**
     * @see [[typeParameter]]
     */
   val parametricPolymorphism = "[parametric polymorphism]"
   val parametricPolymorphicFunction = "[parametric polymorphic function]"

   val companionObject = "[companion object]"
   val glueObject = "[glue object]"

  /**
    * about control flow
    */
   val forComprehension = "[for comprehension]"

    val effect = "[effect (fp)]"

  /**
    * About category theory
    */

  //
  val semigroup = "[semigroup]"
  val monoid = "[monoid]"

  /**
    * See Functor.md
    */
  val functor = "[functor]"
  val biFunctor = "[bi functor]"
  /**
    * @wiki Applies [[partialFunction]] nested in a instance of [[functor]] F to an argument nested in an instance of F and returns the result in instance of F.
    *      If the [[partialFunction]] has more than 1 argument then it may apply multiple arguments but the function needs to be in curried form.
    */
  val applyFunctor = "[apply functor]"
  val applicativeFunctor = "[applicative functor]"
  val applicativeBuilder = "[applicative builder]"
  val applicativeValue = "[applicative value]"
  val traverseFunctor = "[traverse]"
  /**
    * #todo https://github.com/typelevel/cats-tagless/blob/master/core/src/main/scala/cats/tagless/FunctorK.scala
    */
  val functorK = "[FunctorK]"
  val arrow = "[arrow]"
  val functionArrow = "[function arrow]"
  val KleisliArrow = "[Kleisli arrow]"
  val KleisliFunction = "[Kleisli function]"
  val coProduct = "[coproduct]"
  val lens = "[lens]"
  val freeMonad = "[free monad]"
  val freeApplicative = "[free applicative]"

  /**
    * Morphism between [[functor]]s
    */
  val naturalTransformation = "[natural transformation]"

  /**
    * @todo related to [[operator_lift]]
    */
  val functorComposition = "[functor composition]"
  val functorLifting = "[functor lifting]"

  /**
    * @todo "fmap" in Haskell
    */
  val operator_map = "[map operator]"
  val operator_mapK = "[mapK operator]"
  val operator_flatMap = "[flatMap operator]"
  val operator_foldMap = "[foldMap operator]"
  val operator_leftMap = "[leftMap operator]"
  val operator_swap = "[swap operator]"
  val operator_swapped = "[swapped operator]"
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
  val operator_>>= = "[>>= operator]"
  //
  val operator_andThenK = "[andThenK operator]"
  val operator_>==> = "[>==> operator]" /** alias for [[operator_andThenK]]*/
  //
  val operator_=<< = "[operator operator=<<]"
  val operator_local = "[operator local]" //@todo clarify
  //
  val operator_bind = "[operator bind]"

  /**
    *
    */
  val lawIdentity = "[identity law]"
  val lawAssociativity = "[associativity law]"
  val lawCommutativity = "[commutativity law]"
  val lawComposition = "[composition law]"
  val lawHomomorphism = "[homomorphism law]"
  val lawInterchange = "[interchange law]"
  val lawMapping = "[mapping law]" //@todo ??
  val lawFusion = "[fusion law]"

  /**
    * [[lawAssociativity]]
    */
  val semigroupLaws = "[semigroup laws]"
  /**
    * [[lawAssociativity]]
    * [[lawCommutativity]]
    */
  val monadLaws = "[monad laws]"

  val functorLaws = "[functor laws]"

  //
  val monad = "[monad]"
  val monadInstance = "[monad instance]"
  val monadicStructure = "[monadic structure]"
  val monadicContext = "[monadic context]"
  val monadicFunction = "[monadic function]"
  val monadicBind = "[monadic bind]"
  val monadicComposition = "[monadic composition]"
  val monadTransformer = "[monad transformer]"
  val optionTransformer = "[Option transformer]"
  val eitherTransformer = "[Either transformer]"

  /**
    *
    */
  val disjunction = "[disjunction]"
  val leftProjection = "[left projection]"
  val rightProjection = "[right projection]"
  val validation = "[validation]"
}

