package com.globallogic.cinemark



import org.junit.*
import grails.test.mixin.*
import com.globallogic.cinemark.enums.SeatsSectionType
import com.globallogic.cinemark.enums.CinemaType

@TestFor(SeatSectionController)
@Mock([SeatSection,Theater,Cinema])
class SeatSectionControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
		def theater = Theater.get(1)?:new Theater(name:"test theater", address:"testaddress 123", phone: 39859394).save()
		def cinema = Cinema.get(1)?:new Cinema(cinemaNumber:1, theater:theater, cinemaType: CinemaType.XD).save()
		params["rows"] = 1
		params["columns"] = theater
		params["type"] = SeatsSectionType.LEFT.section
		params["cinema"] = cinema
    }

    void testIndex() {
        controller.index()
        assert "/seatSection/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.seatSectionInstanceList.size() == 0
        assert model.seatSectionInstanceTotal == 0
    }

    void testCreate() {
		request.method = "GET"
        def model = controller.create()

        assert model.seatSectionInstance != null
    }

    void testSave() {
		request.method = "GET"
		populateValidParams(params)
        def model = controller.create()

        assert model.seatSectionInstance != null
        response.reset()

        populateValidParams(params)
		request.method = "POST"
        controller.create()

        assert response.redirectedUrl == '/seatSection/show/1'
        assert controller.flash.message != null
        assert SeatSection.count() == 1
    }
	
    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/seatSection/list'

        populateValidParams(params)
        def seatSection = new SeatSection(params)

        assert seatSection.save() != null

        params.id = seatSection.id

        def model = controller.show()

        assert model.seatSectionInstance == seatSection
		
		controller.show()
		
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/seatSection/list'

        populateValidParams(params)
        def seatSection = new SeatSection(params)

        assert seatSection.save() != null

        params.id = seatSection.id

        def model = controller.edit()

        assert model.seatSectionInstance == seatSection
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/seatSection/list'

        response.reset()

        populateValidParams(params)
        def seatSection = new SeatSection(params)

        assert seatSection.save() != null

        // test invalid parameters in update
        params.id = seatSection.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/seatSection/edit"
        assert model.seatSectionInstance != null

        seatSection.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/seatSection/show/$seatSection.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        seatSection.clearErrors()

        populateValidParams(params)
        params.id = seatSection.id
        params.version = -1
        controller.update()

        assert view == "/seatSection/edit"
        assert model.seatSectionInstance != null
        assert model.seatSectionInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/seatSection/list'

        response.reset()

        populateValidParams(params)
        def seatSection = new SeatSection(params)

        assert seatSection.save() != null
        assert SeatSection.count() == 1

        params.id = seatSection.id

        controller.delete()

        assert SeatSection.count() == 0
        assert SeatSection.get(seatSection.id) == null
        assert response.redirectedUrl == '/seatSection/list'
    }
}
