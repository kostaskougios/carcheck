package controllers.json

import controllers.VehicleEnquiryForm
import models.Vehicle
import org.scalatest.FunSuite
import org.scalatest.Matchers._
import play.api.libs.json._
import play.api.test.Helpers._
import play.api.test._

/**
  * @author kostas.kougios
  *         Date: 21/01/16
  */
class VehicleEnquiryJsonControllerTest extends FunSuite
{
	test("vehicle not found") {
		running(FakeApplication()) {
			val result = route(FakeRequest(POST, "/api/vehicle-enquiry").withBody(
				Json.toJson(VehicleEnquiryForm("BD51 SMX", "Volvo"))
			)).get
			status(result) should be(NOT_FOUND)
		}
	}

	test("vehicle found") {
		running(FakeApplication()) {
			val result = route(FakeRequest(POST, "/api/vehicle-enquiry").withBody(
				Json.toJson(VehicleEnquiryForm("BD51 SMR", "Volvo"))
			)).get
			val vehicle = Json.fromJson[Vehicle](contentAsJson(result)).get
			vehicle should be(Vehicle("BD51 SMR", "Volvo", "Kostas car"))
		}
	}

	test("invalid registration number") {
		running(FakeApplication()) {
			val result = route(FakeRequest(POST, "/api/vehicle-enquiry").withBody(
				Json.toJson(VehicleEnquiryForm("BD51_SMX", "Volvo"))
			)).get
			status(result) should be(BAD_REQUEST)
			val json = contentAsJson(result)
			(json \ "status").as[String] should be("KO")
			(json \ "message" \ "registrationNumber").as[String] should be("Invalid number BD51_SMX")
		}
	}
}
