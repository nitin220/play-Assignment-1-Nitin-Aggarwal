package controllers

import javax.inject._

import play.api.mvc._
import services.GetUserData

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject() extends Controller {

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
      val obj=new GetUserData
      val usedData=obj.userData(username)
      println(usedData)
    Ok(views.html.profilePage(usedData))
    }.getOrElse{
      Unauthorized("Oops")
    }
  }


}
