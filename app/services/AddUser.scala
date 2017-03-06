package services

import Model.Accounts


object AddUser {

  def addUser(user:Accounts) = Database.listOfAccounts+=user

}
