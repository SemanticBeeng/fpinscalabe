package org.fp.thirdparty.cakesolutions.Existential_types_in_Scala.snippets

  //8<--
trait Snip03 {
  //8<--
  /*sealed */trait AllowedType[A] {
      /**
       * The Java type we are converting to.
       *
       * Note the restrictions:
       *
       * * `:> Null` means that we can turn the type into `null`.
       *   This is needed since many Java SQL libraries interpret NULL as `null`
       * * `<: AnyRef` just means "this is an Object". ie: not an AnyVal.
       */
      type JavaType >: Null <: AnyRef

      /**
       * Function that converts `A` (eg: Int) to the JavaType (eg: Integer)
       */
      def toJavaType(a: A): JavaType

      /**
       * Same as above, but upcasts to Object (which is what `bind` expects)
       */
      def toObject(a: A): Object = toJavaType(a)
    }

    object AllowedType {

      def apply[A](implicit ev: AllowedType[A]) = ev

      def instance[A, J >: Null <: AnyRef](f: A => J): AllowedType[A] =
        new AllowedType[A] {
          type JavaType = J
          def toJavaType(a: A): J = f(a)
        }
    }
  //8<--
}

object Snip03 extends Snip03
  //8<--
