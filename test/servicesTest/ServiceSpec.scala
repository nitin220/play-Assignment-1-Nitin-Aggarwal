package servicesTest

import Model.Accounts
import org.scalatestplus.play.PlaySpec
import play.api.cache.CacheApi
import org.mockito.Mockito._
import org.scalatest.mock.MockitoSugar
import services.CacheListService
import scala.collection.mutable.ListBuffer

class ServiceSpec extends PlaySpec with MockitoSugar {

  "CacheListService should" should {

    "check existence of  user in cache ListBuffer(else part) " in {

      val cache = mock[CacheApi]
      val serviceObject = new CacheListService(cache)
      when(cache.get[ListBuffer[Accounts]]("Key")).thenReturn(Some(ListBuffer[Accounts]()))
      serviceObject.check("") mustBe false
    }

    "check existence of  user in cache ListBuffer(if part) " in {

      val cache = mock[CacheApi]
      val serviceObject = new CacheListService(cache)
      when(cache.get[ListBuffer[Accounts]]("Key")).thenReturn(Some(ListBuffer[Accounts](Accounts(
        "", "", "", "nitin@gmail.com", "", "", "", true, "", "", "", true))))
      serviceObject.check("nitin@gmail.com") mustBe true
    }

    "Get user data from cache" in {

      val cache = mock[CacheApi]
      val serviceObject = new CacheListService(cache)

      when(cache.get[ListBuffer[Accounts]]("Key")).thenReturn(Some(ListBuffer[Accounts](Accounts(
        "", "", "", "nitin@gmail.com", "", "", "", true, "", "", "", true))))

      serviceObject.userData("nitin@gmail.com") mustBe Some(Accounts(
        "", "", "", "nitin@gmail.com", "", "", "", true, "", "", "", true))
    }

    "Get all usernames from cache" in {

      val cache = mock[CacheApi]
      val serviceObject = new CacheListService(cache)

      when(cache.get[ListBuffer[Accounts]]("Key")).thenReturn(Some(ListBuffer[Accounts](Accounts(
        "", "", "", "nitin@gmail.com", "", "", "", true, "", "", "", true))))

      serviceObject.getAll() mustBe List("nitin@gmail.com")
    }

    "Get add user to cache" in {

      val cache = mock[CacheApi]
      val serviceObject = new CacheListService(cache)
      when(cache.get[ListBuffer[Accounts]]("Key")).thenReturn(Some(ListBuffer[Accounts](Accounts(
        "", "", "", "nitin@gmail.com", "", "", "", true, "", "", "", true))))

      serviceObject.addUser(Accounts(
        "", "", "", "nitin@gmail.com", "", "", "", true, "", "", "", true)) mustBe "success"
    }


      "Enable user to login" in {

        val cache = mock[CacheApi]
        val serviceObject = new CacheListService(cache)

        when(cache.get[ListBuffer[Accounts]]("Key")).thenReturn(Some(ListBuffer[Accounts](Accounts(
          "", "", "", "nitin@gmail.com", "", "", "", true, "", "", "", false))))

        serviceObject.enable("nitin@gmail.com") mustBe "success enabled"
      }
    "Disable user to login" in {

        val cache = mock[CacheApi]
        val serviceObject = new CacheListService(cache)

        when(cache.get[ListBuffer[Accounts]]("Key")).thenReturn(Some(ListBuffer[Accounts](Accounts(
          "", "", "", "nitin@gmail.com", "", "", "", true, "", "", "", true))))

        serviceObject.disable("nitin@gmail.com") mustBe "success disabled"
      }
  }

}
