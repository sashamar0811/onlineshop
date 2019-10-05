package org.am02316.com1028.Functionality;



public class User {

	private String forename;
	private String surname;
	private String email;
	private String phoneNumber;
	private String password;
	private String securityWord;

	public User(String forename, String surname, String email, String phoneNumber, String password, String securityWord)
			throws NullPointerException {
		super();

		if (forename == null) {
			throw new NullPointerException();
		}

		if (surname == null) {
			throw new NullPointerException();
		}

		if (email == null) {
			throw new NullPointerException();
		}

		if (phoneNumber == null) {
			throw new NullPointerException();
		}

		if (password == null) {
			throw new NullPointerException();
		}

		if (securityWord == null) {
			throw new NullPointerException();
		}
		this.forename = forename;

		this.surname = surname;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.securityWord = securityWord;
	}

	public String getForename() {
		return this.forename;
	}

	public String getSurname() {
		return this.surname;
	}

	public String getEmail() {
		return this.email;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	
	public String getSecurityWord() {
		return this.securityWord;
	}

	public String getPassword() {
		return this.password;
	}

}
