package controllers

import Model.User
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc.{Action, Controller}
import services.CheckUser

class SignInController extends Controller{

  val SignInForm:Form[User] = Form{

    mapping(
      "uname" -> nonEmptyText,
      "password" -> nonEmptyText
    )(User.apply)(User.unapply)

  }

  def checkUser=Action{ implicit request =>
    SignInForm.bindFromRequest.fold(
      formWithErrors => {
        Redirect(routes.SignInController.signInForm()).flashing(
          "error" -> "Something went Wrong Please Try Again")
      },
      userData => {
        val flag:Boolean=CheckUser.check(userData)
        if(flag) Redirect(routes.HomeController.profile()).withSession(
          "username" -> userData.uname)
        else {
          /*Redirect(routes.HomeController.firstPage())*/
          Redirect(routes.SignInController.signInForm()).flashing(
            "error" -> "UserName Or Password is wrong Please Try Again!!")

        }
      }
    )


  }

  def signInForm = Action { implicit request=>
    Ok((views.html.formSignIn()))
  }

}
