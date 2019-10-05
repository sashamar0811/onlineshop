package org.am02316.com1028;

import java.awt.Color;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import org.am02316.com1028.DAO.ShopDAOImpl;
import org.am02316.com1028.Functionality.Basket;

import org.am02316.com1028.Functionality.PastOrder;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;;

public class BasketCustomer extends JFrame {

	private JPanel contentPane;
	public JTable table1;
	public JTextField cost;
	final ButtonGroup buttonGroup = new ButtonGroup();
	String totalResult;

	JLabel lblTotalCost = new JLabel("Total Cost ");

	JRadioButton rdbtnStandard = new JRadioButton("Standard");
	JRadioButton rdbtnPremium = new JRadioButton("Premium (extra 2.0 £ fee)");
	JTextField emailCust;

	private int id;
	private int id1;
	

	/**
	 * Create the frame.
	 */

	String email = null;

	public BasketCustomer(String email) {
		this.email = email;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 450);
		contentPane = new JPanel();
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg) {

			}
		});
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(new Color(178, 34, 34));
		menuBar.setBackground(new Color(248, 248, 255));
		menuBar.setBounds(445, 0, 283, 59);
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

		JMenu mnNewMenu = new JMenu("Wish List");
		mnNewMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
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
		scrollPane.setBounds(6, 32, 441, 221);
		contentPane.add(scrollPane);

		table1 = new JTable();
		table1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		scrollPane.setViewportView(table1);

		JButton btnCheckOut = new JButton("Check out");
		btnCheckOut.setBackground(Color.WHITE);
		btnCheckOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ShopDAOImpl shop = new ShopDAOImpl();

				for (int j = 0; j < table1.getRowCount(); j++) {

					int quantity = (int) table1.getValueAt(j, 3);
					String seller = table1.getValueAt(j, 1).toString();
					String title = table1.getValueAt(j, 2).toString();
					int total = Integer.parseInt(cost.getText());
					int price = (int) table1.getValueAt(j, 4);
					int i = (int)table1.getValueAt(j, 0);

					PastOrder order = new PastOrder(i, emailCust.getText(), seller, title, quantity);
					Basket basket = new Basket(id1, seller, title, quantity, price, emailCust.getText());

					if (shop.addToPastOrders(order) == true && shop.checkOut(basket)) {

						JOptionPane.showMessageDialog(null, "You successfully purchased books.");

					} else {
						JOptionPane.showMessageDialog(null, "Something went wrong.");
					}

				}

			}
		});
		btnCheckOut.setFont(new Font("Bodoni 72 Oldstyle", Font.BOLD, 14));
		btnCheckOut.setForeground(new Color(178, 34, 34));
		btnCheckOut.setBounds(423, 355, 131, 27);
		contentPane.add(btnCheckOut);

		cost = new JTextField();
		cost.setBounds(93, 355, 97, 26);
		contentPane.add(cost);
		cost.setColumns(10);

		lblTotalCost.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 14));
		lblTotalCost.setForeground(SystemColor.textInactiveText);
		lblTotalCost.setBounds(8, 359, 94, 19);
		contentPane.add(lblTotalCost);

		JLabel lblChooseDelivery = new JLabel("Choose Delivery:");
		lblChooseDelivery.setForeground(SystemColor.textInactiveText);
		lblChooseDelivery.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 14));
		lblChooseDelivery.setBounds(6, 265, 108, 19);
		contentPane.add(lblChooseDelivery);

		buttonGroup.add(rdbtnStandard);
		rdbtnStandard.setBackground(Color.WHITE);
		rdbtnStandard.setForeground(SystemColor.textInactiveText);
		rdbtnStandard.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 13));
		rdbtnStandard.setBounds(6, 295, 85, 29);
		contentPane.add(rdbtnStandard);

		buttonGroup.add(rdbtnPremium);
		rdbtnPremium.setBackground(Color.WHITE);
		rdbtnPremium.setForeground(SystemColor.textInactiveText);
		rdbtnPremium.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 13));
		rdbtnPremium.setBounds(103, 295, 187, 29);
		contentPane.add(rdbtnPremium);

		JLabel lblBasket = new JLabel("Basket");
		lblBasket.setForeground(new Color(178, 34, 34));
		lblBasket.setFont(new Font("Bodoni 72 Oldstyle", Font.BOLD, 21));
		lblBasket.setBounds(200, 6, 93, 18);
		contentPane.add(lblBasket);

		JButton btnCalculateCost = new JButton("Calculate cost");
		btnCalculateCost.setBackground(Color.WHITE);
		btnCalculateCost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {

				Login log = new Login();
				ShopDAOImpl shop = new ShopDAOImpl();
				String email = log.email1.getText();
				int quantity = 0;
				int price = 0;
				int total = 0;
				int fee = 2;
				int totalCost = 0;

				if (rdbtnStandard.isSelected()) {
					for (int i = 0; i < table1.getRowCount(); i++) {
						quantity = (int) table1.getValueAt(i, 3);
						price = (int) table1.getValueAt(i, 4);

						total += (price * quantity);

						cost.setText(String.valueOf(total));
					}
				}
				if (rdbtnPremium.isSelected()) {
					for (int i = 0; i < table1.getRowCount(); i++) {
						quantity = (int) table1.getValueAt(i, 3);
						price = (int) table1.getValueAt(i, 4);

						totalCost += (price * quantity);
						total = totalCost + fee;

						cost.setText(String.valueOf(total));
					}

				}
			}
		});
		btnCalculateCost.setForeground(SystemColor.textInactiveText);
		btnCalculateCost.setFont(new Font("Bodoni 72 Oldstyle", Font.BOLD, 14));
		btnCalculateCost.setBounds(220, 355, 168, 27);
		contentPane.add(btnCalculateCost);

		JButton btnDeleteSelectedRow = new JButton("Delete Selected Row");
		btnDeleteSelectedRow.setBackground(Color.WHITE);
		btnDeleteSelectedRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {

			}
		});
		btnDeleteSelectedRow.setForeground(new Color(178, 34, 34));
		btnDeleteSelectedRow.setFont(new Font("Bodoni 72 Oldstyle", Font.BOLD, 14));
		btnDeleteSelectedRow.setBounds(470, 87, 193, 29);
		contentPane.add(btnDeleteSelectedRow);

		JButton btnRefreshThePage = new JButton("Refresh the page");
		btnRefreshThePage.setBackground(Color.WHITE);
		btnRefreshThePage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {

				ShopDAOImpl shop = new ShopDAOImpl();

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
				table1.setModel(model);

			}
		});
		btnRefreshThePage.setForeground(SystemColor.textInactiveText);
		btnRefreshThePage.setFont(new Font("Bodoni 72 Oldstyle", Font.BOLD, 14));
		btnRefreshThePage.setBounds(15, 3, 154, 29);
		contentPane.add(btnRefreshThePage);

		JComboBox quantity1 = new JComboBox();
		quantity1.setBackground(Color.WHITE);
		quantity1.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
				"11", "12", "13", "14", "15", "16", "17", "18", "19", "20" }));
		quantity1.setBounds(480, 128, 85, 29);
		contentPane.add(quantity1);

		JButton btnUpdate = new JButton("Update quantity");
		btnUpdate.setBackground(Color.WHITE);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table1.getSelectedRow();
				DefaultTableModel model = (DefaultTableModel) table1.getModel();
				String selected = model.getValueAt(row, 0).toString();
				ShopDAOImpl dao = new ShopDAOImpl();
				int quantity = Integer.parseInt(quantity1.getSelectedItem().toString());
				if (dao.updateBookInBasket(selected, quantity) == true) {
					JOptionPane.showMessageDialog(null, "Quantity has been updated.");

				} else {
					JOptionPane.showMessageDialog(null, "Not enough books on a stock.");
				}

			}
		});
		btnUpdate.setForeground(new Color(178, 34, 34));
		btnUpdate.setFont(new Font("Bodoni 72 Oldstyle", Font.BOLD, 14));
		btnUpdate.setBounds(470, 169, 143, 27);
		contentPane.add(btnUpdate);

		emailCust = new JTextField();
		emailCust.setColumns(10);
		emailCust.setBounds(291, 4, 154, 26);
		contentPane.add(emailCust);

	}

}
