package org.specs2.examples.monoid

import org.fp._
import org.fp.concepts._
import org.fp.resources._
import org.fp.bookmarks._
//
import org.fp.studies.monoid.append.{CatsSpec, ScalazSpec}
import org.specs2.ugbase.UserGuidePage

/**
  *
  */
object MonoidAppend extends UserGuidePage {

  def is = s"Basic use of $monoid-s".title ^ s2"""

  ${concepts.monoid}s can be use with/over primitive types

    * in ${resources.Scalaz.id} ${ScalazSpec.is}

    * in ${resources.Cats.id} ${CatsSpec.is}

"""
}
