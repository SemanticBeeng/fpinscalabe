// 8<--
package org.fp.thirdparty.flatten_your_code.snippets

import scalaz.{-\/, \/-, \/}

// 8<--
trait API04 {

  def getUserName: \/[String, String] = if(theRepo.nonEmpty)  \/-(theRepo.head._1) else -\/("No user found")

  def getUser(name: String): Option[User] = theRepo.get(name)

  def getEmail(user: User): String = user.email

  def validateEmail(email: String): Option[String] = {

    if(email.contains("@"))
      Some(email)
    else
      None
  }

  def sendEmail(email: String): \/[String, Boolean] = {

    if(email.nonEmpty)
      -\/(email)
    else
      \/-(false)
  }

  type Repo = Map[String, User]

  var theRepo : Repo = emptyUserRepo

  def workWithRepo(repo: Repo): Unit = theRepo = repo

  lazy val emptyUserRepo = Map[String, User]()
  lazy val userRepo = Map[String, User] {
    "user1" -> UserImpl("user1", "user1@email.com")
    "user2" -> UserImpl("user1", "user2$email.com")
  }
}

case class UserImpl(name: String, email: String) extends User


