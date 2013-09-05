package com.globallogic.cinemark



import org.junit.*
import grails.test.mixin.*
import com.globallogic.cinemark.enums.CinemaType

@TestFor(ScheduleController)
@Mock([ShowTime,Cinema,Movie,Theater,Schedule])
class ScheduleControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
		def theater = Theater.get(1)?:new Theater(name:"test theater", address:"testaddress 123", phone: 39859394).save()
		def cinema = Cinema.get(1)?:new Cinema(cinemaNumber:1, theater:theater, cinemaType: CinemaType.XD).save()
		def movie = Movie.get(1)?: new Movie(title:"Los indestructibles 2",imdbId:"ajf43d",summary:"movie summary",actors:"many",picUrl:"http://some.url",trailerUrl:"http://some.url",genre:"Acción",director:"SomeDirector",year:2012).save()
		def showTime = ShowTime.get(1)?: new ShowTime(price:12,movie:movie,fromDate:new Date().clearTime(),untilDate:new Date().clearTime(),cinema:cinema).save()
		
		params["time"] = "22:00"
		params["showTime"] = showTime
    }

    void testIndex() {
        controller.index()
        assert "/schedule/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.schedulesInstanceList.size() == 0
        assert model.schedulesInstanceTotal == 0
    }

    void testCreate() {
        request.method = "GET"
        def model = controller.create()
        assert model.schedulesInstance != null
    }

    void testSave() {
		request.method = "GET"
		populateValidParams(params)
        def model = controller.create()

        assert model.schedulesInstance != null

		        response.reset()

        populateValidParams(params)
		request.method = "POST"
		
        controller.create()

        assert response.redirectedUrl == '/schedule/show/1'
        assert controller.flash.message != null
        assert Schedule.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/schedule/list'

        populateValidParams(params)
        def schedules = new Schedule(params)

        assert schedules.save() != null

        params.id = schedules.id

        def model = controller.show()

        assert model.schedulesInstance == schedules
    }

    void testUpdate() {
        request.method = "GET"
		controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/schedule/list'

        response.reset()

        populateValidParams(params)
        def schedules = new Schedule(params)

        assert schedules.save() != null

        // test invalid parameters in update
        params.id = schedules.id
        //TODO: add invalid values to params object
		
		request.method = "POST"
        controller.edit()
		
		response.reset()
        schedules.clearErrors()

        populateValidParams(params)
        controller.edit()

        assert response.redirectedUrl == "/schedule/show/$schedules.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        schedules.clearErrors()

        populateValidParams(params)
        params.id = schedules.id
        params.version = -1
        controller.edit()

        assert view == "/schedule/edit"
        assert model.schedulesInstance != null
        assert model.schedulesInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/schedule/list'

        response.reset()

        populateValidParams(params)
        def schedules = new Schedule(params)

        assert schedules.save() != null
        assert Schedule.count() == 1

        params.id = schedules.id

        controller.delete()

        assert Schedule.count() == 0
        assert Schedule.get(schedules.id) == null
        assert response.redirectedUrl == '/schedule/list'
    }
}
