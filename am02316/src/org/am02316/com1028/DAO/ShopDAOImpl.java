package org.am02316.com1028.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.table.DefaultTableModel;

import org.am02316.com1028.BasketCustomer;
import org.am02316.com1028.SellerBooks;
import org.am02316.com1028.Functionality.Admin;
import org.am02316.com1028.Functionality.Basket;
import org.am02316.com1028.Functionality.Book;
import org.am02316.com1028.Functionality.Seller;
import org.am02316.com1028.Functionality.User;
import org.am02316.com1028.Functionality.WishList;
import org.am02316.com1028.Functionality.Customer;
import org.am02316.com1028.Functionality.PastOrder;
import org.am02316.com1028.Functionality.Rating;

/**
 * 
 * @author am02316
 *
 */
public class ShopDAOImpl implements ShopDAO {

	
	private Connection connect; 
	private Statement statement;

	public ShopDAOImpl() {
		super();

		this.connect = null;
		this.statement = null;
		this.openConnection();

	}

	@Override
	/**
	 * method to establish connection with database
	 */
	public void openConnection() { 
		try {

			if (this.connect == null || this.connect.isClosed()) {

				this.connect = DriverManager
						.getConnection("jdbc:hsqldb:file:db_data/myDBFilestore;ifexists=true;shutdown=true", "SA", "");
			}

			if (this.statement == null || this.statement.isClosed()) {
				this.statement = this.connect.createStatement();
			}

		} catch (SQLException e) {
			System.out.println("ERRRO - Failed to create a connection to the database");
			throw new RuntimeException(e);
		}

	}

	@Override
	/**
	 *  method to close the connection with database
	 * 
	 */
	public void closeConnection() { 

		try {

			if (this.statement != null) {
				this.statement.close();
			}
			if (this.connect != null) {
				this.connect.close();
			}
			System.out.println("Closed the connection to the database");
		} catch (Exception e) {
			System.out.print("ERROR-Failed to close the connection to the database");
			throw new RuntimeException(e);
		}
	}

	@Override
	/**
	 * method that takes input values and saves them to the admin and the user tables within database
	 * @param admin
	 * @return true if inputs has been saved in the tables admin and user
	 */
	public boolean registerAdmin(Admin admin) {
		boolean register = false;

		try {
			PreparedStatement pst = this.connect.prepareStatement(
					"insert into User (Forename,Surname,Email,PhoneNumber,Password,SecurityWord)values(?,?,?,?,?,?)");
			PreparedStatement pst2 = this.connect.prepareStatement("insert into Admin (Admin_email)values(?)");
			pst.setString(1, admin.getForename());
			pst.setString(2, admin.getSurname());
			pst.setString(3, admin.getEmail());
			pst.setString(4, admin.getPhoneNumber());
			pst.setString(5, admin.getPassword());
			pst.setString(6, admin.getSecurityWord());
			pst.executeUpdate();

			pst2.setString(1, admin.getEmail());
			pst2.executeUpdate();

			register = true;
		} catch (SQLException e) {
			try {
				this.connect.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();

			}
			e.printStackTrace();
		}

		return register;

	}

	@Override
	/**
	 * method that takes input values and saves them to the seller and user tables within database
	 * @param seller
	 * @return true if inputs has been saved in the tables seller and user
	 */
	public boolean registerSeller(Seller seller) {
		boolean register = false;

		try {
			PreparedStatement pst = this.connect.prepareStatement(
					"insert into User (Forename,Surname,Email,PhoneNumber,Password,SecurityWord)values(?,?,?,?,?,?)");
			PreparedStatement pst2 = this.connect.prepareStatement("insert into Seller (Seller_email)values(?)");
			pst.setString(1, seller.getForename());
			pst.setString(2, seller.getSurname());
			pst.setString(3, seller.getEmail());
			pst.setString(4, seller.getPhoneNumber());
			pst.setString(5, seller.getPassword());
			pst.setString(6, seller.getSecurityWord());
			pst.executeUpdate();

			pst2.setString(1, seller.getEmail());
			pst2.executeUpdate();

			this.connect.commit();
			register = true;
		} catch (SQLException e) {
			try {
				this.connect.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();

			}
			e.printStackTrace();
		}

		return register;

	}

