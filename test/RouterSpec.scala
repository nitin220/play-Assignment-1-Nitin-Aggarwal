
import play.api.test.Helpers._
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.test.FakeRequest
class RouterSpec extends PlaySpec with OneAppPerTest {

  "Routes" should {

    "respond to the index Action" in new App() {
      val Some(result) = route(app, FakeRequest(GET, "/"))
      status(result) mustBe OK
      contentType(result) mustBe ("text/html")
      charset(result) mustBe ("utf-8")
      contentAsString(result) must contain("Welcome Page")
    }

    "respond to the signUp Action" in new App() {
      val Some(result) = route(app, FakeRequest(GET, "/signup"))
      status(result) mustBe OK
      contentType(result) mustBe ("text/html")
      charset(result) mustBe ("utf-8")
      contentAsString(result) must contain("First Name")
    }

    "respond to the signIn Action" in new App() {
      val Some(result) = route(app, FakeRequest(GET, "/signin"))
      status(result) mustBe OK
      contentType(result) mustBe ("text/html")
      charset(result) mustBe ("utf-8")
      contentAsString(result) must contain("Email address")
    }

    "respond to the signUp Form Action" in new App() {
      val Some(result) = route(app, FakeRequest(POST, "/welcomeToFamily"))
      status(result) mustBe OK
      contentType(result) mustBe ("text/html")
      charset(result) mustBe ("utf-8")
      contentAsString(result) must contain("First Name")
    }

    "respond to the signIn Form Action" in new App() {
      val Some(result) = route(app, FakeRequest(POST, "/hello"))
      status(result) mustBe OK
      contentType(result) mustBe ("text/html")
      charset(result) mustBe ("utf-8")
      contentAsString(result) must contain("Email address")
    }

    "respond to the profile Action" in new App() {
      val Some(result) = route(app, FakeRequest(GET, "/profile"))
      status(result) mustBe OK
      contentType(result) mustBe ("text/html")
      charset(result) mustBe ("utf-8")
      contentAsString(result) must contain("Name:")
    }

    "respond to the Logout Action" in new App() {
      val Some(result) = route(app, FakeRequest(GET, "/logout"))
      status(result) mustBe OK
      contentType(result) mustBe ("text/html")
      charset(result) mustBe ("utf-8")
      contentAsString(result) must contain("Welcome Page")
    }

    "respond to the home Action" in new App() {
      val Some(result) = route(app, FakeRequest(GET, "/home"))
      status(result) mustBe OK
      contentType(result) mustBe ("text/html")
      charset(result) mustBe ("utf-8")
      contentAsString(result) must contain("Welcome Page")
    }

    /*"respond to the management/:uname Action" in new App() {
      val Some(result) = route(app, FakeRequest(GET, "/management/:uname"))
      status(result) mustBe OK
      contentType(result) mustBe ("text/html")
      charset(result) mustBe ("utf-8")
      /*contentAsString(result) must contain("Welcome Page")*/
    }*/

    "respond to the management Action" in new App() {
      val Some(result) = route(app, FakeRequest(GET, "/management"))
      status(result) mustBe OK
      contentType(result) mustBe ("text/html")
      charset(result) mustBe ("utf-8")
    }
  }


}
