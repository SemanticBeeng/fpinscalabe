package org.fp.studies.lens

import org.fp.concepts._
import org.fp.resources._
import org.fp.bookmarks._

//
/**
  *
  */
import monocle.Lens
import monocle.macros.{GenLens, Lenses, PLenses}
import monocle.macros.syntax.lens._
//import monocle.function.GenericOptics
//import monocle.generic.GenericInstances
//import monocle.refined.RefinedInstances
//import monocle.state._
//import monocle.std.StdInstances
//import monocle.syntax.Syntaxes
//import shapeless.test.illTyped
//
//trait MonocleSuite //extends //Discipline
                      //with Matchers
                      //with TestInstances
//                       StdInstances
//                      with GenericOptics
//                      with GenericInstances
//                      with RefinedInstances
//                      with Syntaxes
//                      with StateLensSyntax
//                      with StateOptionalSyntax
//                      with StateGetterSyntax
//                      with StateSetterSyntax
//                      with StateTraversalSyntax
//                      with ReaderGetterSyntax

class LensSpec extends org.specs2.Specification /*with MonocleSuite *//*with TextDsl with AutoExamples*/ {

    @Lenses case class Address(streetNumber: Int, streetName: String, city: Option[String] = None)
    @Lenses case class Person(name: String, age: Int, address: Address)

    object Manual { // Lens created manually (i.e. without macro)
      val _name = Lens[Person, String](_.name)(n => p => p.copy(name = n))
      val _age = Lens[Person, Int](_.age)(a => p => p.copy(age = a))
      val _address = Lens[Person, Address](_.address)(a => p => p.copy(address = a))
      val _streetNumber = Lens[Address, Int](_.streetNumber)(n => a => a.copy(streetNumber = n))
    }

    object Semi { // Lens generated semi automatically using GenLens macro
      val name = GenLens[Person](_.name)
      val age = GenLens[Person](_.age)
      val address = GenLens[Person](_.address)
      val streetNumber = GenLens[Address](_.streetNumber)
    }

    val john = Person("John", 30, Address(126, "High Street"))

    @Lenses case class Company(addresses: List[Address], name: String)
    @Lenses case class Employee(name: String, age: Int, address: Address, company: Company, id: String)

    val company1 = Company(List(Address(126, "High Street", Some("city1"))), "acme")
    val jane = Employee("Jane", 40, Address(200, "Main Street"), company1, "id230")

  /**
    * #resource https://groups.google.com/forum/#!topic/scala-monocle/QSjmBQYrelQ
    * @todo [[lawFusion]] example
    */
  object NestedOptional {
        import monocle.function._
        import monocle.function.all._
        import monocle.Traversal
        import monocle.std.option._
        import monocle.std.list._
        import monocle.std.function._


        //trait a extends EachFunctions
        val companyCities = Company.addresses composeTraversal each composeLens Address.city composePrism monocle.std.option.some
    }

    def is = s2"""

      s"$keyPoint Using $lens in different alternative ways.:".p:
        ${ Manual._name.get(john)   must_== "John"  }
        ${ Semi.name.get(john)      must_== "John"  }
        ${ Person.name.get(john)    must_== "John"  }
        ${ john.lens(_.name).get    must_== "John"  }

      s"$keyPoint Using $lens with nested properties:".p:
        ${ GenLens[Person](_.address.streetName).get(john) must_== "High Street"  }

      s"$keyPoint Using $lens with nested optinal properties:".p:
        ${ NestedOptional.companyCities.getAll(jane.company) must_== List("city1") }

"""
}