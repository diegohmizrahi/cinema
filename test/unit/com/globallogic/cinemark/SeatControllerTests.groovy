package com.globallogic.cinemark



import org.junit.*
import grails.test.mixin.*

@TestFor(SeatController)
@Mock(Seat)
class SeatControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
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
        def model = controller.create()

        assert model.seatInstance != null
    }

    void testSave() {
        controller.save()

        assert model.seatInstance != null
        assert view == '/seat/create'

        response.reset()

        populateValidParams(params)
        controller.save()

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

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/seat/list'

        populateValidParams(params)
        def seat = new Seat(params)

        assert seat.save() != null

        params.id = seat.id

        def model = controller.edit()

        assert model.seatInstance == seat
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/seat/list'

        response.reset()

        populateValidParams(params)
        def seat = new Seat(params)

        assert seat.save() != null

        // test invalid parameters in update
        params.id = seat.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/seat/edit"
        assert model.seatInstance != null

        seat.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/seat/show/$seat.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        seat.clearErrors()

        populateValidParams(params)
        params.id = seat.id
        params.version = -1
        controller.update()

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
