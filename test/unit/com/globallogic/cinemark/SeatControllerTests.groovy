package com.globallogic.cinemark



import org.junit.*
import grails.test.mixin.*
import com.globallogic.cinemark.enums.SeatsSectionType

@TestFor(SeatController)
@Mock(Seat)
class SeatControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
		params["row"] = 20
		params["column"] = 20
		params["seatSection"] = SeatsSectionType.LEFT
    }

    void testIndex() {
        controller.index()
        assert "/seat/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.seatInstanceList.size() == 0
        assert model.seatInstanceTotal == 0
    }

    void testCreate() {
		request.method = "GET"
        def model = controller.create()

        assert model.seatInstance != null
    }

    void testSave() {
		request.method = "GET"
        def model = controller.create()

        assert model.seatInstance != null

        response.reset()

        populateValidParams(params)
		request.method = "POST"
        controller.create()

        assert response.redirectedUrl == '/seat/show/1'
        assert controller.flash.message != null
        assert Seat.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/seat/list'

        populateValidParams(params)
        def seat = new Seat(params)

        assert seat.save() != null

        params.id = seat.id

        def model = controller.show()

        assert model.seatInstance == seat
    }

    void testUpdate() {
		request.method = "GET"
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/seat/list'

        response.reset()

        populateValidParams(params)
        def seat = new Seat(params)

        assert seat.save() != null

        // test invalid parameters in update
        params.id = seat.id
        //TODO: add invalid values to params object
		
		request.method = "POST"
        controller.edit()

		response.reset()
        seat.clearErrors()

        populateValidParams(params)
        controller.edit()

        assert response.redirectedUrl == "/seat/show/$seat.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        seat.clearErrors()

        populateValidParams(params)
        params.id = seat.id
        params.version = -1
        controller.edit()

        assert view == "/seat/edit"
        assert model.seatInstance != null
        assert model.seatInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/seat/list'

        response.reset()

        populateValidParams(params)
        def seat = new Seat(params)

        assert seat.save() != null
        assert Seat.count() == 1

        params.id = seat.id

        controller.delete()

        assert Seat.count() == 0
        assert Seat.get(seat.id) == null
        assert response.redirectedUrl == '/seat/list'
    }
}
