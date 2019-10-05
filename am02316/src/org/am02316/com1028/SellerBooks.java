package org.am02316.com1028;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.am02316.com1028.Functionality.Book;
import org.am02316.com1028.DAO.ShopDAOImpl;

import java.awt.Color;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.MatteBorder;
import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextPane;

public class SellerBooks extends JFrame {

	private JPanel contentPane;
	public JTable table;
	private JTextField bookID;
	JTextField email1;

	JComboBox quantity1 = new JComboBox();
	private String[] headers = { "Book ID", "Title", "Author", "Quantity", "Price" };

	DefaultTableModel model = new DefaultTableModel();
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */

	String email = null;

	public SellerBooks(String email) {
		this.email = email;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 450);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Display Info");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg) {
				ShopDAOImpl shop = new ShopDAOImpl();

				Object[] column = new Object[5];
				column[0] = "Id";
				column[1] = "Title";
				column[2] = "Author";
				column[3] = "Quantity";
				column[4] = "Price";
				model.setColumnIdentifiers(column);
				Object[] rowData = new Object[5];
				for (int i = 0; i < shop.showSellerBooks(email).size(); i++) {

					rowData[0] = shop.showSellerBooks(email).get(i).getId();
					rowData[1] = shop.showSellerBooks(email).get(i).getTitle();
					rowData[2] = shop.showSellerBooks(email).get(i).getAuthor();
					rowData[3] = shop.showSellerBooks(email).get(i).getQuantity();
					rowData[4] = shop.showSellerBooks(email).get(i).getPrice();
					model.addRow(rowData);
				}
				table.setModel(model);

			}
		});
		btnNewButton.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 13));
		btnNewButton.setForeground(SystemColor.textInactiveText);
		btnNewButton.setBounds(27, 16, 117, 29);
		contentPane.add(btnNewButton);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg) {
			}
		});
		table.setToolTipText("");
		table.setBorder(UIManager.getBorder("TextField.border"));
		table.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 12));
		table.setBounds(5, 70, 673, 197);
		contentPane.add(table);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(178, 34, 34));
		panel.setBounds(-15, 370, 693, 69);
		contentPane.add(panel);

		JLabel lblNewLabel = new JLabel("Added books:");
		lblNewLabel.setForeground(new Color(178, 34, 34));
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel.setBounds(239, 21, 113, 20);
		contentPane.add(lblNewLabel);

		JButton btnReturnToMain = new JButton("Return to Main Page");
		btnReturnToMain.setBackground(Color.WHITE);
		btnReturnToMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = email1.getText();
				SellerPage sel = new SellerPage(email);
				sel.email1.setText(email);
				sel.setVisible(true);
				dispose();
			}
		});
		btnReturnToMain.setForeground(SystemColor.textInactiveText);
		btnReturnToMain.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 13));
		btnReturnToMain.setBounds(512, 8, 151, 27);
		contentPane.add(btnReturnToMain);

		JButton btnUpdateInfo = new JButton("Update Quantity");
		btnUpdateInfo.setBackground(Color.WHITE);
		btnUpdateInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				String id = bookID.getText();
				String email = email1.getText();
				String quantity = quantity1.getSelectedItem().toString();
				ShopDAOImpl shop = new ShopDAOImpl();
				if (shop.updateBookInStock(id, email, quantity) == true) {

					JOptionPane.showMessageDialog(null, "Quantity has been updated.");
				} else {
					JOptionPane.showMessageDialog(null, "Quantity has not been updated.");
				}

			}

		});
		btnUpdateInfo.setForeground(new Color(178, 34, 34));
		btnUpdateInfo.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 13));
		btnUpdateInfo.setBounds(178, 339, 127, 27);
		contentPane.add(btnUpdateInfo);

		bookID = new JTextField();
		bookID.setBounds(14, 296, 142, 26);
		contentPane.add(bookID);
		bookID.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Book ID");
		lblNewLabel_1.setForeground(SystemColor.textInactiveText);
		lblNewLabel_1.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(15, 279, 61, 16);
		contentPane.add(lblNewLabel_1);
		quantity1.setBackground(Color.WHITE);

		quantity1.setModel(new DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
				"10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20" }));
		quantity1.setBounds(73, 340, 77, 27);
		contentPane.add(quantity1);

		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setForeground(SystemColor.textInactiveText);
		lblQuantity.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 13));
		lblQuantity.setBounds(15, 344, 61, 16);
		contentPane.add(lblQuantity);

		email1 = new JTextField();
		email1.setColumns(10);
		email1.setBounds(364, 7, 127, 26);
		contentPane.add(email1);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(178, 34, 34));
		panel_1.setBounds(322, 268, 4, 100);
		contentPane.add(panel_1);

		JButton btnRemoveBookFrom = new JButton("Remove Book from the Stock");
		btnRemoveBookFrom.setBackground(Color.WHITE);
		btnRemoveBookFrom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {

				int row = table.getSelectedRow();
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				String selected = model.getValueAt(row, 0).toString();
				ShopDAOImpl dao = new ShopDAOImpl();

				if (dao.removeBooksFromStock(selected))
					JOptionPane.showMessageDialog(null, "Book has been deleted from the stock.");

			}
		});
		btnRemoveBookFrom.setForeground(new Color(178, 34, 34));
		btnRemoveBookFrom.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 13));
		btnRemoveBookFrom.setBounds(398, 327, 201, 27);
		contentPane.add(btnRemoveBookFrom);

		JLabel lblNewLabel_2 = new JLabel("Book ID");
		lblNewLabel_2.setForeground(SystemColor.textInactiveText);
		lblNewLabel_2.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(5, 45, 61, 16);
		contentPane.add(lblNewLabel_2);

		JLabel lblTitle = new JLabel("Title");
		lblTitle.setForeground(SystemColor.textInactiveText);
		lblTitle.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 13));
		lblTitle.setBounds(132, 45, 61, 16);
		contentPane.add(lblTitle);

		JLabel lblAuthor = new JLabel("Author");
		lblAuthor.setForeground(SystemColor.textInactiveText);
		lblAuthor.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 13));
		lblAuthor.setBounds(263, 45, 61, 16);
		contentPane.add(lblAuthor);

		JLabel lblQuantity_1 = new JLabel("Quantity");
		lblQuantity_1.setForeground(SystemColor.textInactiveText);
		lblQuantity_1.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 13));
		lblQuantity_1.setBounds(386, 45, 61, 16);
		contentPane.add(lblQuantity_1);

		JLabel lblPrice = new JLabel("Price per book (\u00A3)");
		lblPrice.setForeground(SystemColor.textInactiveText);
		lblPrice.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 13));
		lblPrice.setBounds(513, 45, 111, 18);
		contentPane.add(lblPrice);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(445, 285, 6, 26);
		contentPane.add(textPane);
		
		JLabel lblSelectBookFrom = new JLabel("Select book from the table");
		lblSelectBookFrom.setForeground(SystemColor.textInactiveText);
		lblSelectBookFrom.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblSelectBookFrom.setBounds(424, 301, 152, 18);
		contentPane.add(lblSelectBookFrom);
	}
}
