import grails.plugins.springsecurity.SecurityConfigType

import java.awt.Color
import java.awt.Font

import com.octo.captcha.component.image.backgroundgenerator.GradientBackgroundGenerator
import com.octo.captcha.component.image.color.SingleColorGenerator
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator
import com.octo.captcha.component.image.textpaster.NonLinearTextPaster
import com.octo.captcha.component.image.wordtoimage.ComposedWordToImage
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator
import com.octo.captcha.engine.GenericCaptchaEngine
import com.octo.captcha.image.gimpy.GimpyFactory
import com.octo.captcha.service.multitype.GenericManageableCaptchaService
// locations to search for config files that get merged into the main config;
// config files can be ConfigSlurper scripts, Java properties files, or classes
// in the classpath in ConfigSlurper format

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [
    all:           '*/*',
    atom:          'application/atom+xml',
    css:           'text/css',
    csv:           'text/csv',
    form:          'application/x-www-form-urlencoded',
    html:          ['text/html','application/xhtml+xml'],
    js:            'text/javascript',
    json:          ['application/json', 'text/json'],
    multipartForm: 'multipart/form-data',
    rss:           'application/rss+xml',
    text:          'text/plain',
    xml:           ['text/xml', 'application/xml']
]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// What URL patterns should be processed by the resources plugin
grails.resources.adhoc.patterns = ['/images/*', '/css/*', '/js/*', '/plugins/*']

// The default codec used to encode data with ${}
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"
// enable Sitemesh preprocessing of GSP pages
grails.views.gsp.sitemesh.preprocess = true
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// configure auto-caching of queries by default (if false you can cache individual queries with 'cache: true')
grails.hibernate.cache.queries = false

environments {
    development {
        grails.logging.jul.usebridge = true
    }
    production {
        grails.logging.jul.usebridge = false
        // TODO: grails.serverURL = "http://www.changeme.com"
    }
}
grails {
	mail {
		host = "smtp.gmail.com"
		port = 465
		username = "cubika.desa@gmail.com"
		password = "Cubika2009"
		from = "Cubika"
		props = ["mail.smtp.auth":"true",
					"mail.smtp.socketFactory.port":"465",
					"mail.smtp.socketFactory.class":"javax.net.ssl.SSLSocketFactory",
					"mail.smtp.socketFactory.fallback":"false"]
	}
}

// log4j configuration
log4j = {
    // Example of changing the log pattern for the default console appender:
    //
    //appenders {
    //    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    //}

    error  'org.codehaus.groovy.grails.web.servlet',        // controllers
           'org.codehaus.groovy.grails.web.pages',          // GSP
           'org.codehaus.groovy.grails.web.sitemesh',       // layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping',        // URL mapping
           'org.codehaus.groovy.grails.commons',            // core / classloading
           'org.codehaus.groovy.grails.plugins',            // plugins
           'org.codehaus.groovy.grails.orm.hibernate',      // hibernate integration
           'org.springframework',
           'org.hibernate',
           'net.sf.ehcache.hibernate'
}

grails.plugins.springsecurity.userLookup.userDomainClassName = 'com.globallogic.cinemark.security.User'
grails.plugins.springsecurity.userLookup.authorityJoinClassName = 'com.globallogic.cinemark.security.UserRole'
grails.plugins.springsecurity.authority.className = 'com.globallogic.cinemark.security.Role'
grails.plugins.springsecurity.successHandler.defaultTargetUrl = "/login/authSuccess"
//grails.plugins.springsecurity.securityConfigType = "InterceptUrlMap"
grails.plugins.springsecurity.logout.afterLogoutUrl = '/login/auth'
grails.security.passwordValidUrl = "/login/auth"
grails.plugins.springsecurity.successHandler.alwaysUseDefault = true
grails.plugins.springsecurity.successHandler.alwaysUseDefaultTargetUrl = true

grails.plugins.springsecurity.useSessionFixationPrevention = true


grails.plugins.springsecurity.securityConfigType = SecurityConfigType.InterceptUrlMap
grails.plugins.springsecurity.interceptUrlMap = [
	'/login/**':  ['IS_AUTHENTICATED_ANONYMOUSLY'],
	'/logout/**': ['IS_AUTHENTICATED_ANONYMOUSLY'],
	'/images/**': ['IS_AUTHENTICATED_ANONYMOUSLY'],
	'/javascripts/**': ['IS_AUTHENTICATED_ANONYMOUSLY'],
	'/css/**': ['IS_AUTHENTICATED_ANONYMOUSLY'],
	'/stylesheets/**': ['IS_AUTHENTICATED_ANONYMOUSLY'],
	'/assets/**': ['IS_AUTHENTICATED_ANONYMOUSLY'],
	'/services/**': ['IS_AUTHENTICATED_ANONYMOUSLY'],
	'/captcha/**': ['IS_AUTHENTICATED_ANONYMOUSLY'],
	'/jcaptcha/**': ['IS_AUTHENTICATED_ANONYMOUSLY'],
	'/user/**': ['ROLE_ADMIN' ,'IS_AUTHENTICATED_FULLY'],
	'/cinema/**': ['ROLE_ADMIN' ,'IS_AUTHENTICATED_FULLY'],
	'/seat/**': ['ROLE_ADMIN' ,'IS_AUTHENTICATED_FULLY'],
	'/seatSection/**': ['ROLE_ADMIN' ,'IS_AUTHENTICATED_FULLY'],
	'/showTimes/**': ['ROLE_ADMIN' ,'IS_AUTHENTICATED_FULLY'],
	'/theater/**': ['ROLE_ADMIN' ,'IS_AUTHENTICATED_FULLY'],
	'/movie/**': ['ROLE_ADMIN' ,'IS_AUTHENTICATED_FULLY'],
	'/index': ['ROLE_ADMIN' ,'IS_AUTHENTICATED_FULLY'],
	'/phase/**': ['ROLE_ADMIN' ,'IS_AUTHENTICATED_FULLY'],
	'/;**': ['ROLE_ADMIN' ,'IS_AUTHENTICATED_FULLY'],
	'/index': ['ROLE_ADMIN' ,'IS_AUTHENTICATED_FULLY'],
	'/acp': ['ROLE_ADMIN' ,'IS_AUTHENTICATED_FULLY'],
]

grails.plugins.springsecurity.auth.forceHttps = true
grails.plugins.springsecurity.secureChannel.definition = [
	'/**':'REQUIRES_SECURE_CHANNEL'
 ]



jcaptchas {
	image = new GenericManageableCaptchaService (
			new GenericCaptchaEngine(
				new GimpyFactory(
					new RandomWordGenerator("ABCDFGHJKLMNPQRSTVWXYZ123456789"),
					new ComposedWordToImage(
						new RandomFontGenerator(20,30,[new Font ("Arial" ,0,10)]as Font[]),
						new GradientBackgroundGenerator(
							160,40,	new SingleColorGenerator(new Color(0,60,0)), new SingleColorGenerator(new Color(20,20,20))
						), new NonLinearTextPaster(6,6,new Color(0,255,0))
					)
				)
			),180,180000)
}