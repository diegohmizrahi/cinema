package com.globallogic.cinemark.security

import org.springframework.dao.DataIntegrityViolationException

import com.globallogic.cinemark.PasswordValidator
import com.globallogic.cinemark.constants.Codes
import com.globallogic.cinemark.exceptions.CinemarkException




class UserController {

	static allowedMethods = [   save: "POST",
		edit: ["GET", "POST"],
		update: "POST",
		delete: "POST"]

	def userService
	def roleService
	def jcaptchaService

	def springSecurityService


	def index() {
		redirect action: 'list', params: params
	}

	def list() {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[userInstanceList: userService.list(params), userInstanceTotal: userService.count()]
	}


	def create() {

		def currentUser = springSecurityService.getCurrentUser()
		if (!currentUser.superAdmin) {
			flash.message = message(code: 'user.create.admin')
			redirect action:"list"
			return
		 }
		def userInstance = new User(params)
		[userInstance: userInstance,
					currentUser: currentUser,
					roleComicBook: roleService.findByAuthority("ROLE_ADMIN")
				
				]
	}
	
	def show() {
		
	  def userInstance = User.get(params.id)
	  if (!userInstance){
		flash.message = message(code: 'invalid.id')
		redirect action:"list"
	  }
	  	[userInstance: userInstance]
	
	  
	}

	def save() {
		def userInstance

		def currentUser = springSecurityService.getCurrentUser()
		if (!currentUser.superAdmin) {
			flash.message = message(code: 'user.create.admin')
			redirect action:"list"
			return
		 }
		String password = params.newIdentification
		if (!PasswordValidator.validate(password)){
			flash.message = message(code: 'user.password.invalid.format', args: [
				message(code: 'user.label', default: 'User'),
				params.id
			])
			render view: 'create', model: [userInstance: new User(params), currentUser: currentUser,
						roleComicBook: roleService.findByAuthority("COMICBOOK_ADMIN")]
						
			return
		}

		
		if (params.superAdmin as boolean && !currentUser.superAdmin){
			flash.message = message(code: 'user.edit.forbiddensuperadmin')
			redirect action: 'list'
			return
		}
		params.superAdmin = false
		userInstance = userService.save(params)
		if (userInstance.hasErrors()) {
			render view: 'create', model: [userInstance: userInstance, currentUser: currentUser,
						roleComicBook: roleService.findByAuthority("COMICBOOK_ADMIN")]
			return
		}
		
		flash.message = message(code: 'default.created.message',
				args: [
					message(code: 'user.label', default: 'User'),
					userInstance.id
				])
		redirect action: "list"
	}
	



	def passwordExpired = {
		[username: params.username]
	}


	def updateExpiredPassword = {
		if (!jcaptchaService.validateResponse("image", session.id, params.captchaResponse)) {
			flash.message = Codes.INVALID_CAPTCHA_ERROR.code + " " + Codes.INVALID_CAPTCHA_ERROR.message
			redirect action: 'passwordExpired'
			return
		}

		def username = params.username
		User user = User.findByUsernameAndPassword(username, springSecurityService.encodePassword(params.password))
		if (!user) {
			flash.message = message(code: 'user.invalid.credentials')
			render view: 'passwordExpired', model: [username: username]
			return
		}
		String password = params.password
		String newPassword = params.newPassword
		String newPassword2 = params.confirmNewPassword
		if (!password || !newPassword || !newPassword2 || newPassword != newPassword2) {
			flash.message = 'Please enter your current password and a valid new password'
			render view: 'passwordExpired', model: [username: username]
			return
		}

		params.passwordExpired = false

		try {
			user = userService.update(user,params)
		}
		catch (CinemarkException e) {	
			render view: 'passwordExpired', model: [username: username]			
		}

		flash.message = 'Password changed succesfully'
		redirect controller: 'login', action: 'auth'
	}

