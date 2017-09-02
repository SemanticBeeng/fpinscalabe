package org.specs2.common

import org.specs2._
import org.specs2.execute._
import org.specs2.matcher.MatchResult
import org.specs2.specification.core.Env
//
import org.scalacheck.Properties

/**
  *
  */
trait CheckedSpec { self : ScalaCheck =>

  def check(properties: Properties): Result = {
    properties.properties.foldLeft(Success(): Result) { case (result, (name, p)) =>
      val r = AsResult(p :| name)
      val message = s"${result.message}|+ $name: $r\n  "

      if (r.isSuccess) Success(message)
      else             Failure(message)
    }.mapMessage(_.trim)
  }

  def check[T](m: MatchResult[T]): Result =
    AsResult(m)

  def run(specification: Specification) = {
    val env = Env()
    try "Verification output:\n" + org.specs2.runner.TextRunner.run(specification)(env).output
    finally env.shutdown
  }

}

