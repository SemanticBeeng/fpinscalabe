package org.fp

import org.fp.resources.intf.ResourceType._

//import scala.reflect.ClassTag

/**
  *
  */
package object resources {

  object intf {

    object ResourceType {

      sealed trait ResourceTypeVal

      trait blog extends ResourceTypeVal
      trait book extends ResourceTypeVal
      trait framework extends ResourceTypeVal
      trait project extends ResourceTypeVal
      trait snippet extends ResourceTypeVal

      //val resourceTypes = Seq(blog, book, framework, project)
    }

    trait Resource[R <: ResourceTypeVal] {

      def id : String
      //def kind: R
      def url: String
    }

    trait Annotation[R <: ResourceTypeVal] {

      def resource: Resource[R]
      def reference: String
    }
  }

  object impl {

    case class Resource[R <: ResourceTypeVal](id: String, url: String)/*(implicit m: ClassTag[R])*/ extends intf.Resource[R] {

      override def toString : String = {
        //@todo s"[${m.runtimeClass.getSimpleName} $id]"
        s"(resource)$id"
      }
      def is = toString
      def md = s"[$id]($url)"

    }
    case class Annotation[R <: ResourceTypeVal](resource: Resource[R], reference: String)/*(implicit m: ClassTag[R])*/ extends intf.Annotation[R] {
      override def toString : String = {
        //@todo s"[${m.runtimeClass.getSimpleName} annotation ${resource.is}]"
        s"annotation of ${resource.is}"
      }
      def is = toString
      def md = s"`$is`"
    }
  }

  import impl._

  /**
    * Resources specific to this project about "functional programming in Scala"
    */
  val Scala = Resource[framework]("Scala", "https://github.com/scala/scala")
  val Scalaz = Resource[framework]("Scalaz", "https://github.com/scalaz/scalaz")
  val Cats = Resource[framework]("Cats", "https://github.com/typelevel/cats")
  val Simulacrum = Resource[framework]("Simulacrum", "https://github.com/mpilquist/simulacrum")
  val ScalaCheck = Resource[framework]("ScalaCheck", "https://www.scalacheck.org/")
  val Specs2 = Resource[framework]("Specs2", "https://etorreborre.github.io/specs2/")
  val Discipline = Resource[framework]("Discipline", "https://github.com/typelevel/discipline")
  val KindProjector = Resource[framework]("non/kind-projector", "https://github.com/non/kind-projector")


  val learningScalaz = Resource[blog]("Learning Scalaz", "http://eed3si9n.com/learning-scalaz/")
  val learningScalazFromLearningScalaz = Resource[blog]("Learning Scalaz from Learning Scalaz", "https://earldouglas.com/notes/learning-scalaz.html")
  val herdingCats = Resource[blog]("Herding Cats", "http://eed3si9n.com/herding-cats/")

  /**
    * @todo - write spec
    */
  val herdingCats_Cartesian = Resource[blog]("Herding Cats - Cartesian", "http://archive.is/4z2JF")

  val timperrett = Resource[blog]("timperrett blog", "http://timperrett.com/")

  val scalaWithCats = Resource[book]("Advanced scala with Cats", "[Noel Welsh, Dave Gurnell] Advanced scala with Cats.pdf")

  val stackOverflow = Resource[blog]("Stack overflow", "http://stackoverflow.com/")

  val casualMiracles_Small_Example_of_Kleisli_Arrows = Resource[blog]("A Small Example of Kleisli Arrows", "http://www.casualmiracles.com")
  /**
    * @todo create spec
    */
  val casualMiracles_ApplicativeFunctorWithScalaz = Resource[blog]("A Small Example of Applicative Functors with Scalaz", "http://archive.is/fokMo")

  val cakeSolutions = Resource[blog]("Cake Solutions", "http://www.cakesolutions.net/")

  val misc = Resource[blog]("Misc sources", "")

  val typeLevel_TypeParamsAndMembers = Resource[blog]("Type members are [almost] type parameters", "https://typelevel.org/blog/2015/07/13/type-members-parameters.html")

  val so_typeLambdaAndBenefits = Resource[blog]("Type lambadas and benefits", "https://stackoverflow.com/questions/8736164/what-are-type-lambdas-in-scala-and-what-are-their-benefits")

  val cakeSolutions_ExistentialTypesInScala = Resource[blog]("Existential types in Scala", "http://archive.is/i17zj")

  val caseClassAttributesWithShapeless = Resource[snippet]("Get a list of all case class attributes with shapeless", "https://gist.github.com/lunaryorn/4b7becbea955ae909af7426d2e2e166c")
}

