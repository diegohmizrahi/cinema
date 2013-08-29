package com.globallogic.cinemark.security

import org.codehaus.groovy.grails.plugins.codecs.SHA1Codec

import com.globallogic.cinemark.constants.Codes
import com.globallogic.cinemark.exceptions.CinemarkException
import com.globallogic.cinemark.utils.DateUtils
import com.globallogic.cinemark.utils.ValidatorUtils




class ForgotPasswordService {

	static transactional = true
	def publisherService
	def mailService
	def messageSource
	def userService


	private static Integer EXPIRATION_HOURS = 3;
	private static Integer EXPIRATION_TIME_CHANGE_PASS = 10;

	
	/**
	 * 
	 * @param email
	 * @param isbackOffice
	 * @param serverUrl
	 * @author romina.tornello
	 * @since 
	 * @version 
	 */
	public Map sendConfirmationMail(String email ,Boolean isbackOffice,String serverUrl){
		ValidatorUtils.validateNullOrEmpty(email);
		ForgotPassword fp = new ForgotPassword();
		String userName
		String asunto
		Map map = new HashMap();
		String url
		
		if (isbackOffice){
			def secUser = User.findByEmail(email);
			
			if(!secUser){
				throw new CinemarkException(Codes.INVALID_EMAIL)
			}
			
			fp = ForgotPassword.findByBackOficeUser(secUser)
			if(fp == null){
				fp = new ForgotPassword();
				fp.backOficeUser = secUser
				fp.tokenFP = generateToken(email);
			}
			userName = fp.backOficeUser.username;
			url =  serverUrl + "/forgotPassword/resetPasswordAction?token="
		}else {
//			email = new String(Base64Codec.decode(email))
//			User u = User.findUserByEmailErrOk(email);
//			fp = ForgotPassword.findByWebUser(u);
//			if(fp == null){
//				fp = new ForgotPassword();
//				fp.webUser = u
//				fp.tokenFP = generateToken(email);
//			}
//
//			
//			//userName = fp.webUser.firstName + " " + fp.webUser.lastName ;
//			url = serverUrl + "/site/recover.html?email=" + email + "&token="
		}

		fp.isExpired = false;
		fp.expirationTime = new Date()
		fp.save();
		url += fp.tokenFP
		//map.put("userName",userName);
		map.put("url",url);
		map.put("serverUrl",serverUrl)
		return map

	}

	/**
	 * this method generare a new token
	 * @param email
	 * @return a generaed token
	 * @author pablo.castelo
	 * @version 0.7.7.0 27/07/2011
	 * @since 0.7.7.0
	 */
	private String generateToken(String email){

		String token;
		long time = System.currentTimeMillis();
		token = (email + ";;" + time.toString());
		return SHA1Codec.encode(token);

	}
	
	/**
	 * verify if is a token valid
	 * @param token
	 * @return true if is a valid token
	 * @author romina.tornello
	 * @version 0.7.7.0 27/07/2011
	 * @since 0.7.7.0
	 */
	public Boolean validationToken(String token, Boolean isBackOfice){
		ValidatorUtils.validateNullOrEmpty(token);
		def fp
		boolean res = false;
		
		if(isBackOfice == true ){
			fp = ForgotPassword.findByTokenFP(token)
		}else{
//			def user = User.findByEmail(email); 
//			fp = ForgotPassword.findByTokenFPAndWebUser(token,user);
		}
		if (fp != null) {
			if (fp.isExpired == false ){
				def diferencia = DateUtils.getDiferencia(fp.expirationTime,new Date());
				if(diferencia.horas >= this.EXPIRATION_HOURS){
					fp.isExpired = true;
					fp.expiredTime = new Date();
					fp.save();
					throw new CinemarkException(Codes.INVALID_TOKEN);
				}else{
					fp.isExpired = true;
					fp.expiredTime = new Date();
					res = true;
				}
			}else{
				throw new CinemarkException(Codes.INVALID_TOKEN);
			}
		}else{
			throw new CinemarkException(Codes.INVALID_TOKEN);
		}
		fp.save();
		return res;
	}


	public boolean passwordLength(String pass){

		if (pass.length() < 8 || pass.length() > 64){
			return true
		}else{
			return false
		}

	}
	
	public boolean passwordData(String pass){
		if (!pass.matches('^.*\\p{Alpha}.*$') || !pass.matches('^.*\\p{Digit}.*$') || !pass.matches('^.*[!@#$%^&].*$')) {
			return true
		}else{
			return false
		}
	}
	
	public boolean passwordEquals(String pass1, String pass2){


		if (!pass1.equals(pass2)) {
			return true
		}else{
			return false
		}
	}
	public boolean userSave(User user){

		if (!user.save()) {
			return true
		}else{
			return false
		}
	}
	
	public boolean userUpdate(user,params){
	
		if (!userService.update(user, params)) {
			return true
		}else{
			return false
		}
	}
}
