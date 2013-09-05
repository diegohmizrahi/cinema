package com.globallogic.cinemark



import org.junit.*
import grails.test.mixin.*

@TestFor(MovieController)
@Mock(Movie)
class MovieControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
		params["title"] = "Los indestructibles 2"
		params["imdbId"] = "ajf43d"
		params["summary"] = "movie summary"
		params["actors"] = "many"
		params["picUrl"] = "http://some.url"
		params["trailerUrl"] = "http://some.url"
		params["genre"] = "Acción"
		params["director"] = "SomeDirector"
		params["year"] = 2012
    }

    void testIndex() {
        controller.index()
        assert "/movie/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.movieInstanceList.size() == 0
        assert model.movieInstanceTotal == 0
    }

  void testCreate() {
		request.method = "GET"
        def model = controller.create()
		
        assert model.movieInstance != null
    }

    void testSave() {
       request.method = "GET"
	   populateValidParams(params)
        def model = controller.create()

        assert model.movieInstance != null

        response.reset()

        request.method = "POST"
        controller.create()

        assert response.redirectedUrl == '/movie/show/1'
        assert controller.flash.message != null
        assert Movie.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/movie/list'

        populateValidParams(params)
        def movie = new Movie(params)

        assert movie.save() != null

        params.id = movie.id

        def model = controller.show()

        assert model.movieInstance == movie
    }

      void testUpdate() {
		request.method = "GET"
		controller.edit()
		
        assert flash.message != null
        assert response.redirectedUrl == '/movie/list'

        response.reset()

        populateValidParams(params)
        def movie = new Movie(params)

        assert movie.save() != null

        // test invalid parameters in update
        params.id = movie.id
        //TODO: add invalid values to params object
		request.method = "POST"
        controller.edit()

        //assert model.movieInstance != null
		response.reset()
        movie.clearErrors()

        populateValidParams(params)
        controller.edit()

        assert response.redirectedUrl == "/movie/show/$movie.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        movie.clearErrors()

        populateValidParams(params)
        params.id = movie.id
        params.version = -1
        controller.edit()

        assert model.movieInstance != null
        assert model.movieInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/movie/list'

        response.reset()

        populateValidParams(params)
        def movie = new Movie(params)

        assert movie.save() != null
        assert movie.count() == 1

        params.id = movie.id

        controller.delete()

        assert movie.count() == 0
        assert Movie.get(movie.id) == null
        assert response.redirectedUrl == '/movie/list'
    }
}
