package com.globallogic.cinemark.security

class ForgotPassword {

	//User webUser;
	User backOficeUser;
	String tokenFP;
	Boolean isExpired = false;
	Date expirationTime;
	Boolean passwordChanged = false;
	Date expiredTime;
	
	
	static mapping = {
		table 'FP_TABLE'
		id generator: 'sequence', params: [sequence: 'fp_sequence']
		version false
	}
	
	static constraints = {
//		webUser(nullable:true)
		backOficeUser(nullable:true)
		expirationTime(nullable:false)
		isExpired(nullable:false)
		tokenFP(nullable:false)
		passwordChanged(nullable:true)
		expiredTime(nullable:true)
	}

}
