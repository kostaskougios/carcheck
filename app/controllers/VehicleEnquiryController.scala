package controllers

import javax.inject._

import play.api.data.Form
import play.api.data.Forms._
import play.api.i18n._
import play.api.mvc._

class VehicleEnquiryController @Inject()(val messagesApi: MessagesApi) extends Controller with I18nSupport
{

	val vehicleEnquiryForm: Form[VehicleEnquiryForm] = Form {
		mapping(
			"name" -> nonEmptyText,
			"age" -> nonEmptyText
		)(VehicleEnquiryForm.apply)(VehicleEnquiryForm.unapply)
	}

	def index = Action {
		Ok(views.html.index(vehicleEnquiryForm))
	}

	def search() = Action { implicit request =>
		// Bind the form first, then fold the result, passing a function to handle errors, and a function to handle succes.
		vehicleEnquiryForm.bindFromRequest.fold(
			// The error function. We return the index page with the error form, which will render the errors.
			// We also wrap the result in a successful future, since this action is synchronous, but we're required to return
			// a future because the person creation function returns a future.
			errorForm => {
				Ok(views.html.index(errorForm))
			},
			// There were no errors in the from, so create the person.
			person => {
				Redirect(routes.VehicleEnquiryController.index)
			}
		)
	}
}

case class VehicleEnquiryForm(registrationNumber: String, vehicleMake: String)
