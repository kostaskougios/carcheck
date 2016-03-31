package controllers

import models.Vehicle
import play.api.libs.json.Json

/**
  * @author kostas.kougios
  *         Date: 31/03/16
  */
package object json
{
	implicit val FormReads = Json.reads[VehicleEnquiryForm]
	implicit val FormWrites = Json.writes[VehicleEnquiryForm]
	implicit val VehicleReads = Json.reads[Vehicle]
	implicit val VehicleWrites = Json.writes[Vehicle]
}
