package com.globallogic.cinemark



import org.junit.*
import grails.test.mixin.*

@TestFor(ScheduleController)
@Mock(Schedule)
class ScheduleControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/schedules/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.schedulesInstanceList.size() == 0
        assert model.schedulesInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.schedulesInstance != null
    }

    void testSave() {
        controller.save()

        assert model.schedulesInstance != null
        assert view == '/schedules/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/schedules/show/1'
        assert controller.flash.message != null
        assert Schedule.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/schedules/list'

        populateValidParams(params)
        def schedules = new Schedule(params)

        assert schedules.save() != null

        params.id = schedules.id

        def model = controller.show()

        assert model.schedulesInstance == schedules
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/schedules/list'

        populateValidParams(params)
        def schedules = new Schedule(params)

        assert schedules.save() != null

        params.id = schedules.id

        def model = controller.edit()

        assert model.schedulesInstance == schedules
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/schedules/list'

        response.reset()

        populateValidParams(params)
        def schedules = new Schedule(params)

        assert schedules.save() != null

        // test invalid parameters in update
        params.id = schedules.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/schedules/edit"
        assert model.schedulesInstance != null

        schedules.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/schedules/show/$schedules.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        schedules.clearErrors()

        populateValidParams(params)
        params.id = schedules.id
        params.version = -1
        controller.update()

        assert view == "/schedules/edit"
        assert model.schedulesInstance != null
        assert model.schedulesInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/schedules/list'

        response.reset()

        populateValidParams(params)
        def schedules = new Schedule(params)

        assert schedules.save() != null
        assert Schedule.count() == 1

        params.id = schedules.id

        controller.delete()

        assert Schedule.count() == 0
        assert Schedule.get(schedules.id) == null
        assert response.redirectedUrl == '/schedules/list'
    }
}
