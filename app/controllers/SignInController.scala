package controllers

import javax.inject.Inject

import play.api.mvc.{Action, Controller}
import services.{CacheListService, CacheServices, UserService}

import scala.concurrent.Future

class SignInController @Inject()(service:CacheListService)(mapping:FormController) extends Controller{


  def checkUser=Action{ implicit request =>
      mapping.signInForm.bindFromRequest.fold(
      formWithErrors => {
        Redirect(routes.SignInController.signInForm()).flashing(
          "error" -> "Something went Wrong Please Try Again")
      },
      userData => {

        val flag:Boolean=service.check(userData.uname)
        if(flag)
          Redirect(routes.HomeController.profile()).withSession(
            "username" -> userData.uname)
        else {
          Redirect(routes.SignInController.signInForm()).flashing(
            "error" -> "UserName Or Password is wrong Please Try Again!!")

        }
      }
    )


  }

  def signInForm = Action.async { implicit request=>
    val res = Ok((views.html.formSignIn()))
    Future.successful(res)

  }

}
