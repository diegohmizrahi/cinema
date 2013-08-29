package com.globallogic.cinemark

class Seat {
	
	String row
	Integer seatNumber
	String email
	String confirmationCode
	Integer identificationNumber
	ShowTimes showTime

	
    static constraints = {
		email nullable: true,email: true
		confirmationCode nullable: true
		identificationNumber nullable: true
		row nullable: false, blank: false, unique: ['seatNumber','showTime']
		seatNumber nullable: false, blank: false
    }
}
