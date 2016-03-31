package controllers.json

import javax.inject.Inject

import constraints.VehicleRegistrationNumber
import play.api.libs.json._
import play.api.mvc._
import service.VehicleEnquiryService

/**
  * Serves vehicle enquires via a Rest API
  *
  * Try
  *
  * curl -H "Content-Type: application/json" -X POST -d '{"registrationNumber":"AB51DVL","vehicleMake":"Fiat"}' http://localhost:9000/api/vehicle-enquiry
  *
  */
class VehicleEnquiryJsonController @Inject()(vehicleEnquiryService: VehicleEnquiryService) extends Controller
{
	def search = Action(BodyParsers.parse.json) { implicit request =>

		request.body.validate(FormReads).fold(
			errors => {
				badRequest(JsError.toJson(errors))
			},
			form => {
				if (!VehicleRegistrationNumber.isValid(form.registrationNumber)) {
					badRequest(Json.obj("registrationNumber" -> s"Invalid number ${form.registrationNumber}"))
				} else {
					vehicleEnquiryService.search(form.registrationNumber, form.vehicleMake) match {
						case Some(vehicle) =>
							Ok(Json.toJson(vehicle))
						case None =>
							NotFound
					}
				}
			}
		)
	}

	private def badRequest(msg: JsObject) = BadRequest(
		Json.obj(
			"status" -> "KO",
			"message" -> msg
		)
	)
}


