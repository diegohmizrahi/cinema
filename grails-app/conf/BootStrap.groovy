import com.globallogic.cinemark.security.PasswordChange
import com.globallogic.cinemark.security.Role
import com.globallogic.cinemark.security.User
import com.globallogic.cinemark.security.UserRole
import com.globallogic.cinemark.Theater
import com.globallogic.cinemark.Cinema
import com.globallogic.cinemark.enums.CinemaType
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
					 
		}
    }
    def destroy = {
    }
}
