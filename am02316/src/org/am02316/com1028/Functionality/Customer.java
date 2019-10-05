package org.am02316.com1028.Functionality;

public class Customer extends User{
	
	private String customerType = null;
	
	public Customer(String forename, String surname, String email, String phoneNumber, String password,String securityWord, String customerType) {
		super(forename, surname, email, phoneNumber, password, securityWord);

		
	}

	public String getCustomerType() {
		return this.customerType;
	}

	
	
	
}
