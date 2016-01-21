package controllers

import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc._

class VehicleEnquiryController extends Controller
{

	val vehicleEnquiryForm: Form[VehicleEnquiryForm] = Form {
		mapping(
			"name" -> nonEmptyText,
			"age" -> nonEmptyText
		)(VehicleEnquiryForm.apply)(VehicleEnquiryForm.unapply)
	}

	def searchBegin = Action {
		Ok(views.html.vehicleEnquiry(vehicleEnquiryForm))
	}

	def search() = Action { implicit request =>
		vehicleEnquiryForm.bindFromRequest.fold(
			errorForm => {
				Ok(views.html.vehicleEnquiry(errorForm))
			},
			person => {
				Redirect(routes.VehicleEnquiryController.searchBegin)
			}
		)
	}
}

case class VehicleEnquiryForm(registrationNumber: String, vehicleMake: String)
