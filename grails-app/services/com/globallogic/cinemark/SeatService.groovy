package com.globallogic.cinemark

import com.globallogic.cinemark.enums.SeatsSectionType
import java.util.UUID

class SeatService {
	
	def bookSeats(params) {
		def schedule = Schedule.get(params.schedule)
		def bool = true
		def seatsList = new ArrayList()
		def seat
		def pendingSeats = new ArrayList()
		if (!params.seat[1].size()==1) {
			pendingSeats = params.seat as List
		} else {
			pendingSeats.add(params.seat)
		}
		println pendingSeats
		pendingSeats.each {
			seat = parseSeat(it)
			def seatSection = SeatSection.findByCinemaAndType(schedule.showTime.cinema,seat.seatSection)
			if(!seatSection || !checkValidSeat(seatSection,seat))
				bool = false
			else
				seatsList.add(seat)
				
		}
		def confirmationCode
		if (bool) {
			confirmationCode = UUID.randomUUID().toString().split("-").first()
			seatsList.each {
				def newSeat = new Seat(it)
				newSeat.email = params.email
				newSeat.identificationNumber = params.dni as Integer
				newSeat.confirmationCode = confirmationCode
				newSeat.save(flush:true, failOnError: true)
				schedule.addToTakenSeats(newSeat).save(flush:true)
			}
		}
		return confirmationCode
	}
	
	def boolean checkValidSeat(def section, def bookedSeat) {
		if (bookedSeat.row>=0 && bookedSeat.row < section.rows && bookedSeat.column >= 0 && bookedSeat.column < section.columns) {
			return true
		}
		return false
	}
	
	def parseSeat(def auxSeat) {
		def aux = auxSeat.split(",")
		def seat = [row: aux[0] as Integer, column: aux[1] as Integer,seatSection:SeatsSectionType."${aux[2].toUpperCase()}"]
		return seat
	}
	
}