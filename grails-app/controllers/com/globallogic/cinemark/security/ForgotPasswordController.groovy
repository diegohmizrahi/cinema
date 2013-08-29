package com.globallogic.cinemark.security

import grails.converters.JSON

import com.globallogic.cinemark.PasswordValidator
import com.globallogic.cinemark.constants.Codes
import com.globallogic.cinemark.exceptions.CinemarkException

class ForgotPasswordController {
	

	
	def jcaptchaService
	def forgotPasswordService
	def springSecurityService
	def mailService
	def grailsApplication
	/**
	 * REST Service
	 * Return a JSON response
	 * Send mail for recovery password
	 * @author 
	 * @version 
	 * @since 
	 */
	def forgotPasswordAction = {
		render(view: "forgotPassword" , model: [input: true, linkError: false, emailSent: false])
	}


	def sendMailConfirmationBackOfficeCSRF = {

				
		def userInstance = User.findByUsernameAndEmail (params.username, params.email)
		
		if (!userInstance) {
			flash.error = message (code: "default.username.email.not.found")
			render(view: "forgotPassword" , model: [input: true, linkError: false, emailSent: false])
			return
		}

			if (!jcaptchaService.validateResponse("image", session.id, params.captchaResponse)) {
				flash.error = message (code: "default.invalid.captcha")
				render(view: "forgotPassword" , model: [input: true, linkError: false, emailSent: false])
				return
			}

			def obj = JsonUtils.checkJsonParams(params)
			
			try {
				def map = forgotPasswordService.sendConfirmationMail(JsonUtils.getString(obj.get(JsonUtils.JSON_DATA),"email"),JsonUtils.getBoolean(obj.get(JsonUtils.JSON_DATA),"isBackOffice",false),
					ServerUtilsController.getServerURL(request))
				mailService.sendMail{
					to JsonUtils.getString(obj.get(JsonUtils.JSON_DATA),"email")
					from grailsApplication.config.grails.mail.from
					subject message (code: "default.forgot.email.subject")
					html g.render(template:"/mailTemplate/forgotPassword",model:[url:map.url,serverUrl:map.serverUrl])
				}
			}
			catch (CinemarkException e) {
				flash.error = message (code: "default.forgot.email.invalid")
				render(view: "forgotPassword" , model: [input: true, linkError: false, emailSent: false])				
			}

			if(!(JsonUtils.getBoolean(obj.get(JsonUtils.JSON_DATA),"isBackOffice"))) {
				def dto = [message:Codes.OK_CODE]
				render dto as JSON
			}else{
				
				render(view: "forgotPassword", model: [input: true, linkError: false, emailSent: true])
			}

	}


	def resetPasswordAction = {
		try {
			if (forgotPasswordService.validationToken(params.token,true)) {
				render(view: "resetPassword", model:[token: params.token] )
			}
		}
		catch (CinemarkException e)  {
			render(view: "forgotPassword", model: [input: false, linkError: true, emailSent: false])			
		}
	}


	def resetPasswordBackOficeCSRF = {


			if (!jcaptchaService.validateResponse("image", session.id, params.captchaResponse)) {
				flash.error = message (code: "default.invalid.captcha")
				render(view: "resetPassword", model:[token: params.token] )
				return
			}

			String t = params.token
			def fp = new ForgotPassword();
			def secUser = new User();

			fp = ForgotPassword.findByTokenFP(t);
			secUser = fp.backOficeUser

			String pass = params.newIdentification

			if (forgotPasswordService.passwordLength(pass)){
				flash.error = message (code: "default.invalid.password.length") 
				render(view: "resetPassword", model:[token: params.token] )
				return
			}


			if (!PasswordValidator.validate(pass)) {
				flash.error = message (code: "default.invalid.password.format")
				render(view: "resetPassword", model:[token: params.token] )
				return
			}


			if (forgotPasswordService.passwordEquals(params.newIdentification, params.confirmIdentification)) {

				flash.error = message(code: "default.passwords.dont.match")
				render(view: "resetPassword", model:[token: params.token] )
				return
			}

			def isUsingHistoricPassword = secUser.passwordChange?.any{
				springSecurityService.encodePassword(params.newIdentification) == it.password
			}
			
			if (isUsingHistoricPassword){
				flash.error = message (code: "default.password.history")
				render(view: "resetPassword", model:[token: params.token] )
				return
			}
			try {
				if (forgotPasswordService.userUpdate(secUser, params)) {
					flash.error = message (code: "default.password.data.error")
					render(view: "resetPassword", model:[token: params.token] )
					return
				}
			}catch (CinemarkException cbe) {
				flash.error = message (code: "default.password.data.error")
				render(view: "resetPassword", model:[token: params.token] )
				return
			}

			flash.error = Codes.OK_CODE
			flash.message = message(code: "default.password.changed")
			redirect(controller:"login", action:"index")
			return

	}
}




