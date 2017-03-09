package services

import Model.{User, Accounts}
import com.google.inject.Inject
import play.api.cache.CacheApi


class CacheServices @Inject()(cache: CacheApi) {

  val defaultUser = Accounts(
    "Nitin", "", "Aggarwal", "nitin20.garg@gmail.com", "password", "password", "20/01/1993", true, "Male", "Playing Guitar",
    "In Knoldus",true)
  cache.set(defaultUser.uname, defaultUser)

  def addUser(user: Accounts): CacheApi = {
    val userWithEncryption = user.copy(password = Encryption.hash(user.password))
    cache.set(userWithEncryption.uname, userWithEncryption)
    cache
  }

  def userData(username: String): Accounts = {
    if (!check(username)) {
      cache.get[Accounts](username).get
    }
    else defaultUser

  }

  def check(name: String): Boolean = {
    val user = cache.get[Accounts](name)
    if (!(user.isDefined)) true
    else false
  }

  /*def getAll():List[String]={

  }*/


}
