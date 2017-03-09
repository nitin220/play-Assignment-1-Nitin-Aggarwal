package services

import Model.Accounts
import play.api.cache.CacheApi


trait Cache {

  def getAll():List[String]

  def addUser(user:Accounts):String

  def userData(username:String):Option[Accounts]

  def check(name:String):Boolean

  def disable(uname:String):String

  def enable(uname:String):String

}
