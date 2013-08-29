package com.globallogic.cinemark

import org.springframework.dao.DataIntegrityViolationException

class CinemaController {

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        redirect action: 'list', params: params
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [cinemaInstanceList: Cinema.list(params), cinemaInstanceTotal: Cinema.count()]
    }

    def create() {
		switch (request.method) {
		case 'GET':
        	[cinemaInstance: new Cinema(params)]
			break
		case 'POST':
	        def cinemaInstance = new Cinema(params)
	        if (!cinemaInstance.save(flush: true)) {
	            render view: 'create', model: [cinemaInstance: cinemaInstance]
	            return
	        }

			flash.message = message(code: 'default.created.message', args: [message(code: 'cinema.label', default: 'Cinema'), cinemaInstance.id])
	        redirect action: 'show', id: cinemaInstance.id
			break
		}
    }

    def show() {
        def cinemaInstance = Cinema.get(params.id)
        if (!cinemaInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'cinema.label', default: 'Cinema'), params.id])
            redirect action: 'list'
            return
        }

        [cinemaInstance: cinemaInstance]
    }

    def edit() {
		switch (request.method) {
		case 'GET':
	        def cinemaInstance = Cinema.get(params.id)
	        if (!cinemaInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cinema.label', default: 'Cinema'), params.id])
	            redirect action: 'list'
	            return
	        }

	        [cinemaInstance: cinemaInstance]
			break
		case 'POST':
	        def cinemaInstance = Cinema.get(params.id)
	        if (!cinemaInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cinema.label', default: 'Cinema'), params.id])
	            redirect action: 'list'
	            return
	        }

	        if (params.version) {
	            def version = params.version.toLong()
	            if (cinemaInstance.version > version) {
	                cinemaInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
	                          [message(code: 'cinema.label', default: 'Cinema')] as Object[],
	                          "Another user has updated this Cinema while you were editing")
	                render view: 'edit', model: [cinemaInstance: cinemaInstance]
	                return
	            }
	        }

	        cinemaInstance.properties = params

	        if (!cinemaInstance.save(flush: true)) {
	            render view: 'edit', model: [cinemaInstance: cinemaInstance]
	            return
	        }

			flash.message = message(code: 'default.updated.message', args: [message(code: 'cinema.label', default: 'Cinema'), cinemaInstance.id])
	        redirect action: 'show', id: cinemaInstance.id
			break
		}
    }

    def delete() {
        def cinemaInstance = Cinema.get(params.id)
        if (!cinemaInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'cinema.label', default: 'Cinema'), params.id])
            redirect action: 'list'
            return
        }

        try {
            cinemaInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'cinema.label', default: 'Cinema'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'cinema.label', default: 'Cinema'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}
