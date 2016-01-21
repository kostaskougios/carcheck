package constraints

import play.api.data.validation.{Constraint, Invalid, Valid}

/**
  * @author kostas.kougios
  *         Date: 21/01/16
  */
object VehicleRegistrationNumber
{
	// found this at http://regexlib.com/UserPatterns.aspx?authorid=d95177b0-6014-4e73-a959-73f1663ae814&AspxAutoDetectCookieSupport=1
	// it looks ok to me.
	private val regExpr = "^([A-HK-PRSVWY][A-HJ-PR-Y])\\s?([0][2-9]|[1-9][0-9])\\s?[A-HJ-PR-Z]{3}$".r

	/**
	  * Checks if a registration number of a vehicle is in a valid format
	  *
	  * @param number the reg number
	  * @return true if the reg number format is ok
	  */
	def isValid(number: String): Boolean = regExpr.pattern.matcher(number).matches

	val constraint = Constraint[String]("vehicle.number") {
		number =>
			if (isValid(number)) Valid else Invalid("invalid.reg.number")
	}
}
