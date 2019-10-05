package org.am02316.com1028;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import org.am02316.com1028.DAO.ShopDAOImpl;


import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class AdminPage extends JFrame {

	private JPanel contentPane;
	public JTable sellers;
	DefaultTableModel model = new DefaultTableModel();
	JComboBox box = new JComboBox();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminPage frame = new AdminPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdminPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 450);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(178, 34, 34));
		panel.setBounds(-12, 0, 690, 65);
		contentPane.add(panel);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(15, 113, 657, 202);
		contentPane.add(scrollPane_1);

		sellers = new JTable();
		sellers.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		scrollPane_1.setViewportView(sellers);

		JLabel lblListOfSellers = new JLabel("List of sellers:");
		lblListOfSellers.setForeground(SystemColor.textInactiveText);
		lblListOfSellers.setFont(new Font("Bodoni 72 Oldstyle", Font.BOLD, 16));
		lblListOfSellers.setBounds(289, 77, 113, 20);
		contentPane.add(lblListOfSellers);

		JButton btnNewButton = new JButton("Log Out");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setForeground(SystemColor.textInactiveText);
		btnNewButton.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login log = new Login();
				log.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(522, 74, 117, 29);
		contentPane.add(btnNewButton);

		JButton btnListOfCustomers = new JButton("List of Customers");
		btnListOfCustomers.setBackground(Color.WHITE);
		btnListOfCustomers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				AdminPageCust cust = new AdminPageCust();

				ShopDAOImpl shop = new ShopDAOImpl();
				DefaultTableModel model = new DefaultTableModel();
				Object[] column = new Object[5];
				column[0] = "Forename";
				column[1] = "Surname";
				column[2] = "Email";
				column[3] = "PhoneNumber";
				column[4] = "SecurityNumber";
				model.setColumnIdentifiers(column);
				Object[] rowData = new Object[5];
				for (int i = 0; i < shop.showCustomers().size(); i++) {

					rowData[0] = shop.showCustomers().get(i).getForename();
					rowData[1] = shop.showCustomers().get(i).getSurname();
					rowData[2] = shop.showCustomers().get(i).getEmail();
					rowData[3] = shop.showCustomers().get(i).getPhoneNumber();
					rowData[4] = shop.showCustomers().get(i).getSecurityWord();

					model.addRow(rowData);

				}
				cust.table.setModel(model);
				cust.setVisible(true);
				dispose();
			}
		});
		btnListOfCustomers.setForeground(SystemColor.textInactiveText);
		btnListOfCustomers.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 14));
		btnListOfCustomers.setBounds(16, 77, 140, 29);
		contentPane.add(btnListOfCustomers);

		JButton btnShowInfo = new JButton("Show Info");
		btnShowInfo.setBackground(Color.WHITE);
		btnShowInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ShopDAOImpl shop = new ShopDAOImpl();

				Object[] column = new Object[5];
				column[0] = "Forename";
				column[1] = "Surname";
				column[2] = "Email";
				column[3] = "PhoneNumber";
				column[4] = "SecurityNumber";
				model.setColumnIdentifiers(column);
				Object[] rowData = new Object[5];
				for (int i = 0; i < shop.showSellers().size(); i++) {

					rowData[0] = shop.showSellers().get(i).getForename();
					rowData[1] = shop.showSellers().get(i).getSurname();
					rowData[2] = shop.showSellers().get(i).getEmail();
					rowData[3] = shop.showSellers().get(i).getPhoneNumber();
					rowData[4] = shop.showSellers().get(i).getSecurityWord();

					model.addRow(rowData);

				}
				sellers.setModel(model);

			}
		});
		btnShowInfo.setForeground(SystemColor.textInactiveText);
		btnShowInfo.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 14));
		btnShowInfo.setBounds(6, 334, 140, 29);
		contentPane.add(btnShowInfo);

		JButton btnApproveSelectedSeller = new JButton("Approve selected seller");
		btnApproveSelectedSeller.setBackground(Color.WHITE);
		btnApproveSelectedSeller.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ShopDAOImpl shop = new ShopDAOImpl();
				boolean permission = Boolean.parseBoolean(box.getSelectedItem().toString());
				int row = sellers.getSelectedRow();
				String selected = model.getValueAt(row, 2).toString();
				if (shop.givePermissionToSell(selected, permission) == true) {
					JOptionPane.showMessageDialog(null, "Permission to sell has been given to seller.");
				} else {
					JOptionPane.showMessageDialog(null, "Try again.");
				}

			}
		});
		btnApproveSelectedSeller.setForeground(new Color(178, 34, 34));
		btnApproveSelectedSeller.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 14));
		btnApproveSelectedSeller.setBounds(458, 334, 181, 27);
		contentPane.add(btnApproveSelectedSeller);
		box.setBackground(Color.WHITE);

		box.setForeground(SystemColor.textInactiveText);
		box.setFont(new Font("Bodoni 72 Oldstyle", Font.BOLD, 15));
		box.setModel(new DefaultComboBoxModel(new String[] { "false", "true" }));
		box.setBounds(304, 335, 140, 27);
		contentPane.add(box);
	}
}
