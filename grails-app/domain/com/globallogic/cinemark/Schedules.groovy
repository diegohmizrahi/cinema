package com.globallogic.cinemark

class Schedules {
	
	String time
	ShowTimes showTime
	
	static constraints = {
		time nullable:false, blank: false, unique: ['showTime']
		showTime blank: false, nullable: false
    }
}
