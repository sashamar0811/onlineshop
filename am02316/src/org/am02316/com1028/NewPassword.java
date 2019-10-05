package org.am02316.com1028;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.am02316.com1028.DAO.ShopDAOImpl;
import org.am02316.com1028.Functionality.Validation;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class NewPassword extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField textWord;
	private JTextField textEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewPassword frame = new NewPassword();
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
	public NewPassword() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 450);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(178, 34, 34));
		panel.setBounds(503, 0, 175, 394);
		contentPane.add(panel);

		JLabel lblEnterNewPassword = new JLabel("Enter New Password");
		lblEnterNewPassword.setForeground(SystemColor.textInactiveText);
		lblEnterNewPassword.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 14));
		lblEnterNewPassword.setBounds(135, 159, 140, 22);
		contentPane.add(lblEnterNewPassword);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBackground(Color.WHITE);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				ShopDAOImpl dao = new ShopDAOImpl();
				Validation valid = new Validation();
				String email = textEmail.getText();
				String securityWord = textWord.getText();
				String password = passwordField.getText();
				if(dao.updatePassword(email, securityWord, password) == true && valid.checkPassword(password) == true) {
					JOptionPane.showMessageDialog(null, "Password has been updated");
				}else {
					JOptionPane.showMessageDialog(null, "Incorrect email or security word");
				}
			}

		});
		btnUpdate.setForeground(SystemColor.textInactiveText);
		btnUpdate.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 13));
		btnUpdate.setBounds(135, 236, 125, 29);
		contentPane.add(btnUpdate);

		passwordField = new JPasswordField();
		passwordField.setBounds(125, 197, 157, 26);
		contentPane.add(passwordField);
		JLabel lblNewLabel = new JLabel("Password Recovery");
		lblNewLabel.setForeground(SystemColor.textInactiveText);
		lblNewLabel.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 22));
		lblNewLabel.setBounds(125, 6, 195, 29);
		contentPane.add(lblNewLabel);

		JLabel lblTypeYourSecurity = new JLabel(" Type your email and security word");
		lblTypeYourSecurity.setForeground(new Color(178, 34, 34));
		lblTypeYourSecurity.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 16));
		lblTypeYourSecurity.setBounds(104, 45, 238, 21);
		contentPane.add(lblTypeYourSecurity);

		textWord = new JTextField();
		textWord.setBounds(132, 124, 147, 26);
		contentPane.add(textWord);
		textWord.setColumns(10);
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(new Color(178, 34, 34));
		lblEmail.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 16));
		lblEmail.setBounds(73, 94, 40, 21);
		contentPane.add(lblEmail);

		JLabel lblSecurityWord = new JLabel("Security Word");
		lblSecurityWord.setForeground(new Color(178, 34, 34));
		lblSecurityWord.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 16));
		lblSecurityWord.setBounds(19, 126, 98, 21);
		contentPane.add(lblSecurityWord);
		textEmail = new JTextField();
		textEmail.setColumns(10);
		textEmail.setBounds(132, 91, 147, 26);
		contentPane.add(textEmail);

		JButton btnReturnToLogin = new JButton("Return to Login Page");
		btnReturnToLogin.setBackground(Color.WHITE);
		btnReturnToLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login log = new Login();
				log.setVisible(true);
			}
		});
		btnReturnToLogin.setForeground(new Color(178, 34, 34));
		btnReturnToLogin.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 13));
		btnReturnToLogin.setBounds(283, 236, 155, 27);
		contentPane.add(btnReturnToLogin);

	}
}
