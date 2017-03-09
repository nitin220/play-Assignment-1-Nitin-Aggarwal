package services

import Model.Accounts
import com.google.inject.{Singleton, Inject}
import play.api.cache.CacheApi

import scala.collection.mutable.ListBuffer

class CacheListService @Inject()(cache: CacheApi) extends Cache {

  val listOfAccounts = new ListBuffer[Accounts]() += Accounts(
    "Nitin", "", "Aggarwal", "nitin20.garg@gmail.com", "password", "password", "20/01/1993", true, "Male",
    "Playing Guitar", "In Knoldus", true)

  val defaultUser = Accounts(
    "", "", "", "", "", "", "", true, "", "", "", true)

  cache.set("Key", listOfAccounts)

  def addUser(user: Accounts): String = {
    val userWithEncryption = user.copy(password = Encryption.hash(user.password))
    val list = cache.get[ListBuffer[Accounts]]("Key").map {
      list => list += userWithEncryption
    }.getOrElse(ListBuffer())
    cache.remove("key")
    cache.set("Key", list)
    "success"
  }

  def userData(username: String): Option[Accounts] = {
    if (check(username)) {
      val optionList = cache.get[ListBuffer[Accounts]]("Key")
      val list = optionList.map {
        identity
      }.getOrElse(ListBuffer())
      val result = list.filter(_.uname == username).headOption
      result
    }
    else {

      None
    }

  }

  def check(name: String): Boolean = {
    val list = cache.get[ListBuffer[Accounts]]("Key").map {
      identity
    }.getOrElse(ListBuffer())
    val userList: ListBuffer[Accounts] = list.filter(_.uname == name)
    if (userList nonEmpty) true
    else false
  }

  def getAll(): List[String] = {
    val list = cache.get[ListBuffer[Accounts]]("Key").map {
      identity
    }.getOrElse(ListBuffer())
    val listOfUsers = list.map(_.uname)
    listOfUsers.toList
  }

  def enable(name: String): String = {
    val list = cache.get[ListBuffer[Accounts]]("Key").map {
      identity
    }.getOrElse(ListBuffer())
    val user = list.filter(_.uname == name).headOption.map {
      identity
    }.getOrElse(defaultUser)
    list -= user
    val newuser = user.copy(isEnable = true)
    list += newuser
    cache.remove("key")
    cache.set("key", list)
    "success enabled"
  }

  def disable(name: String): String = {
    val list = cache.get[ListBuffer[Accounts]]("Key").map {
      identity
    }.getOrElse(ListBuffer())
    val user = list.filter(_.uname == name).headOption.map {
      identity
    }.getOrElse(defaultUser)
    list -= user
    val newuser = user.copy(isEnable = false)
    list += newuser
    cache.remove("key")
    cache.set("key", list)
    "success disabled"
  }

}
