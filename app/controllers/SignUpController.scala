package controllers


import com.google.inject.Inject
import play.api.mvc._
import services.{CacheListService, CacheServices, UserService}

class SignUpController @Inject()(service: CacheListService)(mapping:FormController) extends Controller {


  def addAccount = Action { implicit request =>


    mapping.regForm.bindFromRequest.fold(
      formWithErrors => {
        Redirect(routes.SignUpController.signUpForm()).flashing(
          "error" -> "Something went Wrong Please Try Again")
      },
      userData => {
        if(service.check(userData.uname)){
        service.addUser(userData)
        Redirect(routes.HomeController.profile()).withSession(
          "username" -> userData.uname)}
          else{
          Redirect(routes.SignUpController.signUpForm()).flashing(
            "error" -> "Username Already exists")
        }

      }
    )
  }


  def signUpForm = Action { implicit request =>

    Ok(views.html.formSignUp())
  }


}
