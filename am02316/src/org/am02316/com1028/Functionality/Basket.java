package org.am02316.com1028.Functionality;

public class Basket {

	private int id = 0;
	private String title = null;
	private String customerEmail = null;
	private int quantity = 0;
	private int price = 0;

	
	private String sellerEmail = null;

	public Basket(int id, String sellerEmail, String title, int quantity, int price, String customerEmail) throws NullPointerException {
		super();
	
		if (sellerEmail == null) {
			throw new NullPointerException();
		}
		if (title == null) {
			throw new NullPointerException();
		}
		if (quantity == 0) {
			throw new NullPointerException();
		}
		
		if (customerEmail == null) {
			throw new NullPointerException();
		}
		this.id = id;
		this.customerEmail = customerEmail;
		this.title = title;

		this.quantity = quantity;
		this.price = price;
		this.sellerEmail = sellerEmail;
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

	public int getPrice() {
		return this.price;
	}

	public String getSellerEmail() {
		return sellerEmail;
	}

}
