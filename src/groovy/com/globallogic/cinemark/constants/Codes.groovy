package com.globallogic.cinemark.constants

enum Codes {

	OK_CODE("200", "OK"),
	MAIL_SENDED_ERROR("702", "The email can not be sended"),
	MAIL_SENDED("703", "The email was sended"),
	INVALID_ARGUMENT("704", "Invalid argument"),
	HTTPS_ERROR("706", "The url should be invoked in secure channel"),
	UNEXPECTED_ERROR("1000","Error Inesperado"),
	PASSWORD_CHANGE("2000","New Password and Confirmation do not match"),
	PASSWORD_ALREADY_USED("2001","You can not use any of your last passwords"),
	PASSWORD_INVALID_FORMAT("2002","Password must be 8 characters long, and contain at least one Uppercase, one lowercase, one digit, and any of these special characters: @#${'$'}%^&+="),
	INVALID_DATE("1002","Invalid Date"),	
	INVALID_CAPTCHA_ERROR("1048","The captcha is not valid"),
	METOD_NOT_ALLOW("405","Method not allowed Explained"),
	NOT_ENOUGH_POINTS("401","Not enough points"),
	INVALID_ITEM("500","Fail to acquire item"),
	INVALID_TOKEN("707","Invalid token or User not authenticated"),
	INVALID_EMAIL("605","Invalid email"),	
	INVALID_USER_ERROR("708","Invalid User"),
	INPUT_DATA_EXCEPTION_ERROR("1001","Invalid or null arguments"),
	EMPTY_JSON_ERROR("602","Empty JSON"),
	INVALID_JSON_ERROR("601","Invalid JSON"),
	INVALID_NUMBER_FORMAT("1042","Invalid format , should be an Integer or Long"),
	INVALID_DATE_FORMAT("1030","Invalid Date Format"),
	GAME_NOT_EXIST_ERROR("1040","Inexsistent game"),
	PHASE_NOT_EXIST_ERROR("1041","Inexsistent phase"),
	NO_GAMES_LOADED("1043", "No games loaded"),
	INVALID_SEARCH_TYPE("1042","The search type is invalid"),
	INVALID_URL("1050","Invalid Url"),
	ITEM_NOT_PURCHASED("1052","Item not purchased"),
	INEXISTENT_PLAYER("1053","Inexistent Player"),
	INEXISTENT_BADGE("1054","Inexistent Badge"),
	INVALID_CHECKSUM("1060","Invalid CheckSum"),
	ITEM_ALREADY_USED("1051", "Item already used"),
	NO_AVAILABLE_ITEMS("1061","There are no items available"),
	PLAYER_ALREADY_EXISTS("1070","Player already exists"),
	NOT_PLAYING("1071","Player has not played yet"),
	NOT_ENABLED_PHASES("1072","Not enabled phases"),
	INVALIDATE_DATE("1073", "Invalid Date"),
	BANNED_PLAYER("1074","User is banned");
	


	
	private Codes(def code, def message) {
		this.message = message
		this.code = code
	}

	def message
	def code
}
