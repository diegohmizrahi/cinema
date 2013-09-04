package com.globallogic.cinemark

import java.util.List;

import grails.converters.JSON
import javax.xml.bind.ValidationException

import com.globallogic.cinemark.constants.Codes
import com.globallogic.cinemark.exceptions.CinemarkException

class CinemarkController {

	def jcaptchaService

	def checkedOperation(def closure) {
		def returnObject
		try {
			closure.response.setHeader("Access-Control-Allow-Origin", "*") //Allows cross-domain origin
			
			returnObject = closure()
		} catch (CinemarkException e) {
			returnObject = [code:e.code.code,message:e.code.message] 
		} catch (ValidationException e) {
			closure.response.setStatus(400)
			returnObject = [code:450,message:e.message]
			log.error("ValidationException: " + e.message)
		} catch (Exception e) {
			closure.response.setStatus(500)
			returnObject = [code:Codes.UNEXPECTED_ERROR.code,message:e.message]
		}
		returnObject as JSON
	}
	
	def buildDTOList(def obs){
		List dtoList = new ArrayList();
		obs?.each() {
			dtoList.add(it.buildDTO());
		}
		return dtoList;
	}

}
