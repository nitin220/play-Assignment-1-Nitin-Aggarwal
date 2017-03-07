package controllers


import play.api.mvc.Controller

import Model.{User, Accounts}
import play.api.data.Form
import play.api.data.Forms._

class FormController extends Controller {

   val regForm:Form[Accounts] = Form{
     mapping(
       "fname"->nonEmptyText,
       "mname"->text,
       "lname"->nonEmptyText,
       "uname" -> email,
       "password" -> nonEmptyText,
       "repassword"->nonEmptyText,
       "dateOfBitrh" -> nonEmptyText,
       "role"->boolean,
       "gender" -> nonEmptyText,
       "hobby" -> nonEmptyText,
       "status"->text,
       "isEnable"->boolean
     )(Accounts.apply)(Accounts.unapply).verifying("Password do not match",fields=>
       fields match {case data=>data.password==data.repassword })

   }

  val signInForm:Form[User] = Form{

    mapping(
      "uname" -> nonEmptyText,
      "password" -> nonEmptyText
    )(User.apply)(User.unapply)

  }

}
