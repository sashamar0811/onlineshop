package org.am02316.com1028.Functionality;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {


	
	
	
	
	
	public boolean checkEmail(String email) {
		boolean checked = false;
		String emailPattern = 
				  "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				  + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
				       Pattern pattern = Pattern.compile(emailPattern);
				       Matcher matcher=pattern.matcher(email);
				       if(matcher.matches())
				       {
				           checked =true;
				       }
				       else{
				           checked =false;
				       }
		
				       
		return checked;
	}
	
	public boolean checkPassword(String password) {
		boolean checked = false;
		String passwordPattern = "^.*(?=.{4,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z]).*$";
				       Pattern pattern = Pattern.compile(passwordPattern);
				       Matcher matcher=pattern.matcher(password);
				       if(matcher.matches())
				       {
				           checked =true;
				       }
				       else{
				           checked =false;
				       }
		
		return checked;
	}
}
