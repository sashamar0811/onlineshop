package org.am02316.com1028;

import java.sql.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.am02316.com1028.DAO.ShopDAOImpl;
import org.am02316.com1028.Functionality.User;



import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class Login extends JFrame {

	private JPanel contentPane;
	JTextField email1;
	private JPasswordField password1;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	JRadioButton rdbtnSeller = new JRadioButton("Seller");
	JRadioButton rdbtnAdmin = new JRadioButton("Admin");
	JRadioButton rdbtnCustomer = new JRadioButton("Customer");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 450);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(178, 34, 34));
		panel.setBounds(0, 0, 256, 426);
		contentPane.add(panel);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		panel.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/Images/8314929977_28fd740070_b.jpg")));

		JLabel lblAlmar = new JLabel("Almar");
		lblAlmar.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 24));
		lblAlmar.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlmar.setForeground(new Color(108, 108, 108));
		lblAlmar.setBounds(348, 6, 127, 38);
		contentPane.add(lblAlmar);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(new Color(105, 105, 105));
		lblEmail.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 17));
		lblEmail.setBounds(302, 51, 91, 21);
		contentPane.add(lblEmail);

		email1 = new JTextField();
		email1.setBounds(303, 78, 275, 38);
		contentPane.add(email1);
		email1.setColumns(10);

		JLabel lblPassword = new JLabel(" Password");
		lblPassword.setForeground(new Color(105, 105, 105));
		lblPassword.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 17));
		lblPassword.setBounds(302, 128, 91, 21);
		contentPane.add(lblPassword);

		password1 = new JPasswordField();
		password1.setBounds(303, 152, 275, 38);
		contentPane.add(password1);

		JButton btnLogin = new JButton("Login");
		btnLogin.setBackground(Color.WHITE);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent act) {
				ShopDAOImpl dao = new ShopDAOImpl();
				String email = email1.getText();
				String password = password1.getText();

				if (dao.login(email, password) == true && rdbtnCustomer.isSelected()) {
					JOptionPane.showMessageDialog(null, "Email and password are correct.");

					CustomerPage cust = new CustomerPage(email);

					cust.emailCust.setText(email1.getText());

					cust.setVisible(true);

					dispose();
				}
				if (dao.login(email, password) == true && rdbtnAdmin.isSelected()) {
					JOptionPane.showMessageDialog(null, "Email and password are correct.");

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

				if (dao.login(email, password) == true && rdbtnSeller.isSelected()) {
					JOptionPane.showMessageDialog(null, "Email and password are correct.");
					SellerPage seller = new SellerPage(email);

					seller.email1.setText(email);
					seller.setVisible(true);
					dispose();
				}
				if (dao.login(email, password) == false) {
					JOptionPane.showMessageDialog(null, "Incorrect email or password.");

				}

			}

		});
		btnLogin.setForeground(SystemColor.textInactiveText);
		btnLogin.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 13));
		btnLogin.setBounds(289, 271, 127, 29);
		contentPane.add(btnLogin);

		JLabel lblOr = new JLabel("      or");
		lblOr.setForeground(new Color(128, 0, 0));
		lblOr.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 16));
		lblOr.setBounds(414, 302, 61, 20);
		contentPane.add(lblOr);

		JButton btnRegister = new JButton("Register");
		btnRegister.setBackground(Color.WHITE);
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Register register = new Register();
				register.setVisible(true);
			}
		});
		btnRegister.setForeground(SystemColor.textInactiveText);
		btnRegister.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 13));
		btnRegister.setBounds(472, 338, 96, 29);
		contentPane.add(btnRegister);

		JButton btnResetPassword = new JButton("Reset Password");
		btnResetPassword.setBackground(Color.WHITE);
		btnResetPassword.setBounds(289, 338, 127, 29);
		contentPane.add(btnResetPassword);
		btnResetPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewPassword password = new NewPassword();
				password.setVisible(true);

			}
		});
		btnResetPassword.setForeground(SystemColor.textInactiveText);
		btnResetPassword.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 13));

		buttonGroup.add(rdbtnAdmin);
		rdbtnAdmin.setBackground(Color.WHITE);
		rdbtnAdmin.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 13));
		rdbtnAdmin.setForeground(SystemColor.textInactiveText);
		rdbtnAdmin.setBounds(267, 222, 69, 29);
		contentPane.add(rdbtnAdmin);

		buttonGroup.add(rdbtnSeller);
		rdbtnSeller.setBackground(Color.WHITE);
		rdbtnSeller.setForeground(SystemColor.textInactiveText);
		rdbtnSeller.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 13));
		rdbtnSeller.setBounds(383, 222, 65, 29);
		contentPane.add(rdbtnSeller);

		buttonGroup.add(rdbtnCustomer);
		rdbtnCustomer.setBackground(Color.WHITE);
		rdbtnCustomer.setForeground(SystemColor.textInactiveText);
		rdbtnCustomer.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 13));
		rdbtnCustomer.setBounds(504, 222, 89, 29);
		contentPane.add(rdbtnCustomer);
	}
}
