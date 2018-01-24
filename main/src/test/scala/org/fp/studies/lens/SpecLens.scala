package org.fp.studies.lens

import org.fp.concepts._
import org.fp.resources._
import org.fp.bookmarks._
import org.specs2.specification.dsl.mutable.{AutoExamples, TextDsl}

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
//class SpecLens extends org.specs2.Specification /*with MonocleSuite *//*with TextDsl with AutoExamples*/ {
//
//    @Lenses case class Address(streetNumber: Int, streetName: String)
//    @Lenses case class Person(name: String, age: Int, address: Address)
//
//    object Manual { // Lens created manually (i.e. without macro)
//      val _name = Lens[Person, String](_.name)(n => p => p.copy(name = n))
//      val _age = Lens[Person, Int](_.age)(a => p => p.copy(age = a))
//      val _address = Lens[Person, Address](_.address)(a => p => p.copy(address = a))
//      val _streetNumber = Lens[Address, Int](_.streetNumber)(n => a => a.copy(streetNumber = n))
//    }
//
//    object Semi { // Lens generated semi automatically using GenLens macro
//      val name = GenLens[Person](_.name)
//      val age = GenLens[Person](_.age)
//      val address = GenLens[Person](_.address)
//      val streetNumber = GenLens[Address](_.streetNumber)
//    }
//
//    val john = Person("John", 30, Address(126, "High Street"))
//
//    def is = s2"""
//      some exp
//      ${ Manual._name.get(john) must_== "John1"     }
//      ${ Semi.name.get(john)    must_== "John"      }
//      ${ Person.name.get(john)  must_== "John"      }
//      ${ john.lens(_.name).get  must_== "John"      }
//"""
////    //s"$keyPoint Use ...:".p
////    eg {
////
////      Manual._name.get(john) must_== "John"
////      Semi.name.get(john)    must_== "John"
////      Person.name.get(john)  must_== "John"
////      john.lens(_.name).get  must_== "John"
////    }
//}

class LensSpec extends org.specs2.Specification {

    @Lenses case class Address(streetNumber: Int, streetName: String)
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


def is = s2"""

  This is a specification to check the 'Hello world' string
  The 'Hello world' string satisfies the following properties:
      ${ Manual._name.get(john) must_== "John1"     }
      ${ Semi.name.get(john)    must_== "John"      }
      ${ Person.name.get(john)  must_== "John"      }
      ${ john.lens(_.name).get  must_== "John"      }
"""

}