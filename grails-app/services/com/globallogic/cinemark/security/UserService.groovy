package com.globallogic.cinemark.security

import com.globallogic.cinemark.constants.Codes
import com.globallogic.cinemark.exceptions.CinemarkException
import com.globallogic.cinemark.utils.DateUtils


class UserService {

    def springSecurityService

    def save(params) {
        def userInstance = new User(params)
        if(params.newIdentification != params.confirmIdentification) {
            userInstance.errors.rejectValue("password","must be the same as 'ConfirmPassword'")
            return userInstance
        }
		
		userInstance.password = params.newIdentification
		userInstance.save(flush:true)
		
		if(userInstance.errors.hasErrors())
	    	return userInstance
        		
		UserRole.create(userInstance,Role.findByAuthority("ROLE_ADMIN"))

		PasswordChange passwordChange = new PasswordChange()
		passwordChange.changeDate = new Date()
		passwordChange.password = springSecurityService.encodePassword(params.newIdentification)
		userInstance.addToPasswordChange(passwordChange)
		passwordChange.save(flush: true,failOnError:true)
		userInstance = userInstance.save(flush: true)
		userInstance
    }

     def update(def userInstance,def params){
		Boolean isUsingHistoricPassword = false, passwordChange=false
		
        if (params.version) {
            def version = params.version.toLong()
            if (userInstance.version > version) {
                userInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                        [message(code: 'user.label', default: 'User')] as Object[],
                        "Another user has updated this User while you were editing")

                return userInstance
            }
        }
		def pass 
		if (params.password == null || params.password.equals("")){
			pass = userInstance.password
			userInstance.properties = params
			userInstance.password = pass
		}else{
			userInstance.properties = params
		}
		
        /** BEGIN password history check */
		if (params.newIdentification && params.newIdentification != "" && springSecurityService.encodePassword(params.newIdentification) != userInstance.password){
			if (params.newIdentification != params.confirmIdentification) {
				throw new CinemarkException(Codes.PASSWORD_CHANGE)
			}
			
			isUsingHistoricPassword = userInstance.passwordChange?.any{
				springSecurityService.encodePassword(params.newIdentification) == it.password
			}
			
			if (isUsingHistoricPassword){
				throw new CinemarkException(Codes.PASSWORD_ALREADY_USED)
			}
			passwordChange=true
		}

	
		if (!userInstance.hasErrors() && passwordChange){
			userInstance.passwordExpired = false
			def passwordChanges = userInstance.passwordChange as List
			passwordChanges?.sort{ it.id }
			if (passwordChanges.size() == 13){
				userInstance.removeFromPasswordChange(passwordChanges[0])
				passwordChanges[0].delete()
			}
			PasswordChange pc = new PasswordChange()
			pc.changeDate = new Date()
			pc.password = springSecurityService.encodePassword(params.newIdentification)
			userInstance.addToPasswordChange(pc)
			
			userInstance.password = params.newIdentification
			pc.save(flush: true, failOnError:true)
		}
		/** END password history check */
		
        userInstance.validate()
        if(userInstance.errors.hasErrors())
            return userInstance
			
		userInstance.save(flush: true)
		
    }
	
	def isPasswordExpired (User userInstance, int dateExpirationDays) {
		def passwordList =  PasswordChange.findAllByUser(userInstance)
		
		
			
		def lastPassword = passwordList.get(passwordList.size()-1)
		
		def lastPasswordDate = lastPassword.changeDate
		
		def lastPasswordAge = DateUtils.compareDateDifferenceDays (lastPasswordDate, new Date ())
		
		if (lastPasswordAge > dateExpirationDays) {
			userInstance.passwordExpired = true
			userInstance.save(true)
			return true
		}
		
		false
	}
	
	def passwordData(String pass){
		if (!pass.matches('^.*\\p{Alpha}.*$') || !pass.matches('^.*\\p{Digit}.*$') || !pass.matches('^.*[!@#$%^&].*$')) {
			return true
		}else{
			return false
		}
	}

    def delete(userInstance) {
        def roles = UserRole.findAllByUser(userInstance)
        roles.each {
            it.delete(flush: true)
        }
		
		def fps = ForgotPassword.findAllByBackOficeUser(userInstance)
		fps.each {
			it.delete(flush: true)
		}
        userInstance.delete(flush: true)
    }

    def principal() {
        def principal = springSecurityService.principal
        def user = User.get(principal.id.toInteger())
        user
    }

    def get(id){
		def idLong = id.toLong()
		if(!idLong)
			return null
        User.get(id)
    }


    def list(params) {
        User.list(params)
    }

    def count() {
        User.count()
    }
}
