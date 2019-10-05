package org.am02316.com1028.Functionality;

public class Rating {

	private String rate = null;
	private int id = 0;
	private String sellerEmail = null;

	
	public Rating(String rate, int id, String sellerEmail) throws NullPointerException {
		super();
		if (sellerEmail == null) {
			throw new NullPointerException();
		}
		this.rate = rate;
		this.id = id;
		this.sellerEmail = sellerEmail;
	}

	public String getRate() {
		return this.rate;
	}

	public int getId() {
		return this.id;
	}

	public String getSellerEmail() {
		return this.sellerEmail;
	}

}
