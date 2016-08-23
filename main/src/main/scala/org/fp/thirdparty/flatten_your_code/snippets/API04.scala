// 8<--
package org.fp.thirdparty.flatten_your_code.snippets

import scalaz.{-\/, \/-, \/}

// 8<--
trait API04 {

  def getUserName(data: Map[String, String]): \/[String, String] = {

    if(data.nonEmpty) {
      val v: \/[String, String] = \/-(s"user: $data.head._1 $data.head._2")
      v
    }
    else {
      val v: \/[String, String] = -\/("No user found")
      v
    }
  }

  def getUser(name: String): Option[User] = {

    if(name.startsWith("user"))
      Some(new UserImpl)
    else
      None
  }

  def getEmail(user: User): String = "user@email.com"

  def validateEmail(email: String): Option[String] = {

    if(email.nonEmpty)
      Some("user@email.com")
    else
      None
  }

  def sendEmail(email: String): \/[String, Boolean] = {

    if(email.nonEmpty)
      -\/(email)
    else
      \/-(false)
  }

  val emptyUserRepo = Map[String, String]()
}

class UserImpl extends User {

}


