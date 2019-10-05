package org.am02316.com1028;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.am02316.com1028.DAO.ShopDAOImpl;
import org.am02316.com1028.Functionality.Rating;

import java.awt.Color;
import javax.swing.JRadioButton;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RatingPage extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	JTextField id;
	JTextField title;
	JTextField quantity;
	JTextField seller;
	int idRate;
	JRadioButton btn1 = new JRadioButton("1");
	JRadioButton btn2 = new JRadioButton("2");
	JRadioButton btn3 = new JRadioButton("3");
	JRadioButton btn4 = new JRadioButton("4");
	JRadioButton btn5 = new JRadioButton("5");
	JTextField emailCust;


	/**
	 * Create the frame.
	 */
	String email = null;

	public RatingPage(String email) {
		this.email = email;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(178, 34, 34));
		panel.setBounds(0, 0, 456, 56);
		contentPane.add(panel);

		buttonGroup.add(btn1);
		btn1.setBackground(Color.WHITE);
		btn1.setForeground(SystemColor.textInactiveText);
		btn1.setFont(new Font("Bodoni 72", Font.BOLD, 15));
		btn1.setBounds(17, 266, 40, 23);
		contentPane.add(btn1);

		buttonGroup.add(btn2);
		btn2.setBackground(Color.WHITE);
		btn2.setForeground(SystemColor.textInactiveText);
		btn2.setFont(new Font("Bodoni 72", Font.BOLD, 15));
		btn2.setBounds(101, 266, 40, 23);
		contentPane.add(btn2);

		buttonGroup.add(btn3);
		btn3.setBackground(Color.WHITE);
		btn3.setForeground(SystemColor.textInactiveText);
		btn3.setFont(new Font("Bodoni 72", Font.BOLD, 15));
		btn3.setBounds(190, 266, 40, 23);
		contentPane.add(btn3);

		buttonGroup.add(btn4);
		btn4.setBackground(Color.WHITE);
		btn4.setForeground(SystemColor.textInactiveText);
		btn4.setFont(new Font("Bodoni 72", Font.BOLD, 15));
		btn4.setBounds(272, 266, 40, 23);
		contentPane.add(btn4);

		buttonGroup.add(btn5);
		btn5.setBackground(Color.WHITE);
		btn5.setForeground(SystemColor.textInactiveText);
		btn5.setFont(new Font("Bodoni 72", Font.BOLD, 15));
		btn5.setBounds(355, 266, 40, 23);
		contentPane.add(btn5);

		JLabel lblNewLabel = new JLabel("Rate:");
		lblNewLabel.setForeground(new Color(178, 34, 34));
		lblNewLabel.setFont(new Font("Bodoni 72 Oldstyle", Font.BOLD, 22));
		lblNewLabel.setBounds(190, 225, 54, 29);
		contentPane.add(lblNewLabel);

		JButton btnNewButton = new JButton("Submit");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ShopDAOImpl shop = new ShopDAOImpl();
				String num = buttonGroup.getSelection().getActionCommand();
				Rating rate = new Rating(num, idRate, seller.getText());

				if (shop.addToRating(rate) == true) {
					JOptionPane.showMessageDialog(null, "Your review has been saved.");
				} else {
					JOptionPane.showMessageDialog(null, "Try again.");
				}
			}
		});
		btnNewButton.setForeground(new Color(178, 34, 34));
		btnNewButton.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 14));
		btnNewButton.setBounds(159, 301, 117, 29);
		contentPane.add(btnNewButton);

		id = new JTextField();
		id.setBounds(146, 68, 130, 26);
		contentPane.add(id);
		id.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Id");
		lblNewLabel_1.setForeground(SystemColor.textInactiveText);
		lblNewLabel_1.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(111, 73, 40, 16);
		contentPane.add(lblNewLabel_1);

		title = new JTextField();
		title.setColumns(10);
		title.setBounds(146, 106, 130, 26);
		contentPane.add(title);

		JLabel lblTitle = new JLabel("Title");
		lblTitle.setForeground(SystemColor.textInactiveText);
		lblTitle.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 15));
		lblTitle.setBounds(101, 111, 40, 16);
		contentPane.add(lblTitle);

		quantity = new JTextField();
		quantity.setColumns(10);
		quantity.setBounds(159, 182, 49, 26);
		contentPane.add(quantity);

		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setForeground(SystemColor.textInactiveText);
		lblQuantity.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 15));
		lblQuantity.setBounds(101, 184, 54, 20);
		contentPane.add(lblQuantity);

		seller = new JTextField();
		seller.setColumns(10);
		seller.setBounds(159, 144, 117, 26);
		contentPane.add(seller);

		JLabel lblSellersEmail = new JLabel("Seller's email");
		lblSellersEmail.setForeground(SystemColor.textInactiveText);
		lblSellersEmail.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 15));
		lblSellersEmail.setBounds(65, 148, 87, 20);
		contentPane.add(lblSellersEmail);

		JTextPane txtpnPleaseRateSeller = new JTextPane();
		txtpnPleaseRateSeller.setForeground(SystemColor.textInactiveText);
		txtpnPleaseRateSeller.setFont(new Font("Bodoni 72 Oldstyle", Font.BOLD, 15));
		txtpnPleaseRateSeller.setText("Please rate seller depending on a quality of delivered book");
		txtpnPleaseRateSeller.setBounds(288, 72, 125, 159);
		contentPane.add(txtpnPleaseRateSeller);

		emailCust = new JTextField();
		emailCust.setColumns(10);
		emailCust.setBounds(0, 63, 99, 26);
		contentPane.add(emailCust);
	}
}
