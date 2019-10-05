package org.am02316.com1028.Functionality;

public class Book {

	private String id = null;
	private String sellerEmail = null;
	private String title = null;
	private String author = null;
	private String quantity = null;
	private String price = null;

	public Book(String id, String title, String author, String sellerEmail, String quantity, String price)
			throws NullPointerException {
		super();

		if (id == null) {
			throw new NullPointerException();
		}

		if (title == null) {
			throw new NullPointerException();
		}

		if (author == null) {
			throw new NullPointerException();
		}

		if (sellerEmail == null) {
			throw new NullPointerException();
		}

		if (quantity == null) {
			throw new NullPointerException();
		}

		if (price == null) {
			throw new NullPointerException();
		}

		this.id = id;
		this.title = title;
		this.author = author;
		this.sellerEmail = sellerEmail;
		this.quantity = quantity;
		this.price = price;
	}

	public String getId() {
		return this.id;
	}

	public String getSellerEmail() {
		return this.sellerEmail;
	}

	public String getTitle() {
		return this.title;
	}

	public String getAuthor() {
		return this.author;
	}

	public String getQuantity() {
		return this.quantity;
	}

	public String getPrice() {
		return this.price;
	}

}
