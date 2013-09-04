package com.globallogic.cinemark

import java.util.List;

class Schedules {
	
	String time
	ShowTimes showTime
	static hasMany = [takenSeats: Seat]
	
	static constraints = {
		time nullable:false, blank: false, unique: ['showTime']
		showTime blank: false, nullable: false
		takenSeats nullable: true
    }
	
	def buildDTO() {
		def dto = [
			id: this.id,
			schedules: [ time: this.time ],
		]
		return dto
	}
	
	public static def buildDTOList(List<Schedules> schedules){
		List schedulesDTOList = new ArrayList();
		for (sc in schedules) {
			schedulesDTOList.add(sc.buildDTO());
		}
		return schedulesDTOList;
	}
}
