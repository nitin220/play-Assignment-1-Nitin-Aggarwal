package services

import Model.{User, Accounts}

trait UserService {

  def addUser(user:Accounts):Unit

  def check(user:User):Boolean

  def userData(username:String):Accounts

}
