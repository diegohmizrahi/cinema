package com.globallogic.cinemark

import org.springframework.dao.DataIntegrityViolationException

class SchedulesController {

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        redirect action: 'list', params: params
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [schedulesInstanceList: Schedules.list(params), schedulesInstanceTotal: Schedules.count()]
    }

    def create() {
		switch (request.method) {
		case 'GET':
        	[schedulesInstance: new Schedules(params)]
			break
		case 'POST':
	        def schedulesInstance = new Schedules(params)
	        if (!schedulesInstance.save(flush: true)) {
	            render view: 'create', model: [schedulesInstance: schedulesInstance]
	            return
	        }

			flash.message = message(code: 'default.created.message', args: [message(code: 'schedules.label', default: 'Schedules'), schedulesInstance.id])
	        redirect action: 'show', id: schedulesInstance.id
			break
		}
    }

    def show() {
        def schedulesInstance = Schedules.get(params.id)
        if (!schedulesInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'schedules.label', default: 'Schedules'), params.id])
            redirect action: 'list'
            return
        }

        [schedulesInstance: schedulesInstance]
    }

    def edit() {
		switch (request.method) {
		case 'GET':
	        def schedulesInstance = Schedules.get(params.id)
	        if (!schedulesInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'schedules.label', default: 'Schedules'), params.id])
	            redirect action: 'list'
	            return
	        }

	        [schedulesInstance: schedulesInstance]
			break
		case 'POST':
	        def schedulesInstance = Schedules.get(params.id)
	        if (!schedulesInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'schedules.label', default: 'Schedules'), params.id])
	            redirect action: 'list'
	            return
	        }

	        if (params.version) {
	            def version = params.version.toLong()
	            if (schedulesInstance.version > version) {
	                schedulesInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
	                          [message(code: 'schedules.label', default: 'Schedules')] as Object[],
	                          "Another user has updated this Schedules while you were editing")
	                render view: 'edit', model: [schedulesInstance: schedulesInstance]
	                return
	            }
	        }

	        schedulesInstance.properties = params

	        if (!schedulesInstance.save(flush: true)) {
	            render view: 'edit', model: [schedulesInstance: schedulesInstance]
	            return
	        }

			flash.message = message(code: 'default.updated.message', args: [message(code: 'schedules.label', default: 'Schedules'), schedulesInstance.id])
	        redirect action: 'show', id: schedulesInstance.id
			break
		}
    }

    def delete() {
        def schedulesInstance = Schedules.get(params.id)
        if (!schedulesInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'schedules.label', default: 'Schedules'), params.id])
            redirect action: 'list'
            return
        }

        try {
            schedulesInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'schedules.label', default: 'Schedules'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'schedules.label', default: 'Schedules'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}
