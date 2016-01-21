package controllers

import javax.inject.Inject

import constraints.VehicleRegistrationNumber
import play.api.data.Form
import play.api.data.Forms._
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc._
import service.VehicleEnquiryService

class VehicleEnquiryController @Inject()(val messagesApi: MessagesApi, vehicleEnquiryService: VehicleEnquiryService)
	extends Controller with I18nSupport
{

	val vehicleEnquiryForm: Form[VehicleEnquiryForm] = Form {
		mapping(
			"registrationNumber" -> nonEmptyText.verifying(VehicleRegistrationNumber.constraint),
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
			form => {
				// Note: we might want to redirect here to avoid re-posts
				val searchResults = vehicleEnquiryService.search(form.registrationNumber, form.vehicleMake)
				Ok(views.html.vehicleEnquirySearchResults(searchResults))
			}
		)
	}
}

case class VehicleEnquiryForm(registrationNumber: String, vehicleMake: String)
