// 8<--
package org.fp.thirdparty.flatten_your_code.snippets

import scalaz.\/

// 8<--
trait API04 {

  def getUserName(data: Map[String, String]): \/[String, String] = ???
  def getUser(name: String): Option[User] = ???
  def getEmail(user: User): String = ???
  def validateEmail(email: String): Option[String] = ???
  def sendEmail(email: String): \/[String, Boolean] = ???

  val data = Map[String, String]()
}


