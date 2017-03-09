package controllersTest

import controllers.HomeController

import org.scalatest.mock.MockitoSugar
import play.api.mvc.Results
import play.api.test.Helpers._
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.test.FakeRequest
import services.CacheListService





class HomeControllerSpec extends PlaySpec with MockitoSugar with Results  {

  "HomeController" should {

   /* "render the firstPage" in {
      val home = route(app, FakeRequest(GET, "/")).get

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include ("Welcome Page")
    }*/

    "render the firstPage" in {
      val service = mock[CacheListService]
      val controller = new HomeController(service)
      val result = controller.firstPage().apply(FakeRequest(GET,"/"))
      contentType(result) mustBe Some("text/html")
      contentAsString(result) must include ("Welcome Page")
    }

   /* "render the firstpage  from management" in {
      val home = route(app, FakeRequest(GET, "/home")).get

      redirectLocation(home) mustBe
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include ("Welcome Page")
      status(route(app, FakeRequest(GET, "/")).get) mustBe OK
      contentAsString(route(app, FakeRequest(GET, "/signup")).get) must include("Create an Account")
    }*/

  }

}
