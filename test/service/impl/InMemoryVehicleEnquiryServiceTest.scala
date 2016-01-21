package service.impl

import models.Vehicle
import org.scalatest.FunSuite
import org.scalatest.Matchers._

/**
  * @author kostas.kougios
  *         Date: 21/01/16
  */
class InMemoryVehicleEnquiryServiceTest extends FunSuite
{
	val vehicleEnquiryService = new InMemoryVehicleEnquiryService

	test("search positive") {
		vehicleEnquiryService.search("BD51 SMR", "Volvo") should be(Some(Vehicle("BD51 SMR", "Volvo", "Kostas car")))
	}

	test("search negative") {
		vehicleEnquiryService.search("BD52 SMR", "Volvo") should be(None)
	}

	test("search invalid reg num") {
		an[IllegalArgumentException] should be thrownBy {
			vehicleEnquiryService.search("AZ01DLQ", "Volvo")
		}
	}

}
