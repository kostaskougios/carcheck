package controllers

import controllers.json.schema.VehicleEnquiry
import models.Vehicle
import play.api.libs.json.Json

/**
  * @author kostas.kougios
  *         Date: 31/03/16
  */
package object json
{
	implicit val FormReads = Json.reads[VehicleEnquiry]
	implicit val FormWrites = Json.writes[VehicleEnquiry]
	// Note: json schema should not be the domain model, but here this are so
	// simple and we'll use the domain model class Vehicle. If the json api
	// and domain class start to diverse, a new json schema class should replace
	// Vehicle in the declarations below:
	implicit val VehicleReads = Json.reads[Vehicle]
	implicit val VehicleWrites = Json.writes[Vehicle]

}
