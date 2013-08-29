package com.globallogic.cinemark.security



import org.junit.*
import grails.test.mixin.*

@TestFor(PasswordChangeController)
@Mock(PasswordChange)
class PasswordChangeControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/passwordChange/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.passwordChangeInstanceList.size() == 0
        assert model.passwordChangeInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.passwordChangeInstance != null
    }

    void testSave() {
        controller.save()

        assert model.passwordChangeInstance != null
        assert view == '/passwordChange/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/passwordChange/show/1'
        assert controller.flash.message != null
        assert PasswordChange.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/passwordChange/list'

        populateValidParams(params)
        def passwordChange = new PasswordChange(params)

        assert passwordChange.save() != null

        params.id = passwordChange.id

        def model = controller.show()

        assert model.passwordChangeInstance == passwordChange
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/passwordChange/list'

        populateValidParams(params)
        def passwordChange = new PasswordChange(params)

        assert passwordChange.save() != null

        params.id = passwordChange.id

        def model = controller.edit()

        assert model.passwordChangeInstance == passwordChange
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/passwordChange/list'

        response.reset()

        populateValidParams(params)
        def passwordChange = new PasswordChange(params)

        assert passwordChange.save() != null

        // test invalid parameters in update
        params.id = passwordChange.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/passwordChange/edit"
        assert model.passwordChangeInstance != null

        passwordChange.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/passwordChange/show/$passwordChange.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        passwordChange.clearErrors()

        populateValidParams(params)
        params.id = passwordChange.id
        params.version = -1
        controller.update()

        assert view == "/passwordChange/edit"
        assert model.passwordChangeInstance != null
        assert model.passwordChangeInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/passwordChange/list'

        response.reset()

        populateValidParams(params)
        def passwordChange = new PasswordChange(params)

        assert passwordChange.save() != null
        assert PasswordChange.count() == 1

        params.id = passwordChange.id

        controller.delete()

        assert PasswordChange.count() == 0
        assert PasswordChange.get(passwordChange.id) == null
        assert response.redirectedUrl == '/passwordChange/list'
    }
}
