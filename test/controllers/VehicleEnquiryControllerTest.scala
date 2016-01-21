package controllers

import org.scalatest.FunSuite
import org.scalatest.Matchers._
import play.api.test.Helpers._
import play.api.test._

/**
  * @author kostas.kougios
  *         Date: 21/01/16
  */
class VehicleEnquiryControllerTest extends FunSuite
{
	test("form rendering") {
		running(FakeApplication()) {
			val form = route(FakeRequest(GET, "/vehicle-enquiry")).get
			status(form) should be(OK)
			contentType(form) should be(Some("text/html"))
			contentAsString(form) should include("Registration number")
		}
	}

	test("invalid vehicle registration number") {
		running(FakeApplication()) {
			val form = route(FakeRequest(POST, "/vehicle-enquiry").withBody(Map("registrationNumber" -> Seq("AZ01DLQ")))).get
			contentAsString(form) should include("Registration number is invalid")
		}
	}

}
