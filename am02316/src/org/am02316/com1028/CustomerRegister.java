package org.am02316.com1028;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.am02316.com1028.DAO.ShopDAOImpl;
import org.am02316.com1028.Functionality.Customer;
import org.am02316.com1028.Functionality.User;
import org.am02316.com1028.Functionality.Validation;
import javax.swing.JTextPane;

public class CustomerRegister extends JFrame {

	private JPanel contentPane;
	private JTextField forename;
	private JTextField surname;
	private JTextField email1;
	private JTextField phoneNumber;
	private JPasswordField passwordField;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField securityWord;
	private JComboBox custType = new JComboBox();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerRegister frame = new CustomerRegister();
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
	public CustomerRegister() {
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
		panel.setBounds(0, 301, 703, 93);
		contentPane.add(panel);

		JLabel lblRegistrationForm = new JLabel("Registration Form");
		lblRegistrationForm.setForeground(SystemColor.textInactiveText);
		lblRegistrationForm.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 22));
		lblRegistrationForm.setBounds(203, 6, 178, 27);
		contentPane.add(lblRegistrationForm);

		JLabel textForename = new JLabel("Forename");
		textForename.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 14));
		textForename.setForeground(SystemColor.textInactiveText);
		textForename.setBounds(92, 49, 65, 19);
		contentPane.add(textForename);

		forename = new JTextField();
		forename.setBounds(172, 45, 261, 26);
		contentPane.add(forename);
		forename.setColumns(10);

		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setForeground(SystemColor.textInactiveText);
		lblSurname.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 14));
		lblSurname.setBounds(96, 78, 61, 16);
		contentPane.add(lblSurname);

		surname = new JTextField();
		surname.setColumns(10);
		surname.setBounds(172, 73, 261, 26);
		contentPane.add(surname);

		JLabel lblEm = new JLabel("Email");
		lblEm.setForeground(SystemColor.textInactiveText);
		lblEm.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 14));
		lblEm.setBounds(118, 110, 61, 16);
		contentPane.add(lblEm);

		email1 = new JTextField();
		email1.setColumns(10);
		email1.setBounds(172, 105, 261, 26);
		contentPane.add(email1);

		JLabel lblPhoneNumber = new JLabel("Phone Number ");
		lblPhoneNumber.setForeground(SystemColor.textInactiveText);
		lblPhoneNumber.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 14));
		lblPhoneNumber.setBounds(32, 162, 98, 19);
		contentPane.add(lblPhoneNumber);

		phoneNumber = new JTextField();
		phoneNumber.setColumns(10);
		phoneNumber.setBounds(172, 158, 261, 26);
		contentPane.add(phoneNumber);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(SystemColor.textInactiveText);
		lblPassword.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 14));
		lblPassword.setBounds(70, 196, 87, 16);
		contentPane.add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setBounds(172, 191, 261, 26);
		contentPane.add(passwordField);

		JLabel label = new JLabel("(+44)");
		label.setForeground(SystemColor.textInactiveText);
		label.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 14));
		label.setBounds(131, 172, 34, 19);
		contentPane.add(label);

		JButton btnRegister = new JButton("Register");
		btnRegister.setBackground(Color.WHITE);
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				ShopDAOImpl dao = new ShopDAOImpl();
				Validation valid = new Validation();
				String email = email1.getText().toString();
				String password = passwordField.getText().toString();

				Customer customer = new Customer(forename.getText(), surname.getText(), email1.getText(),
						phoneNumber.getText(), passwordField.getText(), securityWord.getText(),
						custType.getSelectedItem().toString());

				if (dao.registerCustomer(customer) == true && valid.checkEmail(email) == true
						&& valid.checkPassword(password) == true) {

					JOptionPane.showMessageDialog(null, "You registered successfully.");

				} else {
					JOptionPane.showMessageDialog(null, "Check your inputs.");

				}

			}

		});
		btnRegister.setForeground(SystemColor.textInactiveText);
		btnRegister.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 13));
		btnRegister.setBounds(244, 256, 117, 29);
		contentPane.add(btnRegister);
		custType.setBackground(Color.WHITE);

		custType.setForeground(SystemColor.textInactiveText);
		custType.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 13));
		custType.setModel(new DefaultComboBoxModel(new String[] { "<None>", "Student", "OAP" }));
		custType.setBounds(479, 230, 136, 27);
		contentPane.add(custType);

		JLabel lblSecurityWord = new JLabel("Security Word");
		lblSecurityWord.setForeground(SystemColor.textInactiveText);
		lblSecurityWord.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 14));
		lblSecurityWord.setBounds(501, 86, 89, 19);
		contentPane.add(lblSecurityWord);

		securityWord = new JTextField();
		securityWord.setColumns(10);
		securityWord.setBounds(469, 121, 158, 26);
		contentPane.add(securityWord);

		JLabel lblCustomerTypeoptional = new JLabel("Customer Type (optional)");
		lblCustomerTypeoptional.setForeground(SystemColor.textInactiveText);
		lblCustomerTypeoptional.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 14));
		lblCustomerTypeoptional.setBounds(466, 195, 161, 19);
		contentPane.add(lblCustomerTypeoptional);

		JButton btnBack_1 = new JButton("Back");
		btnBack_1.setBackground(Color.WHITE);
		btnBack_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Register reg = new Register();
				reg.setVisible(true);
				dispose();
			}
		});
		btnBack_1.setForeground(new Color(178, 34, 34));
		btnBack_1.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 13));
		btnBack_1.setBounds(531, 9, 117, 29);
		contentPane.add(btnBack_1);
		
		JTextPane textPane = new JTextPane();
		textPane.setText("*Password must start with a capital letter, contain at least one number and at least 4 characters ");
		textPane.setForeground(Color.GRAY);
		textPane.setFont(new Font("Dialog", Font.PLAIN, 16));
		textPane.setBounds(0, 221, 240, 86);
		contentPane.add(textPane);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Register reg = new Register();
				reg.setVisible(true);
			}
		});

	}
}
