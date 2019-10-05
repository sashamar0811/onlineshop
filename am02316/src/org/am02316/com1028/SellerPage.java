package org.am02316.com1028;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.am02316.com1028.Functionality.Book;
import org.am02316.com1028.DAO.ShopDAOImpl;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class SellerPage extends JFrame {

	private JPanel contentPane;
	private JTextField bookID;
	private JTextField title;
	private JTextField author;
	JTextField email1;
	private JTextField price;

	
	/**
	 * Create the frame.
	 * 
	 * @param email
	 */

	String email = null;

	public SellerPage(String email) {

		this.email = email;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 450);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(178, 34, 34));
		panel.setBounds(0, 326, 706, 102);
		contentPane.add(panel);

		JLabel lblNewLabel = new JLabel("ID (e.g BA67894 - first 2 letters are initials of the author)");
		lblNewLabel.setForeground(SystemColor.textInactiveText);
		lblNewLabel.setFont(lblNewLabel.getFont().deriveFont(lblNewLabel.getFont().getStyle() | Font.BOLD));
		lblNewLabel.setBounds(6, 42, 469, 20);
		contentPane.add(lblNewLabel);

		JLabel lblAddBooks = new JLabel("Add book(s):");
		lblAddBooks.setForeground(new Color(178, 34, 34));
		lblAddBooks.setFont(lblAddBooks.getFont().deriveFont(lblAddBooks.getFont().getStyle() | Font.BOLD));
		lblAddBooks.setBounds(6, 6, 105, 20);
		contentPane.add(lblAddBooks);

		bookID = new JTextField();
		bookID.setBackground(Color.WHITE);
		bookID.setBounds(16, 70, 219, 26);
		contentPane.add(bookID);
		bookID.setColumns(10);

		JLabel lblName = new JLabel("Title of the Book");
		lblName.setForeground(SystemColor.textInactiveText);
		lblName.setFont(lblName.getFont().deriveFont(lblName.getFont().getStyle() | Font.BOLD));
		lblName.setBounds(6, 108, 137, 20);
		contentPane.add(lblName);

		title = new JTextField();
		title.setColumns(10);
		title.setBackground(Color.WHITE);
		title.setBounds(16, 136, 219, 26);
		contentPane.add(title);

		JLabel lblAuthor = new JLabel("Author");
		lblAuthor.setForeground(SystemColor.textInactiveText);
		lblAuthor.setFont(lblAuthor.getFont().deriveFont(lblAuthor.getFont().getStyle() | Font.BOLD));
		lblAuthor.setBounds(6, 174, 56, 20);
		contentPane.add(lblAuthor);

		author = new JTextField();
		author.setColumns(10);
		author.setBackground(Color.WHITE);
		author.setBounds(16, 202, 219, 26);
		contentPane.add(author);

		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setForeground(SystemColor.textInactiveText);
		lblQuantity.setFont(lblQuantity.getFont().deriveFont(lblQuantity.getFont().getStyle() | Font.BOLD));
		lblQuantity.setBounds(292, 75, 70, 20);
		contentPane.add(lblQuantity);

		int[] number = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };
		JComboBox quantity = new JComboBox();
		quantity.setBackground(Color.WHITE);
		quantity.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
				"11", "12", "13", "14", "15", "16", "17", "18", "19", "20" }));

		quantity.setMaximumRowCount(30);
		quantity.setBounds(373, 70, 138, 27);
		contentPane.add(quantity);

		JButton btnAddBooksTo = new JButton("Add to the stock");
		btnAddBooksTo.setBackground(Color.WHITE);
		btnAddBooksTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				ShopDAOImpl dao = new ShopDAOImpl();
				Book book = new Book(bookID.getText(), title.getText(), author.getText(), email1.getText(),
						quantity.getSelectedItem().toString(), price.getText());
				boolean permission = true;
				if (dao.addBooksToStock(book) == true && dao.checkPermission(email, permission)) {

					JOptionPane.showMessageDialog(null, "Book(s) have been added.");

				} else {
					JOptionPane.showMessageDialog(null, "Check your inputs.");

				}

			}

		});
		btnAddBooksTo.setForeground(SystemColor.textInactiveText);
		btnAddBooksTo.setFont(btnAddBooksTo.getFont().deriveFont(btnAddBooksTo.getFont().getStyle() | Font.BOLD));
		btnAddBooksTo.setBounds(202, 269, 200, 29);
		contentPane.add(btnAddBooksTo);

		JButton btnViewYourBooks = new JButton("View your books");
		btnViewYourBooks.setBackground(Color.WHITE);
		btnViewYourBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String email = email1.getText();
				SellerBooks bk = new SellerBooks(email);
				bk.email1.setText(email);
				bk.setVisible(true);
				dispose();
			}
		});
		btnViewYourBooks.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 13));
		btnViewYourBooks.setForeground(SystemColor.textInactiveText);
		btnViewYourBooks.setBounds(519, 6, 148, 29);
		contentPane.add(btnViewYourBooks);

		email1 = new JTextField();
		email1.setColumns(10);
		email1.setBackground(Color.WHITE);
		email1.setBounds(342, 0, 148, 26);
		contentPane.add(email1);

		price = new JTextField();
		price.setColumns(10);
		price.setBackground(Color.WHITE);
		price.setBounds(292, 184, 219, 26);
		contentPane.add(price);

		JLabel lblPrice = new JLabel("Price (\u00A3)");
		lblPrice.setForeground(SystemColor.textInactiveText);
		lblPrice.setFont(lblPrice.getFont().deriveFont(lblPrice.getFont().getStyle() | Font.BOLD));
		lblPrice.setBounds(292, 152, 79, 20);
		contentPane.add(lblPrice);

	}
}
