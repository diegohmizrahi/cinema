package com.globallogic.cinemark



import org.junit.*
import grails.test.mixin.*

@TestFor(MovieController)
@Mock(Movie)
class MovieControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
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
        def model = controller.create()

        assert model.movieInstance != null
    }

    void testSave() {
        controller.save()

        assert model.movieInstance != null
        assert view == '/movie/create'

        response.reset()

        populateValidParams(params)
        controller.save()

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

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/movie/list'

        populateValidParams(params)
        def movie = new Movie(params)

        assert movie.save() != null

        params.id = movie.id

        def model = controller.edit()

        assert model.movieInstance == movie
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/movie/list'

        response.reset()

        populateValidParams(params)
        def movie = new Movie(params)

        assert movie.save() != null

        // test invalid parameters in update
        params.id = movie.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/movie/edit"
        assert model.movieInstance != null

        movie.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/movie/show/$movie.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        movie.clearErrors()

        populateValidParams(params)
        params.id = movie.id
        params.version = -1
        controller.update()

        assert view == "/movie/edit"
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
        assert Movie.count() == 1

        params.id = movie.id

        controller.delete()

        assert Movie.count() == 0
        assert Movie.get(movie.id) == null
        assert response.redirectedUrl == '/movie/list'
    }
}
