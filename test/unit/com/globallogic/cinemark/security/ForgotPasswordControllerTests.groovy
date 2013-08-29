package com.globallogic.cinemark.security



import org.junit.*
import grails.test.mixin.*

@TestFor(ForgotPasswordController)
@Mock(ForgotPassword)
class ForgotPasswordControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/forgotPassword/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.forgotPasswordInstanceList.size() == 0
        assert model.forgotPasswordInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.forgotPasswordInstance != null
    }

    void testSave() {
        controller.save()

        assert model.forgotPasswordInstance != null
        assert view == '/forgotPassword/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/forgotPassword/show/1'
        assert controller.flash.message != null
        assert ForgotPassword.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/forgotPassword/list'

        populateValidParams(params)
        def forgotPassword = new ForgotPassword(params)

        assert forgotPassword.save() != null

        params.id = forgotPassword.id

        def model = controller.show()

        assert model.forgotPasswordInstance == forgotPassword
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/forgotPassword/list'

        populateValidParams(params)
        def forgotPassword = new ForgotPassword(params)

        assert forgotPassword.save() != null

        params.id = forgotPassword.id

        def model = controller.edit()

        assert model.forgotPasswordInstance == forgotPassword
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/forgotPassword/list'

        response.reset()

        populateValidParams(params)
        def forgotPassword = new ForgotPassword(params)

        assert forgotPassword.save() != null

        // test invalid parameters in update
        params.id = forgotPassword.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/forgotPassword/edit"
        assert model.forgotPasswordInstance != null

        forgotPassword.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/forgotPassword/show/$forgotPassword.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        forgotPassword.clearErrors()

        populateValidParams(params)
        params.id = forgotPassword.id
        params.version = -1
        controller.update()

        assert view == "/forgotPassword/edit"
        assert model.forgotPasswordInstance != null
        assert model.forgotPasswordInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/forgotPassword/list'

        response.reset()

        populateValidParams(params)
        def forgotPassword = new ForgotPassword(params)

        assert forgotPassword.save() != null
        assert ForgotPassword.count() == 1

        params.id = forgotPassword.id

        controller.delete()

        assert ForgotPassword.count() == 0
        assert ForgotPassword.get(forgotPassword.id) == null
        assert response.redirectedUrl == '/forgotPassword/list'
    }
}
