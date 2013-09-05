package com.globallogic.cinemark



import org.junit.*
import grails.test.mixin.*

@TestFor(ShowTimeController)
@Mock(ShowTime)
class ShowTimeControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/showTimes/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.showTimesInstanceList.size() == 0
        assert model.showTimesInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.showTimesInstance != null
    }

    void testSave() {
        controller.save()

        assert model.showTimesInstance != null
        assert view == '/showTimes/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/showTimes/show/1'
        assert controller.flash.message != null
        assert ShowTime.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/showTimes/list'

        populateValidParams(params)
        def showTimes = new ShowTime(params)

        assert showTimes.save() != null

        params.id = showTimes.id

        def model = controller.show()

        assert model.showTimesInstance == showTimes
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/showTimes/list'

        populateValidParams(params)
        def showTimes = new ShowTime(params)

        assert showTimes.save() != null

        params.id = showTimes.id

        def model = controller.edit()

        assert model.showTimesInstance == showTimes
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/showTimes/list'

        response.reset()

        populateValidParams(params)
        def showTimes = new ShowTime(params)

        assert showTimes.save() != null

        // test invalid parameters in update
        params.id = showTimes.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/showTimes/edit"
        assert model.showTimesInstance != null

        showTimes.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/showTimes/show/$showTimes.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        showTimes.clearErrors()

        populateValidParams(params)
        params.id = showTimes.id
        params.version = -1
        controller.update()

        assert view == "/showTimes/edit"
        assert model.showTimesInstance != null
        assert model.showTimesInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/showTimes/list'

        response.reset()

        populateValidParams(params)
        def showTimes = new ShowTime(params)

        assert showTimes.save() != null
        assert ShowTime.count() == 1

        params.id = showTimes.id

        controller.delete()

        assert ShowTime.count() == 0
        assert ShowTime.get(showTimes.id) == null
        assert response.redirectedUrl == '/showTimes/list'
    }
}
