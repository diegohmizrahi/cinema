package com.globallogic.cinemark

import org.springframework.dao.DataIntegrityViolationException

class MovieController {

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        redirect action: 'list', params: params
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [movieInstanceList: Movie.list(params), movieInstanceTotal: Movie.count()]
    }

    def create() {
		switch (request.method) {
		case 'GET':
        	[movieInstance: new Movie(params)]
			break
		case 'POST':
	        def movieInstance = new Movie(params)
	        if (!movieInstance.save(flush: true)) {
	            render view: 'create', model: [movieInstance: movieInstance]
	            return
	        }

			flash.message = message(code: 'default.created.message', args: [message(code: 'movie.label', default: 'Movie'), movieInstance.id])
	        redirect action: 'show', id: movieInstance.id
			break
		}
    }

    def show() {
        def movieInstance = Movie.get(params.id)
        if (!movieInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'movie.label', default: 'Movie'), params.id])
            redirect action: 'list'
            return
        }

        [movieInstance: movieInstance]
    }

    def edit() {
		switch (request.method) {
		case 'GET':
	        def movieInstance = Movie.get(params.id)
	        if (!movieInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'movie.label', default: 'Movie'), params.id])
	            redirect action: 'list'
	            return
	        }

	        [movieInstance: movieInstance]
			break
		case 'POST':
	        def movieInstance = Movie.get(params.id)
	        if (!movieInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'movie.label', default: 'Movie'), params.id])
	            redirect action: 'list'
	            return
	        }

	        if (params.version) {
	            def version = params.version.toLong()
	            if (movieInstance.version > version) {
	                movieInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
	                          [message(code: 'movie.label', default: 'Movie')] as Object[],
	                          "Another user has updated this Movie while you were editing")
	                render view: 'edit', model: [movieInstance: movieInstance]
	                return
	            }
	        }

	        movieInstance.properties = params

	        if (!movieInstance.save(flush: true)) {
	            render view: 'edit', model: [movieInstance: movieInstance]
	            return
	        }

			flash.message = message(code: 'default.updated.message', args: [message(code: 'movie.label', default: 'Movie'), movieInstance.id])
	        redirect action: 'show', id: movieInstance.id
			break
		}
    }

    def delete() {
        def movieInstance = Movie.get(params.id)
        if (!movieInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'movie.label', default: 'Movie'), params.id])
            redirect action: 'list'
            return
        }

        try {
            movieInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'movie.label', default: 'Movie'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'movie.label', default: 'Movie'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}
