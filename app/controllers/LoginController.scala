package controllers

import com.google.inject.Inject
import play.api.mvc.{Action, Controller}
import services.{CacheListService, CacheServices}


class LoginController @Inject()(service: CacheListService) extends Controller {

  def logout = Action { implicit request =>

    Redirect(routes.HomeController.firstPage()).withNewSession
  }

  def management = Action { implicit request =>

    val list = service.getAll()
    Ok(views.html.ManagementPage(list))
  }

  def enableUser(uname: String) = Action { implicit request =>

    service.enable(uname)
    Redirect(routes.LoginController.management()).flashing(
      "success" -> "User enabled")
  }

  def disableUser(uname: String) = Action { implicit request =>
    service.disable(uname)
    Redirect(routes.LoginController.management()).flashing(
      "success" -> "User disabled").withSession(
      "username" -> uname)
  }
}
