package org.specs2.guide.monoid

import org.fp._
import org.fp.concepts._
import org.fp.resources._
import org.fp.bookmarks._
import org.fp.studies.monoid.dfault.Spec

//
import org.specs2.ugbase.UserGuidePage

/**
  *
  */
object Monoid_UserGuidePage extends UserGuidePage {

  def is = s"Basic use of $monoid-s".title ^ s2"""

  ${concepts.monoid}s can be use with/over primitive types

    * in ${resources.Scalaz.id} and ${resources.Cats.id}: ${Spec.is}


"""
}
