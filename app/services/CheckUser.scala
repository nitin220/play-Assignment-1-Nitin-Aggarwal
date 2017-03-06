package services

import Model.User

object CheckUser {

  def check(user:User):Boolean={
      val listOfUnameAndPassword=Database.listOfAccounts.map(
        value=>(value.uname,value.password)
      )

    if(listOfUnameAndPassword.contains((user.uname,user.password))) true
    else false
  }

}
