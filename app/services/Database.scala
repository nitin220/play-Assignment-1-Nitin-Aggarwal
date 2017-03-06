package services

import Model.Accounts

import scala.collection.mutable.ListBuffer


object Database {

  val listOfAccounts = new ListBuffer[Accounts]()+=Accounts(
    "Nitin","","Aggarwal","nitin20.garg@gmail.com", "password", "20/01/1993", "Male", "Playing Guitar", "In Knoldus")

}
