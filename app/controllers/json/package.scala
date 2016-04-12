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
	implicit val VehicleReads = Json.reads[Vehicle]
	implicit val VehicleWrites = Json.writes[Vehicle]

}
