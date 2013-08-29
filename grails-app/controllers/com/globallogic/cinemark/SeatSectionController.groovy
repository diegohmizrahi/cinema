package com.globallogic.cinemark

import org.springframework.dao.DataIntegrityViolationException

class SeatSectionController {

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        redirect action: 'list', params: params
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [seatSectionInstanceList: SeatSection.list(params), seatSectionInstanceTotal: SeatSection.count()]
    }

    def create() {
		switch (request.method) {
		case 'GET':
        	[seatSectionInstance: new SeatSection(params)]
			break
		case 'POST':
	        def seatSectionInstance = new SeatSection(params)
	        if (!seatSectionInstance.save(flush: true)) {
	            render view: 'create', model: [seatSectionInstance: seatSectionInstance]
	            return
	        }

			flash.message = message(code: 'default.created.message', args: [message(code: 'seatSection.label', default: 'SeatSection'), seatSectionInstance.id])
	        redirect action: 'show', id: seatSectionInstance.id
			break
		}
    }

    def show() {
        def seatSectionInstance = SeatSection.get(params.id)
        if (!seatSectionInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'seatSection.label', default: 'SeatSection'), params.id])
            redirect action: 'list'
            return
        }

        [seatSectionInstance: seatSectionInstance]
    }

    def edit() {
		switch (request.method) {
		case 'GET':
	        def seatSectionInstance = SeatSection.get(params.id)
	        if (!seatSectionInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'seatSection.label', default: 'SeatSection'), params.id])
	            redirect action: 'list'
	            return
	        }

	        [seatSectionInstance: seatSectionInstance]
			break
		case 'POST':
	        def seatSectionInstance = SeatSection.get(params.id)
	        if (!seatSectionInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'seatSection.label', default: 'SeatSection'), params.id])
	            redirect action: 'list'
	            return
	        }

	        if (params.version) {
	            def version = params.version.toLong()
	            if (seatSectionInstance.version > version) {
	                seatSectionInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
	                          [message(code: 'seatSection.label', default: 'SeatSection')] as Object[],
	                          "Another user has updated this SeatSection while you were editing")
	                render view: 'edit', model: [seatSectionInstance: seatSectionInstance]
	                return
	            }
	        }

	        seatSectionInstance.properties = params

	        if (!seatSectionInstance.save(flush: true)) {
	            render view: 'edit', model: [seatSectionInstance: seatSectionInstance]
	            return
	        }

			flash.message = message(code: 'default.updated.message', args: [message(code: 'seatSection.label', default: 'SeatSection'), seatSectionInstance.id])
	        redirect action: 'show', id: seatSectionInstance.id
			break
		}
    }

    def delete() {
        def seatSectionInstance = SeatSection.get(params.id)
        if (!seatSectionInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'seatSection.label', default: 'SeatSection'), params.id])
            redirect action: 'list'
            return
        }

        try {
            seatSectionInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'seatSection.label', default: 'SeatSection'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'seatSection.label', default: 'SeatSection'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}
