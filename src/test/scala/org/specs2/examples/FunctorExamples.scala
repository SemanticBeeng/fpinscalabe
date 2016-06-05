package org.specs2.examples

import org.specs2._

import scalaz.{std, Functor}
import std.option._
import std.list._


object FunctorExamples extends UserGuidePage { def is = "Functor Examples".title ^ s2"""

  Applying a function to a [[Functor]] preserves the type of the [[Functor]].: ${snippet{

  val len: String => Int = _.length

  class SeqSpecification extends mutable.Specification {
    "updateLast modifies the last element of a Seq".p
    eg { Functor[Option].map(Some("adsf"))(len)         must_== Some(4)   }
    eg { Functor[Option].map(None)(len)                 must_== None      }
    eg { Functor[List].map(List("qwer", "adsfg"))(len)  must_== List(4,5) }
  }
}}

"""
}
