package com.globallogic.cinemark



import org.junit.*
import grails.test.mixin.*

@TestFor(CinemaController)
@Mock(Cinema)
class CinemaControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/cinema/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.cinemaInstanceList.size() == 0
        assert model.cinemaInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.cinemaInstance != null
    }

    void testSave() {
        controller.save()

        assert model.cinemaInstance != null
        assert view == '/cinema/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/cinema/show/1'
        assert controller.flash.message != null
        assert Cinema.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/cinema/list'

        populateValidParams(params)
        def cinema = new Cinema(params)

        assert cinema.save() != null

        params.id = cinema.id

        def model = controller.show()

        assert model.cinemaInstance == cinema
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/cinema/list'

        populateValidParams(params)
        def cinema = new Cinema(params)

        assert cinema.save() != null

        params.id = cinema.id

        def model = controller.edit()

        assert model.cinemaInstance == cinema
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/cinema/list'

        response.reset()

        populateValidParams(params)
        def cinema = new Cinema(params)

        assert cinema.save() != null

        // test invalid parameters in update
        params.id = cinema.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/cinema/edit"
        assert model.cinemaInstance != null

        cinema.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/cinema/show/$cinema.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        cinema.clearErrors()

        populateValidParams(params)
        params.id = cinema.id
        params.version = -1
        controller.update()

        assert view == "/cinema/edit"
        assert model.cinemaInstance != null
        assert model.cinemaInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/cinema/list'

        response.reset()

        populateValidParams(params)
        def cinema = new Cinema(params)

        assert cinema.save() != null
        assert Cinema.count() == 1

        params.id = cinema.id

        controller.delete()

        assert Cinema.count() == 0
        assert Cinema.get(cinema.id) == null
        assert response.redirectedUrl == '/cinema/list'
    }
}
