package org.fp.studies.typeclass.definition

/**
  *
  */
package object auto {

  /**
    *
    */
  trait Definitions {

    import simulacrum._

    @typeclass trait CanTruthy[A] { self =>
      /** Return true, if `a` is truthy. */
      def truthy(a: A): Boolean
    }

    object CanTruthy {
      def fromTruthy[A](f: A => Boolean): CanTruthy[A] = new CanTruthy[A] {
        def truthy(a: A): Boolean = f(a)
      }
    }
  }

  object Definitions extends Definitions


  /**
    * ----------------
    */
  /**
    *
    */
  object Usages extends org.specs2.mutable.Specification /*with Snippets */{

    import auto.Definitions._

    "Some examples of usages".p

    eg {
      "Here’s how we can define typeclass instances for Int:".p

      implicit val intCanTruthy: CanTruthy[Int] = CanTruthy.fromTruthy({
        case 0 => false
        case _ => true
      })

      import CanTruthy.ops._
      10.truthy must_== true
    }

    eg {
      "Next is for List[A]: ".p

      implicit def listCanTruthy[A]: CanTruthy[List[A]] = CanTruthy.fromTruthy({
        case Nil => false
        case _ => true
      })

      import CanTruthy.ops._
      List("foo").truthy must_== true
    }

    eg {
      "It looks like we need to treat Nil specially because of the nonvariance.".p

      implicit val nilCanTruthy: CanTruthy[scala.collection.immutable.Nil.type] = CanTruthy.fromTruthy(_ => false)

      import CanTruthy.ops._
      Nil.truthy must_== false
    }

    eg {
      "And for Boolean using identity: ".p

      implicit val booleanCanTruthy: CanTruthy[Boolean] = CanTruthy.fromTruthy(identity)

      import CanTruthy.ops._

      false.truthy must_== false
    }

    eg {
      "Using CanTruthy typeclass, let’s define truthyIf like LYAHFGG ".p
      "<i>Now let’s make a function that mimics the if statement, but that works with YesNo values.</i>".p
      "To delay the evaluation of the passed arguments, we can use pass-by-name: ".p

      import CanTruthy.ops._

      def truthyIf[A: CanTruthy, B, C](cond: A)(ifyes: => B)(ifno: => C) =
        if (cond.truthy) ifyes
        else ifno

      // duplicate but good to see what needs to be in scope
      implicit def listCanTruthy[A]: CanTruthy[List[A]] = CanTruthy.fromTruthy({
        case Nil => false
        case _ => true
      })

      // duplicate but good to see what needs to be in scope
      implicit val booleanCanTruthy: CanTruthy[Boolean] = CanTruthy.fromTruthy(identity)

      truthyIf(Nil: List[String]) { "YEAH!" } { "NO!" } must_== "NO!"
      truthyIf(2 :: 3 :: 4 :: Nil) { "YEAH!" } { "NO!" } must_== "YEAH!"
      truthyIf(true) { "YEAH!" } { "NO!" } must_== "YEAH!"
    }
  }
}
