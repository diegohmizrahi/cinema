package com.globallogic.cinemark



import java.util.Date;

import org.junit.*
import grails.test.mixin.*
import com.globallogic.cinemark.enums.CinemaType

@TestFor(ShowTimeController)
@Mock([ShowTime,Cinema,Movie,Theater])
class ShowTimeControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
		def theater = Theater.get(1)?:new Theater(name:"test theater", address:"testaddress 123", phone: 39859394).save()
		def cinema = Cinema.get(1)?:new Cinema(cinemaNumber:1, theater:theater, cinemaType: CinemaType.XD).save()
		def movie = Movie.get(1)?: new Movie(title:"Los indestructibles 2",imdbId:"ajf43d",summary:"movie summary",actors:"many",picUrl:"http://some.url",trailerUrl:"http://some.url",genre:"Acción",director:"SomeDirector",year:2012).save()
		params["price"] = 12 
		params["movie"] = movie
		params["fromDate"] = new Date().clearTime()
		params["untilDate"] = new Date().clearTime()
		params["cinema"] = cinema
    }

    void testIndex() {
        controller.index()
        assert "/showTime/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.showTimesInstanceList.size() == 0
        assert model.showTimesInstanceTotal == 0
    }

    void testCreate() {
		request.method = "GET"
        def model = controller.create()

        assert model.showTimesInstance != null
    }

    void testSave() {
		request.method = "GET"
		populateValidParams(params)
         def model = controller.create()

        assert model.showTimesInstance != null

        response.reset()

        populateValidParams(params)
		request.method = "POST"
        controller.create()

        assert response.redirectedUrl == '/showTime/show/1'
        assert controller.flash.message != null
        assert ShowTime.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/showTime/list'

        populateValidParams(params)
        def showTimes = new ShowTime(params)

        assert showTimes.save() != null

        params.id = showTimes.id

        def model = controller.show()

        assert model.showTimesInstance == showTimes
    }

    void testUpdate() {
       request.method = "GET"
		controller.edit()

		assert flash.message != null
        assert response.redirectedUrl == '/showTime/list'

        response.reset()

        populateValidParams(params)
        def showTimes = new ShowTime(params)

        assert showTimes.save() != null

        // test invalid parameters in update
        params.id = showTimes.id
        //TODO: add invalid values to params object
		request.method = "POST"
		controller.edit()

		response.reset()
        showTimes.clearErrors()

        populateValidParams(params)
        controller.edit()

        assert response.redirectedUrl == "/showTime/show/$showTimes.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        showTimes.clearErrors()

        populateValidParams(params)
        params.id = showTimes.id
        params.version = -1
        controller.edit()

        assert view == "/showTime/edit"
        assert model.showTimesInstance != null
        assert model.showTimesInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/showTime/list'

        response.reset()

        populateValidParams(params)
        def showTimes = new ShowTime(params)

        assert showTimes.save() != null
        assert ShowTime.count() == 1

        params.id = showTimes.id

        controller.delete()

        assert ShowTime.count() == 0
        assert ShowTime.get(showTimes.id) == null
        assert response.redirectedUrl == '/showTime/list'
    }
}
