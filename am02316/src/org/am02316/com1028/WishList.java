package org.am02316.com1028;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.am02316.com1028.DAO.ShopDAOImpl;

import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JTextField;

public class WishList extends JFrame {

	private JPanel contentPane;
	JTable table;
	JTextField email1;

	

	/**
	 * Create the frame.
	 */

	String email = null;

	public WishList(String email) {
		this.email = email;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 450);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(new Color(178, 34, 34));
		menuBar.setBackground(new Color(248, 248, 255));
		menuBar.setBounds(440, 0, 283, 59);
		contentPane.add(menuBar);

		JMenu mnWishList = new JMenu("Main Page");
		mnWishList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				CustomerPage customer = new CustomerPage(email);
				customer.emailCust.setText(email);
				customer.setVisible(true);
				dispose();
			}
		});

		mnWishList.setForeground(new Color(178, 34, 34));
		mnWishList.setFont(new Font("Bodoni 72 Oldstyle", Font.BOLD, 14));
		menuBar.add(mnWishList);

		JMenu mnNewMenu = new JMenu("Basket List");
		mnNewMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
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

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 49, 434, 186);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JLabel lblNewLabel = new JLabel("Wish List:");
		lblNewLabel.setForeground(new Color(178, 34, 34));
		lblNewLabel.setFont(new Font("Bodoni 72 Oldstyle", Font.BOLD, 21));
		lblNewLabel.setBounds(168, 11, 101, 28);
		contentPane.add(lblNewLabel);

		JButton btnDeleteSelected = new JButton("Delete selected row");
		btnDeleteSelected.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				int row = table.getSelectedRow();
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				// String selected = model.getValueAt(row, 0).toString();
				ShopDAOImpl dao = new ShopDAOImpl();
				String selected = model.getValueAt(row, 0).toString();

				if (dao.removeBooksFromWishList(selected))
					JOptionPane.showMessageDialog(null, "Book has been deleted from the stock.");

			}
		});
		btnDeleteSelected.setForeground(new Color(178, 34, 34));
		btnDeleteSelected.setFont(new Font("Bodoni 72 Oldstyle", Font.BOLD, 14));
		btnDeleteSelected.setBounds(142, 268, 173, 27);
		contentPane.add(btnDeleteSelected);

		JButton btnRefreshThePage = new JButton("Refresh the page");
		btnRefreshThePage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				ShopDAOImpl shop = new ShopDAOImpl();

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

				table.setModel(model);

			}
		});
		btnRefreshThePage.setForeground(SystemColor.textInactiveText);
		btnRefreshThePage.setFont(new Font("Bodoni 72 Oldstyle", Font.BOLD, 14));
		btnRefreshThePage.setBounds(6, 13, 155, 27);
		contentPane.add(btnRefreshThePage);

		email1 = new JTextField();
		email1.setBounds(289, 11, 136, 26);
		contentPane.add(email1);
		email1.setColumns(10);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(178, 34, 34));
		panel.setBounds(0, 326, 739, 79);
		contentPane.add(panel);

	}
}
