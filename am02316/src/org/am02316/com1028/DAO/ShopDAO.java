package org.am02316.com1028.DAO;

import java.util.List;

import org.am02316.com1028.Functionality.Admin;
import org.am02316.com1028.Functionality.Basket;
import org.am02316.com1028.Functionality.Book;
import org.am02316.com1028.Functionality.Customer;
import org.am02316.com1028.Functionality.PastOrder;
import org.am02316.com1028.Functionality.Rating;
import org.am02316.com1028.Functionality.Seller;
import org.am02316.com1028.Functionality.User;
import org.am02316.com1028.Functionality.WishList;

public interface ShopDAO {

	public void openConnection();

	public void closeConnection();

	public boolean registerSeller(Seller seller);

	public boolean registerCustomer(Customer customer);

	public boolean login(String email, String password);

	public boolean registerAdmin(Admin admin);

	public boolean addBooksToStock(Book book);

	public List<Book> showSellerBooks(String email);

	public boolean removeBooksFromStock(String selected);

	public boolean updateBookInStock(String id, String email, String quantity);

	public boolean addToBasket(Basket basketBo);

	public boolean addToWishList(WishList wishList);

	public List<Basket> showBasket(String email);

	public boolean removeBooksFromBasket(String selected);

	public boolean updateBookInBasket(String selected, int quantity);

	public boolean checkOut(Basket basket);

	public boolean addToPastOrders(PastOrder order);

	// public void calculateTotalCost(int quantity, int price);

	public List<PastOrder> showPastOrders(String email);

	public List<WishList> showWishList(String email);

	public boolean removeBooksFromWishList(String selected);

	public boolean clearHistory(PastOrder order);

	public boolean addToRating(Rating rate);

	public List<User> showSellers();

	public List<User> showCustomers();

	public boolean givePermissionToSell(String selected, Boolean permission);

	public boolean checkPermission(String email, boolean permission);

	public boolean updatePassword(String email, String securityWord, String password);

}
