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

	test("make can't be empty") {
		running(FakeApplication()) {
			val form = route(FakeRequest(POST, "/vehicle-enquiry").withBody(
				Map(
					"registrationNumber" -> Seq("AB51DVL"),
					"vehicleMake" -> Seq("")
				)
			)).get
			contentAsString(form) should include("This field is required")
		}
	}

	test("vehicle not found") {
		running(FakeApplication()) {
			val form = route(FakeRequest(POST, "/vehicle-enquiry").withBody(
				Map(
					"registrationNumber" -> Seq("AB51DVL"),
					"vehicleMake" -> Seq("Volvo")
				)
			)).get
			contentAsString(form) should include("Search Results")
			contentAsString(form) should include("Vehicle not found")
		}
	}

	test("vehicle found") {
		running(FakeApplication()) {
			val form = route(FakeRequest(POST, "/vehicle-enquiry").withBody(
				Map(
					"registrationNumber" -> Seq("BD51 SMR"),
					"vehicleMake" -> Seq("Volvo")
				)
			)).get
			contentAsString(form) should include("Search Results")
			contentAsString(form) should include("BD51 SMR")
			contentAsString(form) should include("Volvo")
			contentAsString(form) should include("Kostas car")
		}
	}

}
