package services

import Model.Accounts

class GetUserData {

  def userData(username:String):Accounts={
    val user=Database.listOfAccounts.filter(_.uname==username)
    user.head
  }

}
