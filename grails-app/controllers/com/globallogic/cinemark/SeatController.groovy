package com.globallogic.cinemark

import org.springframework.dao.DataIntegrityViolationException

class SeatController extends CinemarkController{

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        redirect action: 'list', params: params
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [seatInstanceList: Seat.list(params), seatInstanceTotal: Seat.count()]
    }

    def create() {
		switch (request.method) {
		case 'GET':
        	[seatInstance: new Seat(params)]
			break
		case 'POST':
	        def seatInstance = new Seat(params)
	        if (!seatInstance.save(flush: true)) {
	            render view: 'create', model: [seatInstance: seatInstance]
	            return
	        }

			flash.message = message(code: 'default.created.message', args: [message(code: 'seat.label', default: 'Seat'), seatInstance.id])
	        redirect action: 'show', id: seatInstance.id
			break
		}
    }

    def show() {
        def seatInstance = Seat.get(params.id)
        if (!seatInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'seat.label', default: 'Seat'), params.id])
            redirect action: 'list'
            return
        }

        [seatInstance: seatInstance]
    }

    def edit() {
		switch (request.method) {
		case 'GET':
	        def seatInstance = Seat.get(params.id)
	        if (!seatInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'seat.label', default: 'Seat'), params.id])
	            redirect action: 'list'
	            return
	        }

	        [seatInstance: seatInstance]
			break
		case 'POST':
	        def seatInstance = Seat.get(params.id)
	        if (!seatInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'seat.label', default: 'Seat'), params.id])
	            redirect action: 'list'
	            return
	        }

	        if (params.version) {
	            def version = params.version.toLong()
	            if (seatInstance.version > version) {
	                seatInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
	                          [message(code: 'seat.label', default: 'Seat')] as Object[],
	                          "Another user has updated this Seat while you were editing")
	                render view: 'edit', model: [seatInstance: seatInstance]
	                return
	            }
	        }

	        seatInstance.properties = params

	        if (!seatInstance.save(flush: true)) {
	            render view: 'edit', model: [seatInstance: seatInstance]
	            return
	        }

			flash.message = message(code: 'default.updated.message', args: [message(code: 'seat.label', default: 'Seat'), seatInstance.id])
	        redirect action: 'show', id: seatInstance.id
			break
		}
    }

    def delete() {
        def seatInstance = Seat.get(params.id)
        if (!seatInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'seat.label', default: 'Seat'), params.id])
            redirect action: 'list'
            return
        }

        try {
            seatInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'seat.label', default: 'Seat'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'seat.label', default: 'Seat'), params.id])
            redirect action: 'show', id: params.id
        }
    }
	
	def bookSeats = {
		def resp = checkedOperation {
			if (!params.showTime || !params.email || !params.dni || !params.seat) {
				response.setStatus(400)
				return []
			}			
		}
		render resp
	}
}
