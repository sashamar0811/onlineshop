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
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Register extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnCustomer = new JRadioButton("Customer");
	private JRadioButton rdbtnAdmin = new JRadioButton("Admin");
	private JRadioButton rdbtnSeller = new JRadioButton("Seller");
	private final JButton btnReturnToLogin = new JButton("Return to Login Page");

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
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
	public Register() {
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
		panel.setBounds(0, 316, 678, 78);
		contentPane.add(panel);

		JLabel lblRegistrationForm = new JLabel("Register as:");
		lblRegistrationForm.setForeground(SystemColor.textInactiveText);
		lblRegistrationForm.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 22));
		lblRegistrationForm.setBounds(225, 6, 178, 27);
		contentPane.add(lblRegistrationForm);

		buttonGroup.add(rdbtnCustomer);
		rdbtnCustomer.setBackground(Color.WHITE);
		rdbtnCustomer.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 13));
		rdbtnCustomer.setForeground(SystemColor.textInactiveText);
		rdbtnCustomer.setBounds(95, 151, 95, 27);
		contentPane.add(rdbtnCustomer);

		buttonGroup.add(rdbtnAdmin);
		rdbtnAdmin.setBackground(Color.WHITE);
		rdbtnAdmin.setForeground(SystemColor.textInactiveText);
		rdbtnAdmin.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 13));
		rdbtnAdmin.setBounds(274, 151, 95, 27);
		contentPane.add(rdbtnAdmin);

		buttonGroup.add(rdbtnSeller);
		rdbtnSeller.setBackground(Color.WHITE);
		rdbtnSeller.setForeground(SystemColor.textInactiveText);
		rdbtnSeller.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 13));
		rdbtnSeller.setBounds(418, 151, 95, 27);
		contentPane.add(rdbtnSeller);

		JButton btnContinue = new JButton("Continue");
		btnContinue.setBackground(Color.WHITE);
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {

				if (rdbtnCustomer.isSelected()) {
					CustomerRegister cust = new CustomerRegister();
					cust.setVisible(true);
					dispose();
				}
				if (rdbtnAdmin.isSelected()) {
					AdminRegister ad = new AdminRegister();
					ad.setVisible(true);
					dispose();
				}
				if (rdbtnSeller.isSelected()) {
					SellerRegister sel = new SellerRegister();
					sel.setVisible(true);
					dispose();
				}

			}
		});
		btnContinue.setForeground(SystemColor.textInactiveText);
		btnContinue.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 13));
		btnContinue.setBounds(252, 239, 117, 29);
		contentPane.add(btnContinue);
		btnReturnToLogin.setBackground(Color.WHITE);
		btnReturnToLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login log = new Login();
				log.setVisible(true);
			}
		});
		btnReturnToLogin.setForeground(new Color(178, 34, 34));
		btnReturnToLogin.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 13));
		btnReturnToLogin.setBounds(489, 10, 155, 27);

		contentPane.add(btnReturnToLogin);

	}
}