	@Override
	/**
	 * method that checks if inserted email and password are in the user table
	 * @param email,password
	 * @return true if email and password matches with values in database
	 */
	public boolean login(String email, String password) {
		boolean register = false;

		try {
			PreparedStatement pst2 = this.connect
					.prepareStatement("select Email,Password from User where Email = ? and Password = ?");
			pst2.setString(1, email);
			pst2.setString(2, password);
			ResultSet res = pst2.executeQuery();

			if (res.next()) {
				this.connect.commit();
				register = true;

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return register;

	}

	@Override
	/**
	 * method that takes input values and saves them to the customer and user tables within database
	 * @param customer
	 * @return true if inputs has been saved in the tables customer and user
	 */
	public boolean registerCustomer(Customer customer) {
		boolean register = false;
		try {

			PreparedStatement pst2 = this.connect
					.prepareStatement("insert into Customer (Customer_email,CustomerType)values(?,?)");
			PreparedStatement pst = this.connect.prepareStatement(
					"insert into User (Forename,Surname,Email,PhoneNumber,Password,SecurityWord)values(?,?,?,?,?,?)");
			pst.setString(1, customer.getForename());
			pst.setString(2, customer.getSurname());
			pst.setString(3, customer.getEmail());
			pst.setString(4, customer.getPhoneNumber());
			pst.setString(5, customer.getPassword());
			pst.setString(6, customer.getSecurityWord());
			pst.executeUpdate();
			this.connect.commit();

			pst2.setString(1, customer.getEmail());
			pst2.setString(2, customer.getCustomerType());
			pst2.executeUpdate();

			this.connect.commit();
			register = true;
		} catch (SQLException e) {
			try {
				this.connect.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();

			}
			e.printStackTrace();
		}

		return register;

	}

	@Override
	/**
	 * method that takes input values and saves them to the book table within database
	 * @param book
	 * @return true if values has been saved in the book table
	 */
	public boolean addBooksToStock(Book book) {
		boolean register = false;
		try {
			PreparedStatement pst = this.connect.prepareStatement(
					"insert into Book (Id,Title,Author,Seller_email,Quantity,Price)values(?,?,?,?,?,?)");
			pst.setString(1, book.getId());
			pst.setString(2, book.getTitle());
			pst.setString(3, book.getAuthor());
			pst.setString(4, book.getSellerEmail());
			pst.setString(5, book.getQuantity());
			pst.setString(6, book.getPrice());
			pst.executeUpdate();
			this.connect.commit();
			register = true;
		} catch (SQLException e) {
			try {
				this.connect.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();

			}
			e.printStackTrace();
		}

		return register;

	}

	@Override
	/**
	 * method that selects data from table book in order to show it in jtable within gui class
	 * @return an array list of books
	 */
	public List<Book> showSellerBooks(String email) {
		List<Book> sellerBook = new ArrayList<Book>();
		Book book;

		try {
			PreparedStatement pst2 = this.connect.prepareStatement("select * from Book where Seller_email = ? ");
			
			pst2.setString(1, email);
			ResultSet res = pst2.executeQuery();

			while (res.next()) {
				book = new Book(res.getString("Id"), res.getString("Title"), res.getString("Author"),
						res.getString("Seller_email"), res.getString("Quantity"), res.getString("Price"));
				sellerBook.add(book);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return sellerBook;

	}

	@Override
	/**
	 * method that removes selected book from the table
	 * @param selected
	 * @ return true if book has been removed
	 */
	public boolean removeBooksFromStock(String selected) {
		boolean remove = false;
		try {
			PreparedStatement pst = this.connect.prepareStatement("delete from Book where Id = ?");
			pst.setString(1, selected);

			remove = true;
			pst.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return remove;
	}

	@Override
	/**
	 * method that updates quantity of selected book
	 * @param id,email,quantity
	 * @return true if a quantity has been updated within book table
	 */
	public boolean updateBookInStock(String id, String email, String quantity) {

		boolean update = false;
		try {
			PreparedStatement pst = this.connect
					.prepareStatement("update Book set Quantity = ? where Id = ? and Seller_email = ?");
			pst.setString(1, quantity);
			pst.setString(2, id);
			pst.setString(3, email);

			update = true;
			pst.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return update;
	}

	@Override
	/**
	 * method that inserts selected inputs into the basket table
	 * @param basket
	 * @return true if book has been added to the basket table
	 */
	public boolean addToBasket(Basket basket) {
		boolean add = false;
		try {
			PreparedStatement pst = this.connect.prepareStatement(
					"insert into Basket (Seller_email,Title,Quantity,Price,Customer_email)values(?,?,?,?,?)");

			pst.setString(1, basket.getSellerEmail());
			pst.setString(2, basket.getTitle());

			pst.setInt(3, basket.getQuantity());
			pst.setInt(4, basket.getPrice());
			pst.setString(5, basket.getCustomerEmail());

			pst.executeUpdate();
			this.connect.commit();
			add = true;
		} catch (SQLException e) {
			try {
				this.connect.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();

			}
			e.printStackTrace();
		}

		return add;
	}

	@Override
	/**
	 * method that inserts selected inputs into the wishList table
	 * @param wishList
	 * @return true if book has been added to the wishList
	 */
	public boolean addToWishList(WishList wishList) {

		boolean add = false;
		try {
			PreparedStatement pst = this.connect
					.prepareStatement("insert into WishList (Title,Customer_email,Quantity,Price)values(?,?,?,?)");
			pst.setString(1, wishList.getTitle());
			pst.setString(2, wishList.getCustomerEmail());
			pst.setString(3, wishList.getQuantity());
			pst.setString(4, wishList.getPrice());

			pst.executeUpdate();
			this.connect.commit();
			add = true;
		} catch (SQLException e) {
			try {
				this.connect.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();

			}
			e.printStackTrace();
		}

		return add;
	}

	@Override
	/**
	 * method that selects all values from the basket table
	 * @param email
	 * @return an array list of basket 
	 */
	public List<Basket> showBasket(String email) {

		List<Basket> basket = new ArrayList<Basket>();
		Basket bask;

		try {
			PreparedStatement pst2 = this.connect.prepareStatement("select * from Basket where Customer_email = ?");

			pst2.setString(1, email);
			ResultSet res = pst2.executeQuery();

			while (res.next()) {
				bask = new Basket(res.getInt("Id"), res.getString("Seller_email"),

						res.getString("Title"), res.getInt("Quantity"), res.getInt("Price"),
						res.getString("Customer_email"));
				basket.add(bask);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return basket;
	}

	@Override
	/**
	 * method that removes selected book from the basket table
	 * @param selected
	 * @return true book has been removed from the basket table
	 */
	public boolean removeBooksFromBasket(String selected) {
		boolean remove = false;
		try {
			PreparedStatement pst = this.connect.prepareStatement("delete from Basket where Id = ?");
			pst.setString(1, selected);

			remove = true;
			pst.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return remove;
	}

	@Override
	public boolean updateBookInBasket(String selected, int quantity) {
		boolean update = false;
		try {
			PreparedStatement pst = this.connect.prepareStatement("update Basket set Quantity = ? where Id = ? ");
			pst.setInt(1, quantity);
			pst.setString(2, selected);

			update = true;
			pst.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return update;
	}

	@Override
	public boolean checkOut(Basket basket) {
		boolean check = false;
		try {
			PreparedStatement pst = this.connect.prepareStatement("delete from Basket");

			check = true;
			pst.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return check;
	}


	@Override
	public List<PastOrder> showPastOrders(String email) {

		List<PastOrder> order = new ArrayList<PastOrder>();
		PastOrder ord;

		try {
			PreparedStatement pst2 = this.connect.prepareStatement("select * from PastOrders where Customer_email = ?");
			pst2.setString(1, email);
			ResultSet res = pst2.executeQuery();

			while (res.next()) {
				ord = new PastOrder(res.getInt("Id"),

						res.getString("Customer_email"), res.getString("Seller_email"), res.getString("Title"),

						res.getInt("Quantity")

				);
				order.add(ord);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return order;

	}

	@Override
	public List<WishList> showWishList(String email) {

		List<WishList> list = new ArrayList<WishList>();
		WishList wishList;

		try {
			PreparedStatement pst2 = this.connect.prepareStatement("select * from WishList where Customer_email = ?");
			pst2.setString(1, email);

			ResultSet res = pst2.executeQuery();

			while (res.next()) {
				wishList = new WishList(

						res.getInt("Id"), res.getString("Customer_email"), res.getString("Title"),

						res.getString("Price"), res.getString("Quantity")

				);
				list.add(wishList);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return list;

	}

	@Override
	public boolean removeBooksFromWishList(String selected) {

		boolean remove = false;
		try {
			PreparedStatement pst = this.connect.prepareStatement("delete from WishList where Id = ?");

			pst.setString(1, selected);

			remove = true;
			pst.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return remove;
	}

	@Override
	public boolean addToPastOrders(PastOrder order) {

		boolean add = false;
		try {
			PreparedStatement pst = this.connect.prepareStatement(
					"insert into PastOrders (Id,Customer_email,Seller_email,Title,Quantity)values(?,?,?,?,?)");
			pst.setInt(1, order.getId());
			pst.setString(2, order.getCustomerEmail());
			pst.setString(3, order.getSellerEmail());
			pst.setString(4, order.getTitle());
			pst.setInt(5, order.getQuantity());

			pst.executeUpdate();
			this.connect.commit();
			add = true;
		} catch (SQLException e) {
			try {
				this.connect.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();

			}
			e.printStackTrace();
		}

		return add;
	}

	@Override
	public boolean clearHistory(PastOrder order) {
		boolean clear = false;
		try {
			PreparedStatement pst = this.connect.prepareStatement("delete from PastOrders");

			clear = true;
			pst.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return clear;

	}

	@Override
	public boolean addToRating(Rating rate) {
		boolean add = false;
		try {
			PreparedStatement pst = this.connect
					.prepareStatement("insert into Rating (Id,Seller_email,Rate)values(?,?,?)");
			pst.setInt(1, rate.getId());
			pst.setString(2, rate.getSellerEmail());
			pst.setString(3, rate.getRate());

			pst.executeUpdate();
			this.connect.commit();
			add = true;
		} catch (SQLException e) {
			try {
				this.connect.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();

			}
			e.printStackTrace();
		}

		return add;
	}

	@Override
	public List<User> showSellers() {
		List<User> list = new ArrayList<User>();
		User user;

		try {
			PreparedStatement pst2 = this.connect
					.prepareStatement("SELECT * FROM User AS b INNER JOIN Seller as a ON (a.seller_email = b.email)");

			ResultSet res = pst2.executeQuery();

			while (res.next()) {
				user = new User(

						res.getString("Forename"), res.getString("Surname"), res.getString("Email"),

						res.getString("PhoneNumber"), res.getString("Password"), res.getString("SecurityWord")

				);
				list.add(user);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return list;

	}

	@Override
	public List<User> showCustomers() {
		List<User> list = new ArrayList<User>();
		User user;

		try {
			PreparedStatement pst2 = this.connect.prepareStatement(
					"SELECT * FROM User AS b INNER JOIN Customer as a ON (a.customer_email = b.email)");

			ResultSet res = pst2.executeQuery();

			while (res.next()) {
				user = new User(

						res.getString("Forename"), res.getString("Surname"), res.getString("Email"),

						res.getString("PhoneNumber"), res.getString("Password"), res.getString("SecurityWord")

				);
				list.add(user);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean givePermissionToSell(String selected, Boolean permission) {
		boolean update = false;
		try {
			PreparedStatement pst2 = this.connect
					.prepareStatement("update Seller set Permission = ? where Seller_email = ? ");
			pst2.setBoolean(1, permission);
			pst2.setString(2, selected);

			update = true;
			pst2.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return update;
	}

	@Override
	public boolean checkPermission(String email, boolean permission) {
		boolean checked = false;
		try {
			PreparedStatement pst2 = this.connect.prepareStatement(
					"select Permission, Seller_email from Seller where Permission = ? and Seller_email = ? ");
			pst2.setBoolean(1, permission);
			pst2.setString(2, email);
			ResultSet res = pst2.executeQuery();
			if (res.next()) {
				this.connect.commit();
				checked = true;

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return checked;
	}

	@Override
	/**
	 * method that updates password within user table
	 * @param email, securityWord, password
	 * @return true if password has been updated with user table
	 */
	public boolean updatePassword(String email, String securityWord, String password) {
		boolean update = false;
		try {
			PreparedStatement pst = this.connect.prepareStatement("Update User set Password = ? where Email = ? and SecurityWord = ?");
			pst.setString(1, password);
			pst.setString(2, email);
			pst.setString(3, securityWord);

			update = true;
			pst.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return update;
	}

}
