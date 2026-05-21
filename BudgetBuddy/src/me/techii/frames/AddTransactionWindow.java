package me.techii.frames;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import me.techii.services.base.Database;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.time.LocalDate;

public class AddTransactionWindow extends JFrame {
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	private MainWindow parent;

	private int windowWidth = 450;
	private int windowHeight = 366;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField amountField;
	private JTextField descriptionField;

	private MainWindow parentWindow;

	public AddTransactionWindow(MainWindow mainWindow) throws ClassNotFoundException, SQLException {

		this.parentWindow = parent;

		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds((screenSize.width / 2) - (windowWidth / 2), (screenSize.height / 2) - (windowHeight / 2), windowWidth, windowHeight);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Income", "Expense"}));
		comboBox.setBounds(122, 12, 302, 34);
		contentPane.add(comboBox);

		JLabel lblNewLabel = new JLabel("Type:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 18, 57, 22);
		contentPane.add(lblNewLabel);

		JLabel lblCategory = new JLabel("Category:");
		lblCategory.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCategory.setBounds(10, 69, 102, 22);
		contentPane.add(lblCategory);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Income", "Food", "Transport", "Entertainment", "Utilities", "Other"}));
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox_1.setBounds(122, 63, 302, 34);
		contentPane.add(comboBox_1);

		JLabel lblAmount = new JLabel("Amount:");
		lblAmount.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAmount.setBounds(10, 123, 102, 22);
		contentPane.add(lblAmount);

		amountField = new JTextField();
		amountField.setBounds(122, 119, 242, 34);
		contentPane.add(amountField);
		amountField.setColumns(10);

		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDescription.setBounds(10, 176, 102, 22);
		contentPane.add(lblDescription);

		descriptionField = new JTextField();
		descriptionField.setColumns(10);
		descriptionField.setBounds(122, 172, 242, 99);
		contentPane.add(descriptionField);

		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					double amount = Double.parseDouble(amountField.getText());
					String type = comboBox.getSelectedItem().toString().toLowerCase();
					String desc = descriptionField.getText();

					// Iegūstam kārtas numuru no saraksta: Income=1, Food=2, Transport=3...
					int categoryId = comboBox_1.getSelectedIndex() + 1;
					int userId = 1;

					Database db = new Database();
					Connection conn = db.getConn();

					// Klasisks, vienkāršs datu ierakstīšanas vaicājums
					String sql = "INSERT INTO Transactions (amount, date, description, type, user_id, category_id) VALUES (?, ?, ?, ?, ?, ?)";
					PreparedStatement stmt = conn.prepareStatement(sql);
					stmt.setDouble(1, amount);
					stmt.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
					stmt.setString(3, desc);
					stmt.setString(4, type);
					stmt.setInt(5, userId);
					stmt.setInt(6, categoryId);

					stmt.executeUpdate();

					stmt.close();
					conn.close();

					// Liekam galvenajam logam pārlasīt datus no jauna
					parentWindow.refreshData();
					dispose();

				} catch (NumberFormatException ex) {
					System.out.println("Kļūda: Ievadi skaitli!");
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnNewButton.setBackground(Color.GREEN);
		btnNewButton.setBounds(122, 282, 117, 34);
		contentPane.add(btnNewButton);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBackground(Color.RED);
		btnCancel.setBounds(247, 282, 117, 34);
		contentPane.add(btnCancel);

		JLabel lblNewLabel_1 = new JLabel("€");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(378, 119, 33, 34);
		contentPane.add(lblNewLabel_1);
	}
}