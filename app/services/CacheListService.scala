package services

import Model.Accounts
import com.google.inject.Inject
import play.api.cache.CacheApi

import scala.collection.mutable.ListBuffer

class CacheListService @Inject()(cache: CacheApi) extends Cache {

  val listOfAccounts = new ListBuffer[Accounts]() += Accounts(
    "Nitin", "", "Aggarwal", "nitin20.garg@gmail.com", "password", "password", "20/01/1993", true, "Male",
    "Playing Guitar", "In Knoldus",true)

  val defaultUser = Accounts(
    "Nitin", "", "Aggarwal", "nitin20.garg@gmail.com", "password", "password", "20/01/1993", true, "Male",
    "Playing Guitar", "In Knoldus",true)

  cache.set("Key", listOfAccounts)

  def addUser(user: Accounts): CacheApi = {
    val userWithEncryption = user.copy(password = Encryption.hash(user.password))
    val list = cache.get[ListBuffer[Accounts]]("Key").get += userWithEncryption
    cache.set("Key", list)
    cache
  }

  def userData(username: String): Accounts = {
    if (!check(username)) {
      val list = cache.get[ListBuffer[Accounts]]("Key").get
      list.filter(_.uname == username).head
    }
    else defaultUser

  }

  def check(name: String): Boolean = {
    val list = cache.get[ListBuffer[Accounts]]("Key").get
    val userList = list.filter(_.uname == name)
    if (!(userList != Nil)) true
    else false
  }

  def getAll():List[String]={
    val list = cache.get[ListBuffer[Accounts]]("Key").get
    val listOfUsers=list.map(_.uname)
    listOfUsers.toList
  }

  def enable(name:String):String={
    val list = cache.get[ListBuffer[Accounts]]("Key").get
    val newUserList=list.map{
      case element if element.uname==name =>element.copy(isEnable = true )
      case element => element
    }
    cache.set("key",newUserList)
    "Success"
  }

  def disable(name:String):String={
    val list = cache.get[ListBuffer[Accounts]]("Key").get
    val newUserList=list.map{
      case element if (element.uname==name) =>element.copy(isEnable = false )
      case element => element
    }
    cache.set("key",newUserList)
    "success dissabled"
  }

}
