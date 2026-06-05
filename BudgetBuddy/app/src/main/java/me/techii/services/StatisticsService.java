package me.techii.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;

import me.techii.services.base.Database;

public class StatisticsService {
	public static void showStatistics(int userID, JLabel foodLabel, JLabel transportLabel, JLabel entertainmentLabel, JLabel utilitiesLabel, JLabel otherLabel, JLabel totalExpensesLabel) throws ClassNotFoundException, SQLException {
		Database db = new Database();
		Connection conn = db.getConn();

		String sql = "SELECT * FROM Transactions WHERE user_id = " + userID;
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();

		double totalExpenses = 0;
		double food = 0, transport = 0, entertainment = 0, utilities = 0, other = 0;

		while (rs.next()) {
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

			if (type.equalsIgnoreCase("income")) {
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
		}

		rs.close();
		stmt.close();
		conn.close();

		double food_percent, transport_percent, entertainment_percent, utilities_percent, other_percent;

		food_percent = Math.round((food / totalExpenses) * 100);
		transport_percent = Math.round((transport / totalExpenses) * 100);
		entertainment_percent = Math.round((entertainment / totalExpenses) * 100);
		utilities_percent = Math.round((utilities / totalExpenses) * 100);
		other_percent = Math.round((other / totalExpenses) * 100);

		foodLabel.setText("€ " + food + " (" + food_percent + "%)");
		transportLabel.setText("€ " + transport + " (" + transport_percent + "%)");
		entertainmentLabel.setText("€ " + entertainment + " (" + entertainment_percent + "%)");
		utilitiesLabel.setText("€ " + utilities + " (" + utilities_percent + "%)");
		otherLabel.setText("€ " + other + " (" + other_percent + "%)");

		totalExpensesLabel.setText("€ " + totalExpenses);
	}
}
