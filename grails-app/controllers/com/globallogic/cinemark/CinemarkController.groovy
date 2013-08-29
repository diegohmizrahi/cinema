package com.globallogic.cinemark


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import grails.converters.JSON

import javax.xml.bind.ValidationException

import org.codehaus.groovy.grails.web.json.JSONObject

import com.globallogic.cinemark.constants.Codes
import com.globallogic.cinemark.exceptions.CinemarkException
import com.octo.captcha.service.CaptchaServiceException

class CinemarkController {

	def jcaptchaService

	def checkedOperation(def closure) {
		def returnObject
		try {
			closure.response.setHeader("Access-Control-Allow-Origin", "*") //Allows cross-domain origin
			returnObject = closure() as JSON
		} catch (CinemarkException e) {
			returnObject = [code:e.code.code,message:e.code.message] as JSON
		} catch (ValidationException e) {
			returnObject = [code:450,message:e.message] as JSON
			log.error("ValidationException: " + e.message)
		} catch (Exception e) {
			returnObject = [code:Codes.UNEXPECTED_ERROR.code,message:e.message] as JSON
		}
		returnObject
	}

}
