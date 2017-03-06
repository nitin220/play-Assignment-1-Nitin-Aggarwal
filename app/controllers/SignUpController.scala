package controllers

import Model.Accounts
import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.json._
import play.api.libs.json.Json
import play.api.mvc._
import play.mvc.BodyParser._
import services.AddUser



class SignUpController extends Controller{

  val RegForm:Form[Accounts] = Form{
    mapping(
      "fname"->nonEmptyText,
      "mname"->text,
      "lname"->nonEmptyText,
      "uname" -> email,
      "password" -> nonEmptyText,
      "dateOfBitrh" -> nonEmptyText,
      "gender" -> nonEmptyText,
      "hobby" -> nonEmptyText,
      "status"->text
    )(Accounts.apply)(Accounts.unapply)

  }

  def addAccount = Action { implicit request =>
    /*val account = RegForm.bindFromRequest.get
    AddUser.addUser(account)
    Redirect(routes.HomeController.firstPage())*/

    RegForm.bindFromRequest.fold(
      formWithErrors => {
        Redirect(routes.SignUpController.signUpForm()).flashing(
          "error" -> "Something went Wrong Please Try Again")
      },
      userData => {
      AddUser.addUser(userData)
       Redirect(routes.HomeController.profile()).withSession(
         "username" -> userData.uname)
      }
    )
  }

 /* def addAccount= Action(parse.form(RegForm)) { implicit request =>

  val userData = request.body
    AddUser.addUser(userData)
    implicit val xyz=Json.format[Accounts]
    val jsonBody: Option[JsValue] = Some(Json.toJson(userData))
    jsonBody.map { json =>
      Ok("Got: " + (json \ "uname").as[String])
    }.getOrElse {
      BadRequest("Expecting application/json request body")
    }

    //Redirect(routes.HomeController.firstPage())
  //Redirect(routes.Application.home(id))
}*/

  def signUpForm = Action { implicit request=>

    Ok(views.html.formSignUp())
  }



}
