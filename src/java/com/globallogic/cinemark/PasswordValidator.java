package com.globallogic.cinemark;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
public class PasswordValidator{
  
	  private static final String PASSWORD_PATTERN = 
              "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&/()?¿¡$%]).{8,64})";
 
	  public static boolean validate(final String password){
		  System.out.println(password !=null);
		  Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
		  Matcher matcher = pattern.matcher(password);
		  return matcher.matches();
 	  }
}