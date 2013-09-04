package com.globallogic.cinemark.constants

enum Codes {

	OK_CODE("200", "OK"),
	MAIL_SENDED_ERROR("702", "The email can not be sended"),
	MAIL_SENDED("703", "The email was sended"),
	INVALID_ARGUMENT("704", "Invalid argument"),
	HTTPS_ERROR("706", "The url should be invoked in secure channel"),
	INVALID_THEATER("1300","No such theater"),
	NO_AVAILABLE_CINEMAS("1301", "No cinemas available for selected theater"),
	UNEXPECTED_ERROR("1000","Error Inesperado"),
	PASSWORD_CHANGE("2000","New Password and Confirmation do not match"),
	PASSWORD_ALREADY_USED("2001","You can not use any of your last passwords"),
	PASSWORD_INVALID_FORMAT("2002","Password must be 8 characters long, and contain at least one Uppercase, one lowercase, one digit, and any of these special characters: @#${'$'}%^&+="),
	INVALID_DATE("1002","Invalid Date"),	
	INVALID_CAPTCHA_ERROR("1048","The captcha is not valid"),
	METOD_NOT_ALLOW("405","Method not allowed Explained"),
	INVALID_EMAIL("605","Invalid email"),	
	INPUT_DATA_EXCEPTION_ERROR("1001","Invalid or null arguments"),
	INVALID_NUMBER_FORMAT("1042","Invalid format , should be an Integer or Long"),
	INVALID_DATE_FORMAT("1030","Invalid Date Format"),
	INVALID_URL("1050","Invalid Url"),
	INVALIDATE_DATE("1073", "Invalid Date"),
	BANNED_PLAYER("1074","User is banned");
	


	
	private Codes(def code, def message) {
		this.message = message
		this.code = code
	}

	def message
	def code
}
