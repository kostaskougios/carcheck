package controllers.json

import javax.inject.Inject

import play.api.libs.json._
import play.api.mvc._
import service.VehicleEnquiryService

/**
  * Serves vehicle enquires via a Rest API
  */
class VehicleEnquiryJsonController @Inject()(vehicleEnquiryService: VehicleEnquiryService) extends Controller
{
	def search = Action(BodyParsers.parse.json) { implicit request =>

		request.body.validate(FormReads).fold(
			errors => {
				BadRequest(Json.obj("status" -> "KO", "message" -> JsError.toJson(errors)))
			},
			form => {
				vehicleEnquiryService.search(form.registrationNumber, form.vehicleMake) match {
					case Some(vehicle) =>
						Ok(Json.toJson(vehicle))
					case None =>
						NotFound
				}
			}
		)
	}
}


