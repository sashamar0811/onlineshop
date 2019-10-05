package org.am02316.com1028;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.am02316.com1028.DAO.ShopDAOImpl;

import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminPageCust extends JFrame {

	private JPanel contentPane;
	JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminPageCust frame = new AdminPageCust();
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
	public AdminPageCust() {
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

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 117, 651, 241);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JLabel lblNewLabel = new JLabel("List of Customers:");
		lblNewLabel.setForeground(SystemColor.textInactiveText);
		lblNewLabel.setFont(new Font("Bodoni 72 Oldstyle", Font.BOLD, 16));
		lblNewLabel.setBounds(228, 77, 141, 21);
		contentPane.add(lblNewLabel);

		JButton btnNewButton = new JButton("Back");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				AdminPage admin = new AdminPage();
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
				for (int i = 0; i < shop.showSellers().size(); i++) {

					rowData[0] = shop.showSellers().get(i).getForename();
					rowData[1] = shop.showSellers().get(i).getSurname();
					rowData[2] = shop.showSellers().get(i).getEmail();
					rowData[3] = shop.showSellers().get(i).getPhoneNumber();
					rowData[4] = shop.showSellers().get(i).getSecurityWord();

					model.addRow(rowData);

				}
				admin.sellers.setModel(model);
				admin.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setForeground(new Color(178, 34, 34));
		btnNewButton.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 14));
		btnNewButton.setBounds(519, 74, 117, 29);
		contentPane.add(btnNewButton);
	}
}
