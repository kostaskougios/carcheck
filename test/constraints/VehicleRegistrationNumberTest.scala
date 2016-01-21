package constraints

import org.scalatest.FunSuite
import org.scalatest.Matchers._

/**
  * @author kostas.kougios
  *         Date: 21/01/16
  */
class VehicleRegistrationNumberTest extends FunSuite
{
	test("isValid positive") {
		VehicleRegistrationNumber.isValid("BD51 SMR") should be(true)
		VehicleRegistrationNumber.isValid("AB51DVL") should be(true)
	}

	test("isValid negative") {
		VehicleRegistrationNumber.isValid("AZ01DLQ") should be(false)
	}
}
