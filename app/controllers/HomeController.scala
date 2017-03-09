package controllers

import javax.inject._

import play.api.mvc._
import services.{CacheListService}

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(service:CacheListService) extends Controller {

  /**
   * Create an Action to render an HTML page with a welcome message.
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def firstPage = Action { implicit request=>
    Ok(views.html.index())
  }

  def profile = Action { implicit request=>

    request.session.get("username").map{ username=>
      val usedData=service.userData(username)
      if(usedData!=None){
      if(usedData.get.isEnable) {
        Ok(views.html.profilePage(usedData.get))}
      else Redirect(routes.SignInController.signInForm()).flashing(
        "error" -> "Blocked By Admin!!")}
      else Redirect(routes.SignUpController.signUpForm()).flashing(
        "error" -> "Please Register first")
    }.getOrElse{
      Unauthorized("Oops")
    }
  }
  def profilePage=Action{
    Redirect(routes.HomeController.firstPage())
  }


}
