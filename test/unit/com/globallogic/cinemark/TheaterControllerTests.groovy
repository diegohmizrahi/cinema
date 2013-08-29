package com.globallogic.cinemark



import org.junit.*
import grails.test.mixin.*

@TestFor(TheaterController)
@Mock(Theater)
class TheaterControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/theater/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.theaterInstanceList.size() == 0
        assert model.theaterInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.theaterInstance != null
    }

    void testSave() {
        controller.save()

        assert model.theaterInstance != null
        assert view == '/theater/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/theater/show/1'
        assert controller.flash.message != null
        assert Theater.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/theater/list'

        populateValidParams(params)
        def theater = new Theater(params)

        assert theater.save() != null

        params.id = theater.id

        def model = controller.show()

        assert model.theaterInstance == theater
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/theater/list'

        populateValidParams(params)
        def theater = new Theater(params)

        assert theater.save() != null

        params.id = theater.id

        def model = controller.edit()

        assert model.theaterInstance == theater
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/theater/list'

        response.reset()

        populateValidParams(params)
        def theater = new Theater(params)

        assert theater.save() != null

        // test invalid parameters in update
        params.id = theater.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/theater/edit"
        assert model.theaterInstance != null

        theater.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/theater/show/$theater.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        theater.clearErrors()

        populateValidParams(params)
        params.id = theater.id
        params.version = -1
        controller.update()

        assert view == "/theater/edit"
        assert model.theaterInstance != null
        assert model.theaterInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/theater/list'

        response.reset()

        populateValidParams(params)
        def theater = new Theater(params)

        assert theater.save() != null
        assert Theater.count() == 1

        params.id = theater.id

        controller.delete()

        assert Theater.count() == 0
        assert Theater.get(theater.id) == null
        assert response.redirectedUrl == '/theater/list'
    }
}
