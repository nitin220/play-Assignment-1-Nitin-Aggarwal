package services

import Model.{User, Accounts}

import scala.collection.mutable.ListBuffer


class service extends UserService{

  val listOfAccounts = new ListBuffer[Accounts]()+=Accounts(
    "Nitin","","Aggarwal","nitin20.garg@gmail.com", "password","password", "20/01/1993",true,
    "Male", "Playing Guitar", "In Knoldus",true)

  def addUser(user:Accounts) = listOfAccounts+=user

  def userData(username:String):Accounts={
    val user=listOfAccounts.filter(_.uname==username)
    user.head
  }

  def check(user:User):Boolean={
    val listOfUnameAndPassword=listOfAccounts.map(
      value=>(value.uname,value.password)
    )

    if(listOfUnameAndPassword.contains((user.uname,user.password))) true
    else false
  }

}
