package com.globallogic.cinemark

import org.springframework.dao.DataIntegrityViolationException

class SchedulesController extends CinemarkController {

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST', movieSchedules:'GET']
	
	def SchedulesService

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
			if ((params.hours as Integer) < 0 || (params.hours  as Integer) > 24 || (params.minutes  as Integer) < 0 || (params.minutes  as Integer) > 59) {
				flash.message = message(code: 'schedules.time.error')
				render view: 'create', model: [schedulesInstance: schedulesInstance]
			}
			if (params.hours?.length() < 2) 
				params.hours = '0' + params.hours
			
			if (params.minutes?.length() < 2)
				params.minutes = '0' + params.minutes
				
			schedulesInstance.time = params.hours + ":" + params.minutes 

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
	
	def movieSchedules = {
		
		def resp = checkedOperation {
			if (!params.movie || !params.theater) {
					response.setStatus(400)
					return []
			}
			def movie = Movie.get(params.movie)
			def theater = Theater.get(params.theater)
			
			if (!movie || !theater) {
				response.setStatus(204)
				return []
			}
				
			
			
			return schedulesService.getNextSchedules(theater,movie)
		}
		render resp
	}
}
