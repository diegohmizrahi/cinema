package com.globallogic.cinemark

import com.globallogic.cinemark.enums.SeatsSectionType
class ScheduleService {
	
	def getNextSchedules(def theater, def movie) {
		def showTimes = ShowTime.executeQuery("FROM ShowTime st WHERE st.cinema.theater = :theater AND st.movie = :movie",[theater:theater, movie: movie])
		def schedules
		List auxList = new ArrayList()
		showTimes?.each { st ->
			def aux = [:]
			schedules = Schedule.findAllByShowTime(st)
			schedules.each { sc ->
				aux = sc.buildDTO()
				st.cinema?.seatSections?.each { sec ->
					aux."${sec.type.section.toLowerCase()}"= sec.buildDTO()
					sc.takenSeats?.each { taken ->
						if (taken?.seatSection.equals(sec.type)) {
							aux."${sec.type.section.toLowerCase()}".takenSeats.add(taken.buildDTO())
							
						}
					}
				}
				auxList.add(aux)
			}
			
			
		}
		auxList
	}
	
}