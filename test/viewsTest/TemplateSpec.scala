package viewsTest

import play.api.test.FakeRequest
import play.api.test.Helpers._
import org.scalatestplus.play.PlaySpec


class TemplateSpec extends PlaySpec {

  "Template" should {

    "render index template" in new App {
      val html = views.html.index()
      contentAsString(html) mustBe contain("Welcome Page")
    }

    "render main template with welcome Page and navbar" in new App {
      val html = views.html.main("")(views.html.welcomePage())
      contentAsString(html) mustBe contain("Welcome Page")
    }

   /* "render SignUp template" in new App {
     /* val html = views.html.formSignUp()*/
      val result = route(app, FakeRequest(GET, "/signup")).get
      contentAsString(result) mustBe contain("First Name")
    }*/

    /*"render SignIn template" in new App {
      val html = views.html.formSignIn()
      contentAsString(html) mustBe contain("Email address")
    }*/

  }

}