	def edit() {
		def currentUser = springSecurityService.getCurrentUser()
		if (!params.id) {
			params.id = springSecurityService.getCurrentUser().id
			redirect action: 'edit', params: params
		}
		def userInstance = userService.get(params.id)
		if (!userInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'user.label', default: 'User'),
				params.id
			])
			redirect action: 'list'
			return
		}		

		if (userInstance?.superAdmin && !currentUser.superAdmin) {
			flash.message = "You cannot edit this User"
			redirect action:"list"
			return
		}

		[userInstance: userInstance,
					currentUser: currentUser,
					roleComicBook: roleService.findByAuthority("ROLE_ADMIN")]
	}

	def update() {
		User currentUser = springSecurityService.getCurrentUser()
		def userInstance = userService.get(params.id)
		if (!userInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'user.label', default: 'User'),
				params.id
			])
			redirect action: 'list'
			return
		}


		
		
		if(params.adminPassword == null || !params.adminPassword ){
			flash.message = message(code: 'admin.password.null', args: [
				message(code: 'user.label', default: 'User'),
				params.id
			])
			render view: 'edit', model: [userInstance: userInstance,currentUser: currentUser,
				roleComicBook: roleService.findByAuthority("COMICBOOK_ADMIN")]
			return
		}else if(!springSecurityService.encodePassword(params.adminPassword).equals(currentUser.password)){
			flash.message = message(code: 'admin.password.incorrect', args: [
				message(code: 'user.label', default: 'User'),params.id])
		render view: 'edit', model: [userInstance: userInstance,currentUser: currentUser,
			roleComicBook: roleService.findByAuthority("COMICBOOK_ADMIN")]
		return
		}
		
		
		// Convert value from checkbox to Boolean
		params.accountLocked = params.accountLocked as boolean

		// Prevent Admin user to be locked and to change username
		if(userInstance.superAdmin && params.accountLocked == true){
			flash.message = message(code: 'user.admin.lock', args: [
				message(code: 'user.label', default: 'User'),
				userInstance.id
			])
			redirect action: 'list'
			return
		}



		if(userInstance.superAdmin && userInstance.username != params.username){
			flash.message = message(code: 'user.admin.username.forbidden', args: [
				message(code: 'user.label', default: 'User'),
				userInstance.id
			])
			redirect action: 'list'
			return
		}

		// Prevent users to edit another user's information
		
		if (currentUser.username != userInstance.username &&  !currentUser.superAdmin){
			flash.message = message(code: 'user.edit.forbidden', args: [
				message(code: 'user.label', default: 'User'),
				userInstance.id
			])
			redirect action: 'list'
			return
		}

		/* END User account lock */
		if (!currentUser.superAdmin && userInstance.superAdmin != params.superAdmin as boolean){
			flash.message = message(code: 'user.edit.forbidden.superadmin')
			redirect action: 'list'
			return
		}

		def isUsingHistoricPassword = userInstance.passwordChange?.any{
				springSecurityService.encodePassword(params.newIdentification) == it.password
		}

		if (isUsingHistoricPassword){
				flash.message = message(code: 'user.password.13last.format', args: [
					message(code: 'user.label', default: 'User'),
					params.id
				])
				render view: 'edit', model: [userInstance: userInstance,currentUser: currentUser,
					   roleComicBook: roleService.findByAuthority("COMICBOOK_ADMIN")]
				return
		}


			if (params.newIdentification == "" || !params.newIdentification){
				params.remove(params.newIdentification)
				params.remove(params.confirmIdentification)
			}else {
				if (!params.newIdentification.equals(params.confirmIdentification)){
					flash.message = message(code: 'user.edit.passworddontmatch')
						render view: 'edit', model: [userInstance: userInstance,currentUser: currentUser,
							roleComicBook: roleService.findByAuthority("COMICBOOK_ADMIN")]
						return
				}else {
					if (!PasswordValidator.validate(params.newIdentification)){
						flash.message = message(code: 'user.password.invalid.format', args: [
							message(code: 'user.label', default: 'User'),
							params.id
						])
						render view: 'edit', model: [userInstance: userInstance,currentUser: currentUser,
							roleComicBook: roleService.findByAuthority("COMICBOOK_ADMIN")]
						return
					}
				}
			}

			try {
				params.password = params.newIdentification
				userInstance = userService.update(userInstance,params)
			}
			catch (CinemarkException e) {
				render view : 'edit', model: [userInstance: userInstance,currentUser: currentUser,
					roleComicBook: roleService.findByAuthority("ROLE_ADMIN")]
				return
			}
			if (userInstance.hasErrors()){
				render view : 'edit', model: [ userInstance: userInstance, currentUser: currentUser,
					roleComicBook: roleService.findByAuthority("ROLE_ADMIN")]
				return
			}
			flash.message = message(code: 'default.updated.message', args: [
				message(code: 'user.label', default: 'User'),
				userInstance.id
			])
			redirect action: 'list'
		}

	def delete() {
		def userInstance = userService.get(params.id)
		User currentUser = springSecurityService.getCurrentUser()
		if (!currentUser.superAdmin) {
			flash.message = message(code: 'admin.not.delete.message')
			redirect action:"list"
			return
		}
		if (!userInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'user.label', default: 'User'),
				params.id
			])
			redirect action: 'list'
			return
		}
		
		if (userInstance.equals(currentUser)){
			flash.message = message(code: 'default.not.deleted.message', args: [
				message(code: 'user.label', default: 'User'),
				params.id
			])
			redirect action: 'list'
			return
		}
		try {
			userService.delete(userInstance)
			flash.message = message(code: 'default.deleted.message', args: [
				message(code: 'user.label', default: 'User'),
				params.id
			])
			redirect action: 'list'
			return
		}
		catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [
				message(code: 'user.label', default: 'User'),
				params.id
			])
			redirect action: 'list'
			return
		}
	}
}
