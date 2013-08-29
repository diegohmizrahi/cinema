package com.globallogic.cinemark.security

class PasswordChange {

	Date changeDate
	String password
	
	static belongsTo = [user:User]
		
    static mapping = {
		id generator: 'sequence', params: [sequence: 'password_change_seq']
	}
	
}
