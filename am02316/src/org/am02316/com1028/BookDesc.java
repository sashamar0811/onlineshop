package org.am02316.com1028;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.am02316.com1028.DAO.ShopDAOImpl;
import org.am02316.com1028.Functionality.Basket;
import org.am02316.com1028.Functionality.WishList;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class BookDesc extends JFrame {

	private JPanel contentPane;
	JTextField bookID;
	JTextField title;
	JTextField author;
	JTextField quantity;
	JTextField price;
	JTextField customerQuantity;
	JTextField emailCust;
	JTextField seller;
	/**
	 * Launch the application.
	 */
	int id;

	/**
	 * Create the frame.
	 * 
	 * @param email
	 */

	String email = null;

	public BookDesc(String email) {
		this.email = email;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 450);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblBookId = new JLabel("Book ID");
		lblBookId.setForeground(SystemColor.textInactiveText);
		lblBookId.setFont(new Font("Bodoni 72 Oldstyle", Font.BOLD, 15));
		lblBookId.setBounds(38, 89, 61, 16);
		contentPane.add(lblBookId);

		JLabel lblTitle = new JLabel("Title");
		lblTitle.setForeground(SystemColor.textInactiveText);
		lblTitle.setFont(new Font("Bodoni 72 Oldstyle", Font.BOLD, 15));
		lblTitle.setBounds(58, 139, 61, 16);
		contentPane.add(lblTitle);

		JLabel lblAuthor = new JLabel("Author");
		lblAuthor.setForeground(SystemColor.textInactiveText);
		lblAuthor.setFont(new Font("Bodoni 72 Oldstyle", Font.BOLD, 15));
		lblAuthor.setBounds(58, 187, 61, 16);
		contentPane.add(lblAuthor);

		JLabel lblAvailableQuantity = new JLabel("Available Quantity");
		lblAvailableQuantity.setForeground(SystemColor.textInactiveText);
		lblAvailableQuantity.setFont(new Font("Bodoni 72 Oldstyle", Font.BOLD, 15));
		lblAvailableQuantity.setBounds(317, 119, 127, 20);
		contentPane.add(lblAvailableQuantity);

		JLabel lblPricePerBook = new JLabel("Price per book(\u00A3)");
		lblPricePerBook.setForeground(SystemColor.textInactiveText);
		lblPricePerBook.setFont(new Font("Bodoni 72 Oldstyle", Font.BOLD, 15));
		lblPricePerBook.setBounds(58, 226, 133, 20);
		contentPane.add(lblPricePerBook);

		bookID = new JTextField();
		bookID.setBounds(115, 84, 176, 26);
		contentPane.add(bookID);
		bookID.setColumns(10);

		title = new JTextField();
		title.setColumns(10);
		title.setBounds(115, 134, 176, 26);
		contentPane.add(title);

		author = new JTextField();
		author.setColumns(10);
		author.setBounds(115, 182, 176, 26);
		contentPane.add(author);

		quantity = new JTextField();
		quantity.setColumns(10);
		quantity.setBounds(346, 155, 82, 26);
		contentPane.add(quantity);

		price = new JTextField();
		price.setColumns(10);
		price.setBounds(209, 222, 82, 26);
		contentPane.add(price);

		JButton btnBack = new JButton("Back");
		btnBack.setBackground(Color.WHITE);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CustomerPage customer = new CustomerPage(email);
				customer.emailCust.setText(email);
				customer.setVisible(true);

				dispose();
			}
		});
		btnBack.setForeground(new Color(178, 34, 34));
		btnBack.setFont(new Font("Bodoni 72 Oldstyle", Font.BOLD, 14));
		btnBack.setBounds(327, 46, 117, 29);
		contentPane.add(btnBack);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(178, 34, 34));
		panel.setBounds(0, 0, 478, 34);
		contentPane.add(panel);

		JButton basket = new JButton("Add to Basket");
		basket.setBackground(Color.WHITE);
		basket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				ShopDAOImpl shop = new ShopDAOImpl();
				int p = Integer.parseInt(price.getText());
				int q = Integer.parseInt(customerQuantity.getText());

				Basket basket = new Basket(id, seller.getText(), title.getText(), q, p, emailCust.getText());

				if (shop.addToBasket(basket) == true) {
					JOptionPane.showMessageDialog(null, "Book(s) has/have been added to the basket.");
				} else {
					JOptionPane.showMessageDialog(null, "Something went wrong.");
				}

			}
		});
		basket.setForeground(SystemColor.textInactiveText);
		basket.setFont(new Font("Bodoni 72 Oldstyle", Font.BOLD, 14));
		basket.setBounds(71, 321, 129, 27);
		contentPane.add(basket);

		JButton WishList = new JButton("Add to Wish List");
		WishList.setBackground(Color.WHITE);
		WishList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {

				ShopDAOImpl shop = new ShopDAOImpl();

				WishList wishList = new WishList(id, emailCust.getText(), title.getText(), price.getText(),
						customerQuantity.getText());

				if (shop.addToWishList(wishList) == true) {
					JOptionPane.showMessageDialog(null, "Book(s) has/have been added to the wish list.");
				} else {
					JOptionPane.showMessageDialog(null, "Something went wrong.");
				}

			}
		});
		WishList.setForeground(SystemColor.textInactiveText);
		WishList.setFont(new Font("Bodoni 72 Oldstyle", Font.BOLD, 14));
		WishList.setBounds(253, 321, 145, 27);
		contentPane.add(WishList);

		customerQuantity = new JTextField();
		customerQuantity.setColumns(10);
		customerQuantity.setBounds(194, 280, 45, 26);
		contentPane.add(customerQuantity);

		JLabel lblHowMany = new JLabel("Quantity(to add):");
		lblHowMany.setForeground(SystemColor.textInactiveText);
		lblHowMany.setFont(new Font("Bodoni 72 Oldstyle", Font.BOLD, 15));
		lblHowMany.setBounds(58, 283, 117, 20);
		contentPane.add(lblHowMany);

		JLabel lblOr = new JLabel("or");
		lblOr.setForeground(new Color(178, 34, 34));
		lblOr.setFont(new Font("Bodoni 72 Oldstyle", Font.BOLD, 15));
		lblOr.setBounds(209, 325, 67, 19);
		contentPane.add(lblOr);

		emailCust = new JTextField();
		emailCust.setColumns(10);
		emailCust.setBounds(10, 46, 143, 26);
		contentPane.add(emailCust);

		JLabel lblSellersEmail = new JLabel("Seller's  email");
		lblSellersEmail.setForeground(SystemColor.textInactiveText);
		lblSellersEmail.setFont(new Font("Bodoni 72 Oldstyle", Font.BOLD, 15));
		lblSellersEmail.setBounds(345, 209, 99, 20);
		contentPane.add(lblSellersEmail);

		seller = new JTextField();
		seller.setColumns(10);
		seller.setBounds(346, 240, 113, 26);
		contentPane.add(seller);
	}
}
