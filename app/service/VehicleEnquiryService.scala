package service

import models.Vehicle

/**
  * @author kostas.kougios
  *         Date: 21/01/16
  */
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
