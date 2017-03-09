package Model

/**
  * Created by Nitin Aggarwal on 05-Mar-17.
  */
case class Accounts(
                    fname:String,
                    mname:String,
                    lname:String,
                     uname:String,
                     password:String,
                    repassword:String,
                     dateOfBitrh:String,
                      role:Boolean,
                     gender:String,
                     hobby:String,
                     status:String,
                    isEnable:Boolean
                   )

case class User(
                       uname:String,
                       password:String
                       )
