package com.globallogic.cinemark



import grails.test.mixin.Mock
import grails.test.mixin.TestFor


@TestFor(TheaterController)
@Mock(Theater)
class TheaterControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        params["name"] = 'someValidName'
		params["address"] = "ruta s/n"
		params["phone"] = "389572838"
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
		request.method = "GET"
        def model = controller.create()
		
        assert model.theaterInstance != null
    }

    void testSave() {
       request.method = "GET"
	   populateValidParams(params)
        def model = controller.create()

        assert model.theaterInstance != null

        response.reset()

        request.method = "POST"
        controller.create()

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

      void testUpdate() {
		request.method = "GET"
		controller.edit()
		
        assert flash.message != null
        assert response.redirectedUrl == '/theater/list'

        response.reset()

        populateValidParams(params)
        def theater = new Theater(params)

        assert theater.save() != null

        // test invalid parameters in update
        params.id = theater.id
        //TODO: add invalid values to params object
		request.method = "POST"
        controller.edit()

        //assert model.theaterInstance != null
		response.reset()
        theater.clearErrors()

        populateValidParams(params)
        controller.edit()

        assert response.redirectedUrl == "/theater/show/$theater.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        theater.clearErrors()

        populateValidParams(params)
        params.id = theater.id
        params.version = -1
        controller.edit()

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
