package org.am02316.com1028.Functionality;

public class Seller extends User{

	private boolean permission = false;
	public Seller(String forename, String surname, String email, String phoneNumber, String password,
			String securityWord, boolean permission) {
		super(forename, surname, email, phoneNumber, password, securityWord);
		this.permission = permission;
	}
	public boolean isPermission() {
		return this.permission;
	}
	
	
	

}
