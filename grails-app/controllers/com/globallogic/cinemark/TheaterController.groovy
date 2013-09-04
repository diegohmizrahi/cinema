package com.globallogic.cinemark

import com.globallogic.cinemark.constants.Codes
import org.springframework.dao.DataIntegrityViolationException

class TheaterController extends CinemarkController {

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        redirect action: 'list', params: params
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [theaterInstanceList: Theater.list(params), theaterInstanceTotal: Theater.count()]
    }

    def create() {
		switch (request.method) {
		case 'GET':
        	[theaterInstance: new Theater(params)]
			break
		case 'POST':
	        def theaterInstance = new Theater(params)
	        if (!theaterInstance.save(flush: true)) {
	            render view: 'create', model: [theaterInstance: theaterInstance]
	            return
	        }

			flash.message = message(code: 'default.created.message', args: [message(code: 'theater.label', default: 'Theater'), theaterInstance.id])
	        redirect action: 'show', id: theaterInstance.id
			break
		}
    }

    def show() {
        def theaterInstance = Theater.get(params.id)
        if (!theaterInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'theater.label', default: 'Theater'), params.id])
            redirect action: 'list'
            return
        }

        [theaterInstance: theaterInstance]
    }

    def edit() {
		switch (request.method) {
		case 'GET':
	        def theaterInstance = Theater.get(params.id)
	        if (!theaterInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'theater.label', default: 'Theater'), params.id])
	            redirect action: 'list'
	            return
	        }

	        [theaterInstance: theaterInstance]
			break
		case 'POST':
	        def theaterInstance = Theater.get(params.id)
	        if (!theaterInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'theater.label', default: 'Theater'), params.id])
	            redirect action: 'list'
	            return
	        }

	        if (params.version) {
	            def version = params.version.toLong()
	            if (theaterInstance.version > version) {
	                theaterInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
	                          [message(code: 'theater.label', default: 'Theater')] as Object[],
	                          "Another user has updated this Theater while you were editing")
	                render view: 'edit', model: [theaterInstance: theaterInstance]
	                return
	            }
	        }

	        theaterInstance.properties = params

	        if (!theaterInstance.save(flush: true)) {
	            render view: 'edit', model: [theaterInstance: theaterInstance]
	            return
	        }

			flash.message = message(code: 'default.updated.message', args: [message(code: 'theater.label', default: 'Theater'), theaterInstance.id])
	        redirect action: 'show', id: theaterInstance.id
			break
		}
    }

    def delete() {
        def theaterInstance = Theater.get(params.id)
        if (!theaterInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'theater.label', default: 'Theater'), params.id])
            redirect action: 'list'
            return
        }

        try {
            theaterInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'theater.label', default: 'Theater'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'theater.label', default: 'Theater'), params.id])
            redirect action: 'show', id: params.id
        }
    }
	
	def theaters = {
		def resp = checkedOperation {
			def theaters = Theater.list()
			if (!theaters) {
				response.setStatus(204)
				[]
			}else {
				buildDTOList(theaters)
			}
		}
		render resp
	}
}
