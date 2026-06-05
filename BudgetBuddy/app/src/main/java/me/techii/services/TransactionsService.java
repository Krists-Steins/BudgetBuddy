package me.techii.services;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import me.techii.GUI.MainWindow;
import me.techii.services.base.Database;

public class TransactionsService {
	public static void showTransactions(int userId, JPanel transactionsContainer, String[] kategorijuMasivs,
			JLabel Income_Label, JLabel Food_label, JLabel Transport_label, JLabel Entertainment_label,
			JLabel Utilities_label, JLabel Other_label, JLabel Total_Income_Label, JLabel Total_Expenses_Label,
			JLabel Balance_Label, JLabel Budget_Spent_Label, JLabel Budget_Percent_Label, JPanel BudgetMeterGreenThingy)
			throws SQLException, ClassNotFoundException {
		transactionsContainer.removeAll();

		Database db = new Database();
		Connection conn = db.getConn();

		String sql = "SELECT * FROM Transactions WHERE user_id = " + userId;
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();

		double totalIncome = 0;
		double totalExpenses = 0;
		double food = 0, transport = 0, entertainment = 0, utilities = 0, other = 0;

		while (rs.next()) {
			String date = rs.getDate("date").toString();
			String desc = rs.getString("description");
			String type = "";
			double amount = rs.getDouble("amount");
			int categoryId = rs.getInt("category_id");

			int i = 0;

			String categorySQL = "SELECT * FROM Categories";
			PreparedStatement categorySTMT = conn.prepareStatement(categorySQL);
			ResultSet categoryRS = categorySTMT.executeQuery();

			while (categoryRS.next() && i != categoryId) {
				type = categoryRS.getString("type");
				i++;
			}

			String categoryName = "Income";
			if (categoryId >= 1 && categoryId < kategorijuMasivs.length) {
				categoryName = kategorijuMasivs[categoryId];
			}

			if (type.equalsIgnoreCase("income")) {
				totalIncome += amount;
			} else {
				totalExpenses += amount;

				if (categoryId == 2)
					food += amount;
				else if (categoryId == 3)
					transport += amount;
				else if (categoryId == 4)
					entertainment += amount;
				else if (categoryId == 5)
					utilities += amount;
				else
					other += amount;
			}

			JPanel row = new JPanel();
			row.setLayout(new GridLayout(1, 5));
			row.setPreferredSize(new Dimension(500, 25));
			row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));

			row.add(new JLabel(date));
			row.add(new JLabel(desc));
			row.add(new JLabel(categoryName));
			row.add(new JLabel(type));
			row.add(new JLabel("€ " + amount));

			transactionsContainer.add(row);
		}

		double newBalance = totalIncome - totalExpenses;

		Income_Label.setText("€ " + totalIncome);
		Food_label.setText("€ " + food);
		Transport_label.setText("€ " + transport);
		Entertainment_label.setText("€ " + entertainment);
		Utilities_label.setText("€ " + utilities);
		Other_label.setText("€ " + other);

		Total_Income_Label.setText("€ " + totalIncome);
		Total_Expenses_Label.setText("€ " + totalExpenses);
		Balance_Label.setText("€ " + newBalance);

		String balSQL = "UPDATE Users SET balance = ? WHERE user_id = " + userId;
		PreparedStatement balSTMT = conn.prepareStatement(balSQL);
		balSTMT.setDouble(1, newBalance);
		balSTMT.executeUpdate();

		rs.close();
		stmt.close();
		conn.close();

		double budgetLimit = 500.0;
		Budget_Spent_Label.setText("€ " + totalExpenses);

		int percent = (int) ((totalExpenses / budgetLimit) * 100);
		if (percent > 100)
			percent = 100;

		Budget_Percent_Label.setText(percent + "%");
		int greenWidth = (int) ((percent / 100.0) * 172);
		BudgetMeterGreenThingy.setBounds(0, 0, greenWidth, 30);

		transactionsContainer.revalidate();
		transactionsContainer.repaint();
	}
	
	public static void addTransaction(JTextField amountField, JTextField descriptionField, JComboBox<?> comboBox_1, MainWindow mainWindow) {
		try {
			int errorCount = 0;
			String validCharacters = "1234567890.";
			
			double amount = Double.parseDouble(amountField.getText());
			String amountText = String.valueOf(amount);
			
			String desc = descriptionField.getText();

			int categoryId = comboBox_1.getSelectedIndex() + 1;
			int userId = mainWindow.userId;
			
			for (int i = 0; i < amountText.length(); i++) {
				for (int j = 0; j < validCharacters.length(); j++) {
					if (amountText.charAt(i) != validCharacters.charAt(j)) {
						errorCount++;
					} else {
						errorCount = 0;
						j = validCharacters.length() + 1;
						
					}
				}
				if (errorCount > 1) {
					return;
				}
			}
			
			if (errorCount == 0) {
				Database db = new Database();
				Connection conn = db.getConn();

				String sql = "INSERT INTO Transactions (amount, date, description, user_id, category_id) VALUES (?, ?, ?, ?, ?)";
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setDouble(1, amount);
				stmt.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
				stmt.setString(3, desc);
				stmt.setInt(4, userId);
				stmt.setInt(5, categoryId);

				stmt.executeUpdate();

				stmt.close();
				conn.close();

				mainWindow.refreshData();
			}
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
