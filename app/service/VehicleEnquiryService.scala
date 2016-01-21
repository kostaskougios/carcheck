package service

import com.google.inject.ImplementedBy
import models.Vehicle
import service.impl.InMemoryVehicleEnquiryService

/**
  * Searches for vehicles.
  *
  * @author kostas.kougios
  *         Date: 21/01/16
  */
@ImplementedBy(classOf[InMemoryVehicleEnquiryService])
trait VehicleEnquiryService
{
	/**
	  * Searches for a vehicle by its registration number and make
	  *
	  * @param registrationNumber a valid registration number
	  * @param vehicleMake        the make of the vehicle
	  * @return a vehicle or None if none found
	  */
	def search(registrationNumber: String, vehicleMake: String): Option[Vehicle]
}
