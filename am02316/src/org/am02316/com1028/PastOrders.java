package org.am02316.com1028;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.am02316.com1028.DAO.ShopDAOImpl;
import org.am02316.com1028.Functionality.PastOrder;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.SystemColor;
import javax.swing.JTextField;

public class PastOrders extends JFrame {

	private JPanel contentPane;
	JTable table;
	JTextField email1;
	int id;
	
	
	/**
	 * Create the frame.
	 */
	String email = null;

	public PastOrders(String email) {
		this.email = email;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 455);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 45, 452, 229);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));

		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(new Color(178, 34, 34));
		menuBar.setBackground(new Color(248, 248, 255));
		menuBar.setBounds(455, 0, 283, 59);
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

		JMenu mnPastOrders = new JMenu("Wish List");
		mnPastOrders.addMouseListener(new MouseAdapter() {
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
		mnPastOrders.setFont(new Font("Bodoni 72 Oldstyle", Font.BOLD, 14));
		menuBar.add(mnPastOrders);

		JButton btnRateSelectedBook = new JButton("Rate selected book");
		btnRateSelectedBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RatingPage rate = new RatingPage(email);

				String email = email1.getText();

				int index = table.getSelectedRow();
				TableModel model = table.getModel();
				String id = model.getValueAt(index, 0).toString();

				String emailSeller = model.getValueAt(index, 1).toString();
				String title = model.getValueAt(index, 2).toString();
				String quantity = model.getValueAt(index, 3).toString();

				rate.setLocationRelativeTo(null);
				rate.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				rate.id.setText(id);
				rate.title.setText(title);
				rate.seller.setText(emailSeller);
				rate.quantity.setText(quantity);

				rate.emailCust.setText(email);
				rate.setVisible(true);

			}
		});
		btnRateSelectedBook.setForeground(SystemColor.textInactiveText);
		btnRateSelectedBook.setFont(new Font("Bodoni 72 Oldstyle", Font.BOLD, 15));
		btnRateSelectedBook.setBounds(36, 286, 169, 29);
		contentPane.add(btnRateSelectedBook);

		JLabel lblNewLabel = new JLabel("Past Orders:");
		lblNewLabel.setForeground(new Color(178, 34, 34));
		lblNewLabel.setFont(new Font("Bodoni 72 Oldstyle", Font.BOLD, 21));
		lblNewLabel.setBounds(168, 6, 127, 28);
		contentPane.add(lblNewLabel);

		email1 = new JTextField();
		email1.setBounds(22, 8, 130, 26);
		contentPane.add(email1);
		email1.setColumns(10);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(178, 34, 34));
		panel.setBounds(0, 358, 823, 70);
		contentPane.add(panel);

		JButton btnClearHistory = new JButton("Clear history");
		btnClearHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ShopDAOImpl shop = new ShopDAOImpl();
				for (int j = 0; j < table.getRowCount(); j++) {

					int quantity = (int) table.getValueAt(j, 3);
					String seller = table.getValueAt(j, 1).toString();
					String title = table.getValueAt(j, 2).toString();
					PastOrder order = new PastOrder(id, email1.getText(), seller, title, quantity);
					if (shop.clearHistory(order) == true) {
						JOptionPane.showMessageDialog(null, "History is cleared.");
					} else {
						JOptionPane.showMessageDialog(null, "Something went wrong.");
					}
				}
			}
		});
		btnClearHistory.setForeground(SystemColor.textInactiveText);
		btnClearHistory.setFont(new Font("Bodoni 72 Oldstyle", Font.BOLD, 15));
		btnClearHistory.setBounds(270, 286, 122, 29);
		contentPane.add(btnClearHistory);

		JButton btnRefreshPage = new JButton("Refresh Page");
		btnRefreshPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ShopDAOImpl shop = new ShopDAOImpl();

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
				table.setModel(model);

			}

		});
		btnRefreshPage.setForeground(new Color(178, 34, 34));
		btnRefreshPage.setFont(new Font("Bodoni 72 Oldstyle", Font.BOLD, 15));
		btnRefreshPage.setBounds(298, 8, 152, 29);
		contentPane.add(btnRefreshPage);

	}
}
