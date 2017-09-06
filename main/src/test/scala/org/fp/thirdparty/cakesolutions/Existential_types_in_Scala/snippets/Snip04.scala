package org.fp.thirdparty.cakesolutions.Existential_types_in_Scala.snippets

  //8<--
trait Snip04 {
  import Snip03._

  //8<--
  import java.util.Date
  import java.time.Instant

  object AllowedType {

    def apply[A](implicit ev: AllowedType[A]) = ev

    def instance[A, J >: Null <: AnyRef](f: A => J): AllowedType[A] =
      new AllowedType[A] {
        type JavaType = J
        def toJavaType(a: A): J = f(a)
      }

    implicit val intInstance: AllowedType[Int] = instance(Int.box(_))
    implicit val strInstance: AllowedType[String] = instance(identity)
    implicit val boolInstance: AllowedType[Boolean] = instance(Boolean.box(_))
    implicit val instantInst: AllowedType[Instant] = instance(Date.from(_))

    // For Option, we turn `None` into `null`; this is why we needed that `:> Null` restriction
    implicit def optionInst[A](implicit ev: AllowedType[A]): AllowedType[Option[A]] =
      instance[Option[A], ev.JavaType](s => s.map(ev.toJavaType(_)).orNull)
  }
  //8<--
}

object Snip04 extends Snip04
  //8<--
