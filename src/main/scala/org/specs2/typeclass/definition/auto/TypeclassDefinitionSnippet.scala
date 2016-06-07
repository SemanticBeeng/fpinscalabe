package org.specs2.typeclass.definition.auto

/**
  *
  */
trait TypeclassDefinitionSnippet {

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

object TypeclassDefinitionSnippet extends TypeclassDefinitionSnippet
