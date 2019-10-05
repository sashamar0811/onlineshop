package org.am02316.com1028;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Font;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.am02316.com1028.DAO.ShopDAOImpl;


import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomerPage extends JFrame {

	private JPanel contentPane;
	JTextField emailCust;
	public JTable table;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */

	String email = null;

	public CustomerPage(String email) {

		this.email = email;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 450);
		contentPane = new JPanel();
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg) {

			}
		});
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(new Color(178, 34, 34));
		menuBar.setBackground(new Color(248, 248, 255));
		menuBar.setBounds(470, 0, 283, 59);
		contentPane.add(menuBar);

		JMenu mnWishList = new JMenu("Wish List");
		mnWishList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg) {
				ShopDAOImpl shop = new ShopDAOImpl();

				WishList wish = new WishList(email);

				DefaultTableModel model = new DefaultTableModel();

				Object[] rowData = new Object[4];

				Object[] column = new Object[4];

				column[0] = "Id";
				column[1] = "Title";
				column[2] = "Quantity";
				column[3] = "Price per book (£)";
				model.setColumnIdentifiers(column);

				for (int i = 0; i < shop.showWishList(email).size(); i++) {

					rowData[0] = shop.showWishList(email).get(i).getId();

					rowData[1] = shop.showWishList(email).get(i).getTitle();
					rowData[2] = shop.showWishList(email).get(i).getQuantity();

					rowData[3] = shop.showWishList(email).get(i).getPrice();

					model.addRow(rowData);

				}

				wish.table.setModel(model);
				wish.email1.setText(email);
				wish.setVisible(true);
				dispose();

			}

		});
		mnWishList.setForeground(new Color(178, 34, 34));
		mnWishList.setFont(new Font("Bodoni 72 Oldstyle", Font.BOLD, 14));
		menuBar.add(mnWishList);

		JMenu mnNewMenu = new JMenu("Basket");
		mnNewMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg) {
				ShopDAOImpl shop = new ShopDAOImpl();

				BasketCustomer basket = new BasketCustomer(email);

				DefaultTableModel model = new DefaultTableModel();

				Object[] rowData = new Object[5];

				Object[] column = new Object[5];

				column[0] = "Id";
				column[1] = "Seller's email";
				column[2] = "Title";
				column[3] = "Quantity";
				column[4] = "Price per book (£)";
				model.setColumnIdentifiers(column);

				for (int i = 0; i < shop.showBasket(email).size(); i++) {

					rowData[0] = shop.showBasket(email).get(i).getId();

					rowData[1] = shop.showBasket(email).get(i).getSellerEmail();
					rowData[2] = shop.showBasket(email).get(i).getTitle();
					rowData[3] = shop.showBasket(email).get(i).getQuantity();
					rowData[4] = shop.showBasket(email).get(i).getPrice();

					model.addRow(rowData);

				}

				basket.table1.setModel(model);
				basket.emailCust.setText(email);
				basket.setVisible(true);
				dispose();

			}
		});
		mnNewMenu.setFont(new Font("Bodoni 72 Oldstyle", Font.BOLD, 15));
		menuBar.add(mnNewMenu);

		JMenu mnPastOrders = new JMenu("Past Orders");
		mnPastOrders.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ShopDAOImpl shop = new ShopDAOImpl();

				PastOrders order = new PastOrders(email);

				DefaultTableModel model = new DefaultTableModel();

				Object[] rowData = new Object[4];

				Object[] column = new Object[4];

				column[0] = "Id";
				column[1] = "Seller's email";
				column[2] = "Title";
				column[3] = "Quantity";

				model.setColumnIdentifiers(column);

				for (int i = 0; i < shop.showPastOrders(email).size(); i++) {

					rowData[0] = shop.showPastOrders(email).get(i).getId();

					rowData[1] = shop.showPastOrders(email).get(i).getSellerEmail();
					rowData[2] = shop.showPastOrders(email).get(i).getTitle();
					rowData[3] = shop.showPastOrders(email).get(i).getQuantity();

					model.addRow(rowData);

				}
				order.table.setModel(model);
				order.email1.setText(email);
				order.setVisible(true);
				dispose();
			}
		});
		mnPastOrders.setFont(new Font("Bodoni 72 Oldstyle", Font.BOLD, 14));
		menuBar.add(mnPastOrders);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(178, 34, 34));
		panel.setBounds(0, 367, 712, 82);
		contentPane.add(panel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		scrollPane.setBounds(0, 167, 678, 202);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String email = emailCust.getText();
				BookDesc d = new BookDesc(email);
				int index = table.getSelectedRow();
				TableModel model = table.getModel();
				String id = model.getValueAt(index, 0).toString();
				String title = model.getValueAt(index, 1).toString();
				String author = model.getValueAt(index, 2).toString();
				String quantity = model.getValueAt(index, 3).toString();
				String price = model.getValueAt(index, 4).toString();
				String emailSeller = model.getValueAt(index, 5).toString();

				d.setVisible(true);

				d.setLocationRelativeTo(null);
				d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				d.bookID.setText(id);
				d.title.setText(title);
				d.author.setText(author);
				d.quantity.setText(quantity);
				d.price.setText(price);
				d.seller.setText(emailSeller);

				d.emailCust.setText(email);
				d.setVisible(true);

			}
		});
		scrollPane.setViewportView(table);
		table.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.textInactiveText);
		panel_1.setBounds(-1, 0, 324, 158);
		contentPane.add(panel_1);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(CustomerPage.class.getResource("/images/book-stack.jpg")));
		panel_1.add(lblNewLabel);

		JButton btnNewButton = new JButton("Show available books");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				ShopDAOImpl shop = new ShopDAOImpl();

				DefaultTableModel model = new DefaultTableModel();
				Object[] column = new Object[6];
				column[0] = "Id";
				column[1] = "Title";
				column[2] = "Author";
				column[3] = "Quantity";
				column[4] = "Price per book (£)";
				column[5] = "Seller email";
				model.setColumnIdentifiers(column);
				Object[] rowData = new Object[6];
				for (int i = 0; i < shop.showSellerBooks(email).size(); i++) {

					rowData[0] = shop.showSellerBooks(email).get(i).getId();
					rowData[1] = shop.showSellerBooks(email).get(i).getTitle();
					rowData[2] = shop.showSellerBooks(email).get(i).getAuthor();
					rowData[3] = shop.showSellerBooks(email).get(i).getQuantity();
					rowData[4] = shop.showSellerBooks(email).get(i).getPrice();
					rowData[5] = shop.showSellerBooks(email).get(i).getSellerEmail();

					model.addRow(rowData);

				}
				table.setModel(model);

			}
		});

		btnNewButton.setForeground(SystemColor.textInactiveText);
		btnNewButton.setFont(new Font("Bodoni 72 Oldstyle", Font.BOLD, 13));
		btnNewButton.setBounds(411, 134, 179, 29);
		contentPane.add(btnNewButton);

		emailCust = new JTextField();
		emailCust.setBounds(324, 0, 145, 26);
		contentPane.add(emailCust);
		emailCust.setColumns(10);

		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login log = new Login();
				log.setVisible(true);
				dispose();
			}
		});
		btnLogOut.setForeground(SystemColor.textInactiveText);
		btnLogOut.setFont(new Font("Bodoni 72 Oldstyle", Font.BOLD, 13));
		btnLogOut.setBounds(372, 27, 86, 29);
		contentPane.add(btnLogOut);

	}
}
