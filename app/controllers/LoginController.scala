package controllers

import play.api.mvc.{Action, Controller}


class LoginController extends Controller{

  def logout = Action{ implicit request=>

    Redirect(routes.HomeController.firstPage()).withNewSession
  }

}
