package com.globallogic.cinemark



import org.junit.*
import grails.test.mixin.*
import com.globallogic.cinemark.enums.CinemaType

@TestFor(CinemaController)
@Mock([Cinema,Theater])
class CinemaControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
		def theater = Theater.get(1)?:new Theater(name:"test theater", address:"testaddress 123", phone: 39859394).save()
        params["cinemaNumber"] = 1
		params["theater"] = theater
		params["cinemaType"] = CinemaType.XD
		params["id"] = 1
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
		request.method = "GET"
        def model = controller.create()
		
        assert model.cinemaInstance != null
    }

    void testSave() {
       request.method = "GET"
	   populateValidParams(params)
        def model = controller.create()

        assert model.cinemaInstance != null

        response.reset()

        request.method = "POST"
        controller.create()

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

      void testUpdate() {
		request.method = "GET"
		controller.edit()
		
        assert flash.message != null
        assert response.redirectedUrl == '/cinema/list'

        response.reset()

        populateValidParams(params)
        def cinema = new Cinema(params)

        assert cinema.save() != null

        // test invalid parameters in update
        params.id = cinema.id
        //TODO: add invalid values to params object
		request.method = "POST"
        controller.edit()

		response.reset()
        cinema.clearErrors()

        populateValidParams(params)
		params["cinemaNumber"] = 2
        controller.edit()

        assert response.redirectedUrl == "/cinema/show/$cinema.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        cinema.clearErrors()

        populateValidParams(params)
        params.id = cinema.id
        params.version = -1
        controller.edit()

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
        assert cinema.count() == 1

        params.id = cinema.id

        controller.delete()

        assert cinema.count() == 0
        assert cinema.get(cinema.id) == null
        assert response.redirectedUrl == '/cinema/list'
    }
}
