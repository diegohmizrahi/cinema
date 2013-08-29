package com.globallogic.cinemark.security

class User {

	transient springSecurityService

	String username
	String password
	String email
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired
	boolean superAdmin = false
	
	static hasMany = [passwordChange:PasswordChange]

	static constraints = {
		username blank: false, unique: true, size: 1..200
		password blank: false, size: 1..200
		email blank: false, email: true , unique:true,size: 1..200
	}

	static mapping = {
		table 'USER_TABLE'
		password column: '`password`'
		
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Set
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService.encodePassword(password)
	}
}
