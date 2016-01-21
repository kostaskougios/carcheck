package service.impl

import constraints.VehicleRegistrationNumber
import models.Vehicle
import service.VehicleEnquiryService

/**
  * @author kostas.kougios
  *         Date: 21/01/16
  */
class InMemoryVehicleEnquiryService extends VehicleEnquiryService
{
	private val vehicles = Seq(
		Vehicle("BD51 SMR", "Volvo", "Kostas car"),
		Vehicle("AB51DVL", "Fiat", "Someone's car")
	)

	override def search(registrationNumber: String, vehicleMake: String) = {
		if (!VehicleRegistrationNumber.isValid(registrationNumber)) throw new IllegalArgumentException(s"Invalid registration number : $registrationNumber")
		vehicles.find(v => v.registrationNumber == registrationNumber && v.make == vehicleMake)
	}
}
