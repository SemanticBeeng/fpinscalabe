package org.specs2.typeclass

import org.specs2.common.SnippetHelper.incl
import org.specs2.specification.core.SpecStructure

/**
  *
  */
package object definition {

  /**
    * @see [[org.fp.resources.Scala]] [[org.fp.resources.Scalaz]]
    * @source https://hyp.is/6TGlfiwMEea_38vRFJt4xQ/archive.is/jnGcW
    */
  object ScalaSpec extends org.specs2.Specification /*with Snippets*/ {

    def is = "Defining typeclasses manually".title ^ s"""
         Boiler plate code to define the type class related stuff manually

        ${incl[TypeclassDefinitionSnippet]}
START +++++++++++++++
        $a1
DONE +++++++++++++++
      """

    lazy val uses = Usage.is
    lazy val thisSpec = ScalaSpec.is

    def a1 =
      SpecStructure.dependsOn(thisSpec, uses) and SpecStructure.dependsOn(uses, thisSpec).not

    import TypeclassDefinitionSnippet._
    object Usage extends org.specs2.mutable.Specification /*with Snippets */{

      "Some examples of usages".p
      eg {
        "Here’s how we can define typeclass instances for Int:".p

        implicit val intCanTruthy: CanTruthy[Int] = CanTruthy.truthys({
          case 0 => false
          case _ => true
        })

        import ToCanIsTruthyOps._
        10.truthy must_== true

      }

      eg {
        "Next is for List[A]: ".p

        implicit def listCanTruthy[A]: CanTruthy[List[A]] = CanTruthy.truthys({
          case Nil => false
          case _ => true
        })

        import ToCanIsTruthyOps._
        List("foo").truthy must_== true

      }

      eg {
        "It looks like we need to treat Nil specially because of the nonvariance.".p

        implicit val nilCanTruthy: CanTruthy[scala.collection.immutable.Nil.type] = CanTruthy.truthys(_ => false)

        import ToCanIsTruthyOps._
        Nil.truthy must_== false

      }

      eg {
        "And for Boolean using identity: ".p

        implicit val booleanCanTruthy: CanTruthy[Boolean] = CanTruthy.truthys(identity)

        import ToCanIsTruthyOps._
        false.truthy must_== false

      }

      eg {
        "Using CanTruthy typeclass, let’s define truthyIf like LYAHFGG ".p
        "<i>Now let’s make a function that mimics the if statement, but that works with YesNo values.</i>".p
        "To delay the evaluation of the passed arguments, we can use pass-by-name: ".p

        import ToCanIsTruthyOps._
        def truthyIf[A: CanTruthy, B, C](cond: A)(ifyes: => B)(ifno: => C) =
          if (cond.truthy) ifyes
          else ifno

        // duplicate but good to see what needs to be in scope
        implicit def listCanTruthy[A]: CanTruthy[List[A]] = CanTruthy.truthys({
          case Nil => false
          case _ => true
        })

        // duplicate but good to see what needs to be in scope
        implicit val booleanCanTruthy: CanTruthy[Boolean] = CanTruthy.truthys(identity)

        truthyIf(Nil: List[String]) { "YEAH!" } { "NO!" } must_== "NO!"
        truthyIf(2 :: 3 :: 4 :: Nil) { "YEAH!" } { "NO!" } must_== "YEAH!"
        truthyIf(true) { "YEAH!" } { "NO!" } must_== "YEAH!"

      }
    }
  }

  /**
    * @see [[org.fp.resources.Simulacrum]]
    */
  object SimulacrumSpec extends org.specs2.mutable.Specification {

    success
  }
}

