package com.globallogic.cinemark.security

import org.springframework.dao.DataIntegrityViolationException

class UserRoleController {

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        redirect action: 'list', params: params
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [userRoleInstanceList: UserRole.list(params), userRoleInstanceTotal: UserRole.count()]
    }

    def create() {
		switch (request.method) {
		case 'GET':
        	[userRoleInstance: new UserRole(params)]
			break
		case 'POST':
	        def userRoleInstance = new UserRole(params)
	        if (!userRoleInstance.save(flush: true)) {
	            render view: 'create', model: [userRoleInstance: userRoleInstance]
	            return
	        }

			flash.message = message(code: 'default.created.message', args: [message(code: 'userRole.label', default: 'UserRole'), userRoleInstance.id])
	        redirect action: 'show', id: userRoleInstance.id
			break
		}
    }

    def show() {
        def userRoleInstance = UserRole.get(params.id)
        if (!userRoleInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'userRole.label', default: 'UserRole'), params.id])
            redirect action: 'list'
            return
        }

        [userRoleInstance: userRoleInstance]
    }

    def edit() {
		switch (request.method) {
		case 'GET':
	        def userRoleInstance = UserRole.get(params.id)
	        if (!userRoleInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'userRole.label', default: 'UserRole'), params.id])
	            redirect action: 'list'
	            return
	        }

	        [userRoleInstance: userRoleInstance]
			break
		case 'POST':
	        def userRoleInstance = UserRole.get(params.id)
	        if (!userRoleInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'userRole.label', default: 'UserRole'), params.id])
	            redirect action: 'list'
	            return
	        }

	        if (params.version) {
	            def version = params.version.toLong()
	            if (userRoleInstance.version > version) {
	                userRoleInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
	                          [message(code: 'userRole.label', default: 'UserRole')] as Object[],
	                          "Another user has updated this UserRole while you were editing")
	                render view: 'edit', model: [userRoleInstance: userRoleInstance]
	                return
	            }
	        }

	        userRoleInstance.properties = params

	        if (!userRoleInstance.save(flush: true)) {
	            render view: 'edit', model: [userRoleInstance: userRoleInstance]
	            return
	        }

			flash.message = message(code: 'default.updated.message', args: [message(code: 'userRole.label', default: 'UserRole'), userRoleInstance.id])
	        redirect action: 'show', id: userRoleInstance.id
			break
		}
    }

    def delete() {
        def userRoleInstance = UserRole.get(params.id)
        if (!userRoleInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'userRole.label', default: 'UserRole'), params.id])
            redirect action: 'list'
            return
        }

        try {
            userRoleInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'userRole.label', default: 'UserRole'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'userRole.label', default: 'UserRole'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}
