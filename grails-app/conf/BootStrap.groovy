import com.globallogic.cinemark.security.PasswordChange
import com.globallogic.cinemark.security.Role
import com.globallogic.cinemark.security.User
import com.globallogic.cinemark.security.UserRole
import com.globallogic.cinemark.Theater
import com.globallogic.cinemark.Cinema
import com.globallogic.cinemark.Seat
import com.globallogic.cinemark.Movie
import com.globallogic.cinemark.SeatSection
import com.globallogic.cinemark.ShowTime
import com.globallogic.cinemark.Schedule
import com.globallogic.cinemark.enums.CinemaType
import com.globallogic.cinemark.enums.SeatsSectionType;
class BootStrap {
	
	def roleCinemark
	def springSecurityService
	def grailsApplication

    def init = { servletContext ->
		if (grailsApplication.config.dataSource.dbCreate.contains( "create")) {
			
						//START CREATE BACKOFFICE ADMINS
						def cinemarkUser = User.findByUsername("juan.cugnini") ?: new User(
								username: "juan.cugnini",
								password: "administrator1@",
								confirmPassword: "administrator1@",
								email: "juan.cugnini@globallogic.com",
								accountExpired: false,
								accountLocked: false,
								enabled: true,
								superAdmin: true,
								passwordExpired: false).save(failOnError: true, flush: true)
			
						PasswordChange passwordChange = new PasswordChange()
						passwordChange.changeDate = new Date()
						passwordChange.password = springSecurityService.encodePassword("administrator1@")
						passwordChange.user = cinemarkUser
			
						cinemarkUser.addToPasswordChange(passwordChange)
			
						roleCinemark = Role.findByAuthority("ROLE_ADMIN") ?: new Role(authority:"ROLE_ADMIN").save(failOnError: true, flush: true)
						UserRole.create(cinemarkUser, roleCinemark, true)
						
					def theater = Theater.findByName("Palmares") ?: new Theater(
							name: "Palmares",
							address: "panamericana S/N",
							phone: 94983498349
						).save(flush: true)
						
					def cinema = Cinema.findAllByTheater(theater)?: new Cinema(
						cinemaNumber: 1,
						cinemaType: CinemaType.XD,
						theater: theater
						).save(flush: true)
						
					def section1 = new SeatSection(
						type: SeatsSectionType.CENTER,
						rows: 20,
						columns: 10,
						cinema: cinema
						).save(flush:true)
					
						def section2 = new SeatSection(
							type: SeatsSectionType.RIGHT,
							rows: 20,
							columsn: 10,
							cinema: cinema
							).save(flush:true)
							
							def section3 = new SeatSection(
								type: SeatsSectionType.LEFT,
								rows: 20,
								columns: 10,
								cinema: cinema
								).save(flush:true)
								
					def movie = Movie.findByTitle("Los Indestructibles")?: new Movie(
						title: "Los indestructibles",
						imdbId: "aksdjfk",
						summary:"La primera parte del filme parte con una operación de rescate de The Expendables contra piratas somalíes que toman como rehenes a la tripulación de un barco estadounidense.",
						actors:"Jet Li, Bruce Willis, etc",
						picUrl:"http://upload.wikimedia.org/wikipedia/commons/thumb/5/57/The_Expendables_Comic-Con_Panel.jpg/800px-The_Expendables_Comic-Con_Panel.jpg",
						trailerUrl:"http://upload.wikimedia.org/wikipedia/commons/thumb/5/57/The_Expendables_Comic-Con_Panel.jpg/800px-The_Expendables_Comic-Con_Panel.jpg",
						genre:"Accion",
						director:"Sylvester Stallone",
						year: 2010
					).save(flush:true)
					
					def showTime = new ShowTime(
						price: 12,
						movie: movie,
						fromDate: new Date().clearTime(),
						untilDate: new Date().clearTime(),
						cinema: cinema
						
						).save(flush:true)
						def seat = new Seat(
							row: 2,
							seatSection: SeatsSectionType.LEFT,
							column: 3,
							email: "tetin@te.com",
							confirmationCode:"aslhj399",
							identificationNumber:39939493).save(flush:true)
						def schedule = new Schedule(
							time: "22:00",
							showTime: showTime,
							takenSeats: [seat]
							)
						showTime.addToSchedules(schedule).save(flush:true)
		}
    }
    def destroy = {
    }
}
