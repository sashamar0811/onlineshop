package org.am02316.com1028;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.am02316.com1028.DAO.ShopDAOImpl;
import org.am02316.com1028.Functionality.Seller;
import org.am02316.com1028.Functionality.Validation;
import javax.swing.JTextPane;

public class SellerRegister extends JFrame {

	private JPanel contentPane;
	private JTextField forename;
	private JTextField surname;
	private JTextField email1;
	private JTextField phoneNumber;
	private JPasswordField passwordField;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField securityWord;
	boolean permission;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SellerRegister frame = new SellerRegister();
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
	public SellerRegister() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 450);
		contentPane = new JPanel();
		contentPane.setForeground(SystemColor.textInactiveText);
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(178, 34, 34));
		panel.setBounds(0, 322, 678, 72);
		contentPane.add(panel);

		JLabel lblRegistrationForm = new JLabel("Registration Form");
		lblRegistrationForm.setForeground(SystemColor.textInactiveText);
		lblRegistrationForm.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 22));
		lblRegistrationForm.setBounds(203, 6, 178, 27);
		contentPane.add(lblRegistrationForm);

		JLabel textForename = new JLabel("Forename");
		textForename.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 14));
		textForename.setForeground(SystemColor.textInactiveText);
		textForename.setBounds(83, 49, 65, 19);
		contentPane.add(textForename);

		forename = new JTextField();
		forename.setBounds(163, 45, 261, 26);
		contentPane.add(forename);
		forename.setColumns(10);

		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setForeground(SystemColor.textInactiveText);
		lblSurname.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 14));
		lblSurname.setBounds(93, 78, 61, 16);
		contentPane.add(lblSurname);

		surname = new JTextField();
		surname.setColumns(10);
		surname.setBounds(163, 73, 261, 26);
		contentPane.add(surname);

		JLabel lblEm = new JLabel("Email");
		lblEm.setForeground(SystemColor.textInactiveText);
		lblEm.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 14));
		lblEm.setBounds(103, 110, 61, 16);
		contentPane.add(lblEm);

		email1 = new JTextField();
		email1.setColumns(10);
		email1.setBounds(163, 105, 261, 26);
		contentPane.add(email1);

		JLabel lblPhoneNumber = new JLabel("Phone Number ");
		lblPhoneNumber.setForeground(SystemColor.textInactiveText);
		lblPhoneNumber.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 14));
		lblPhoneNumber.setBounds(50, 147, 98, 19);
		contentPane.add(lblPhoneNumber);

		phoneNumber = new JTextField();
		phoneNumber.setColumns(10);
		phoneNumber.setBounds(163, 158, 261, 26);
		contentPane.add(phoneNumber);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(SystemColor.textInactiveText);
		lblPassword.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 14));
		lblPassword.setBounds(61, 196, 87, 16);
		contentPane.add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setBounds(163, 191, 261, 26);
		contentPane.add(passwordField);

		JLabel label = new JLabel("(+44)");
		label.setForeground(SystemColor.textInactiveText);
		label.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 14));
		label.setBounds(120, 165, 34, 19);
		contentPane.add(label);

		JButton btnRegister = new JButton("Register");

		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				ShopDAOImpl dao = new ShopDAOImpl();
				Validation valid = new Validation();
				String email = email1.getText().toString();
				String password = passwordField.getText().toString();

				Seller seller = new Seller(forename.getText(), surname.getText(), email1.getText(),
						phoneNumber.getText(), passwordField.getText(), securityWord.getText(), permission);
				if (dao.registerSeller(seller) == true && valid.checkEmail(email) == true
						&& valid.checkPassword(password) == true) {
					JOptionPane.showMessageDialog(null, "You registered successfully.");

				} else {
					JOptionPane.showMessageDialog(null, "Check your inputs.");

				}

			}

		});
		btnRegister.setForeground(SystemColor.textInactiveText);
		btnRegister.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 13));
		btnRegister.setBounds(264, 253, 117, 29);
		contentPane.add(btnRegister);

		JLabel lblSecurityWord = new JLabel("Security Word");
		lblSecurityWord.setForeground(SystemColor.textInactiveText);
		lblSecurityWord.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 14));
		lblSecurityWord.setBounds(508, 109, 89, 19);
		contentPane.add(lblSecurityWord);

		securityWord = new JTextField();
		securityWord.setColumns(10);
		securityWord.setBounds(478, 143, 158, 26);
		contentPane.add(securityWord);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Register reg = new Register();
				reg.setVisible(true);
			}
		});
		btnBack.setForeground(new Color(178, 34, 34));
		btnBack.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 13));
		btnBack.setBounds(455, 9, 117, 29);
		contentPane.add(btnBack);
		
		JTextPane txtpnpasswordMustStart = new JTextPane();
		txtpnpasswordMustStart.setFont(new Font("Dialog", Font.PLAIN, 16));
		txtpnpasswordMustStart.setForeground(Color.GRAY);
		txtpnpasswordMustStart.setText("*Password must start with a capital letter, contain at least one number and at least 4 characters ");
		txtpnpasswordMustStart.setBounds(0, 228, 240, 86);
		contentPane.add(txtpnpasswordMustStart);

	}
}
