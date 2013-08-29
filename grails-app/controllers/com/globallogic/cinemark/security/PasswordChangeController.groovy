package com.globallogic.cinemark.security

import org.springframework.dao.DataIntegrityViolationException

class PasswordChangeController {

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        redirect action: 'list', params: params
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [passwordChangeInstanceList: PasswordChange.list(params), passwordChangeInstanceTotal: PasswordChange.count()]
    }

    def create() {
		switch (request.method) {
		case 'GET':
        	[passwordChangeInstance: new PasswordChange(params)]
			break
		case 'POST':
	        def passwordChangeInstance = new PasswordChange(params)
	        if (!passwordChangeInstance.save(flush: true)) {
	            render view: 'create', model: [passwordChangeInstance: passwordChangeInstance]
	            return
	        }

			flash.message = message(code: 'default.created.message', args: [message(code: 'passwordChange.label', default: 'PasswordChange'), passwordChangeInstance.id])
	        redirect action: 'show', id: passwordChangeInstance.id
			break
		}
    }

    def show() {
        def passwordChangeInstance = PasswordChange.get(params.id)
        if (!passwordChangeInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'passwordChange.label', default: 'PasswordChange'), params.id])
            redirect action: 'list'
            return
        }

        [passwordChangeInstance: passwordChangeInstance]
    }

    def edit() {
		switch (request.method) {
		case 'GET':
	        def passwordChangeInstance = PasswordChange.get(params.id)
	        if (!passwordChangeInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'passwordChange.label', default: 'PasswordChange'), params.id])
	            redirect action: 'list'
	            return
	        }

	        [passwordChangeInstance: passwordChangeInstance]
			break
		case 'POST':
	        def passwordChangeInstance = PasswordChange.get(params.id)
	        if (!passwordChangeInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'passwordChange.label', default: 'PasswordChange'), params.id])
	            redirect action: 'list'
	            return
	        }

	        if (params.version) {
	            def version = params.version.toLong()
	            if (passwordChangeInstance.version > version) {
	                passwordChangeInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
	                          [message(code: 'passwordChange.label', default: 'PasswordChange')] as Object[],
	                          "Another user has updated this PasswordChange while you were editing")
	                render view: 'edit', model: [passwordChangeInstance: passwordChangeInstance]
	                return
	            }
	        }

	        passwordChangeInstance.properties = params

	        if (!passwordChangeInstance.save(flush: true)) {
	            render view: 'edit', model: [passwordChangeInstance: passwordChangeInstance]
	            return
	        }

			flash.message = message(code: 'default.updated.message', args: [message(code: 'passwordChange.label', default: 'PasswordChange'), passwordChangeInstance.id])
	        redirect action: 'show', id: passwordChangeInstance.id
			break
		}
    }

    def delete() {
        def passwordChangeInstance = PasswordChange.get(params.id)
        if (!passwordChangeInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'passwordChange.label', default: 'PasswordChange'), params.id])
            redirect action: 'list'
            return
        }

        try {
            passwordChangeInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'passwordChange.label', default: 'PasswordChange'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'passwordChange.label', default: 'PasswordChange'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}
