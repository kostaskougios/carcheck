package controllers

import javax.inject.Inject

import play.api.data.Form
import play.api.data.Forms._
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc._

class VehicleEnquiryController @Inject()(val messagesApi: MessagesApi)
	extends Controller with I18nSupport
{

	val vehicleEnquiryForm: Form[VehicleEnquiryForm] = Form {
		mapping(
			"registrationNumber" -> nonEmptyText,
			"vehicleMake" -> nonEmptyText
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
