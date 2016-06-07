package org.specs2.typeclass.definition

/**
  *
  */
package object manual {

  /**
    *
    */
  trait Definitions {

    // 8<---
    trait CanTruthy[A] {
      self =>
      /** @return true, if `a` is truthy. */
      def truthys(a: A): Boolean
    }

    object CanTruthy {
      def apply[A](implicit ev: CanTruthy[A]): CanTruthy[A] = ev

      def fromTruthy[A](f: A => Boolean): CanTruthy[A] = new CanTruthy[A] {
        def truthys(a: A): Boolean = f(a)
      }

      trait CanTruthyOps[A] {
        def self: A

        implicit def F: CanTruthy[A]

        final def truthy: Boolean = F.truthys(self)
      }

      object ops {
        implicit def toCanIsTruthyOps[A](v: A)(implicit ev: CanTruthy[A]) =
          new CanTruthyOps[A] {
            def self = v

            implicit def F: CanTruthy[A] = ev
          }
      }
    }

    // 8<---
  }

  object Definitions extends Definitions

  /**
    * ----------------
    */
  /**
    *
    */
  object Usages extends org.specs2.mutable.Specification /*with Snippets */{

    import manual.Definitions._

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

