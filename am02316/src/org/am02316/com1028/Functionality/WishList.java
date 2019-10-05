package org.am02316.com1028.Functionality;

public class WishList {

	private int id = 0;
	private String title = null;
	private String customerEmail = null;
	private String quantity = null;
	private String price = null;

	public WishList(int id, String customerEmail, String title, String price, String quantity)
			throws NullPointerException {
		super();

		if (customerEmail == null) {
			throw new NullPointerException();
		}
		if (title == null) {
			throw new NullPointerException();
		}
		if (quantity == null) {
			throw new NullPointerException();
		}
		this.id = id;
		this.customerEmail = customerEmail;
		this.title = title;
		this.quantity = quantity;
		this.price = price;
	}

	public String getTitle() {
		return this.title;
	}

	public String getCustomerEmail() {
		return this.customerEmail;
	}

	public String getQuantity() {
		return this.quantity;
	}

	public String getPrice() {
		return this.price;
	}

	public int getId() {
		return this.id;
	}

}
