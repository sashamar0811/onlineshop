package org.am02316.com1028.Functionality;

public class PastOrder {

	private int id = 0;
	private String title = null;
	private String customerEmail = null;
	private int quantity = 0;

	private String sellerEmail = null;

	public PastOrder(int id, String customerEmail, String sellerEmail, String title, int quantity)
			throws NullPointerException {
		super();

if (customerEmail == null) {
			throw new NullPointerException();
		}
		if (sellerEmail == null) {
			throw new NullPointerException();
		}
		if (title == null) {
			throw new NullPointerException();
		}
		if (quantity == 0) {
			throw new NullPointerException();
		}
		this.id = id;
		this.customerEmail = customerEmail;
		this.sellerEmail = sellerEmail;
		this.title = title;
		this.quantity = quantity;

		;

	}

	public int getId() {
		return this.id;
	}

	public String getTitle() {
		return this.title;
	}

	public String getCustomerEmail() {
		return this.customerEmail;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public String getSellerEmail() {
		return this.sellerEmail;
	}

}
