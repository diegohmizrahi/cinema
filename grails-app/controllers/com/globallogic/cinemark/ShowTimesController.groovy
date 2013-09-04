package com.globallogic.cinemark

import org.springframework.dao.DataIntegrityViolationException

import com.globallogic.cinemark.utils.DateUtils
import com.globallogic.cinemark.enums.SeatsSectionType

class ShowTimesController extends CinemarkController {
	
    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        redirect action: 'list', params: params
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [showTimesInstanceList: ShowTimes.list(params), showTimesInstanceTotal: ShowTimes.count()]
    }

    def create() {
		switch (request.method) {
		case 'GET':
        	[showTimesInstance: new ShowTimes(params)]
			break
		case 'POST':
	        def showTimesInstance = new ShowTimes(params)
			if (DateUtils.compareDateDifferenceDays(showTimesInstance.fromDate, showTimesInstance.untilDate)<0) {
				flash.message = message(code: 'shotimes.invalidDates')
				render view: 'create', model: [showTimesInstance: showTimesInstance]
				return
			}
			showTimesInstance.fromDate.clearTime()
			showTimesInstance.untilDate.clearTime()
	        if (!showTimesInstance.save(flush: true)) {
	            render view: 'create', model: [showTimesInstance: showTimesInstance]
	            return
	        }

			flash.message = message(code: 'default.created.message', args: [message(code: 'showTimes.label', default: 'ShowTimes'), showTimesInstance.id])
	        redirect action: 'show', id: showTimesInstance.id
			break
		}
    }

    def show() {
        def showTimesInstance = ShowTimes.get(params.id)
        if (!showTimesInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'showTimes.label', default: 'ShowTimes'), params.id])
            redirect action: 'list'
            return
        }

        [showTimesInstance: showTimesInstance]
    }

    def edit() {
		switch (request.method) {
		case 'GET':
	        def showTimesInstance = ShowTimes.get(params.id)
	        if (!showTimesInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'showTimes.label', default: 'ShowTimes'), params.id])
	            redirect action: 'list'
	            return
	        }

	        [showTimesInstance: showTimesInstance]
			break
		case 'POST':
	        def showTimesInstance = ShowTimes.get(params.id)
	        if (!showTimesInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'showTimes.label', default: 'ShowTimes'), params.id])
	            redirect action: 'list'
	            return
	        }

	        if (params.version) {
	            def version = params.version.toLong()
	            if (showTimesInstance.version > version) {
	                showTimesInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
	                          [message(code: 'showTimes.label', default: 'ShowTimes')] as Object[],
	                          "Another user has updated this ShowTimes while you were editing")
	                render view: 'edit', model: [showTimesInstance: showTimesInstance]
	                return
	            }
	        }

	        showTimesInstance.properties = params

	        if (!showTimesInstance.save(flush: true)) {
	            render view: 'edit', model: [showTimesInstance: showTimesInstance]
	            return
	        }

			flash.message = message(code: 'default.updated.message', args: [message(code: 'showTimes.label', default: 'ShowTimes'), showTimesInstance.id])
	        redirect action: 'show', id: showTimesInstance.id
			break
		}
    }

    def delete() {
        def showTimesInstance = ShowTimes.get(params.id)
        if (!showTimesInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'showTimes.label', default: 'ShowTimes'), params.id])
            redirect action: 'list'
            return
        }

        try {
            showTimesInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'showTimes.label', default: 'ShowTimes'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'showTimes.label', default: 'ShowTimes'), params.id])
            redirect action: 'show', id: params.id
        }
    }

}
