package me.techii.GUI;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import me.techii.services.base.Database;

public class ViewStatisticsWindow extends JFrame {
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	private int windowWidth = 450;
	private int windowHeight = 366;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JButton btnClose = new JButton("Close");

	private JLabel Total_Expenses_Label;

	private JLabel Food_Label;
	private JLabel Transport_Label;
	private JLabel Entertainment_Label;
	private JLabel Utilities_Label;
	private JLabel Other_Label;

	private String[] kategorijuMasivs = { "", "Income", "Food", "Transport", "Entertainment", "Utilities", "Other" };

	private static int userID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewStatisticsWindow frame = new ViewStatisticsWindow(userID);
					frame.setTitle("Statistics Window - BudgetBuddy");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public ViewStatisticsWindow(int userID) throws ClassNotFoundException, SQLException {
		setResizable(false);
		this.userID = userID;

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds((screenSize.width / 2) - (windowWidth / 2), (screenSize.height / 2) - (windowHeight / 2), 509, 293);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnClose.setBounds(358, 202, 118, 42);
		contentPane.add(btnClose);
		btnClose.setBackground(Color.RED);

		JLabel lblNewLabel = new JLabel("Total Expenses");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(0, 105, 173, 14);
		contentPane.add(lblNewLabel);

		Total_Expenses_Label = new JLabel("€ 0");
		Total_Expenses_Label.setHorizontalAlignment(SwingConstants.CENTER);
		Total_Expenses_Label.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Total_Expenses_Label.setBounds(0, 130, 173, 26);
		contentPane.add(Total_Expenses_Label);

		JLabel lblNewLabel_1 = new JLabel("Food");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(211, 36, 46, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Other");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1_1.setBounds(211, 177, 46, 14);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Entertainment");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1_2.setBounds(211, 104, 118, 14);
		contentPane.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel("Utilities");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1_3.setBounds(211, 140, 62, 14);
		contentPane.add(lblNewLabel_1_3);

		JLabel lblNewLabel_1_4 = new JLabel("Transport");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1_4.setBounds(211, 70, 99, 14);
		contentPane.add(lblNewLabel_1_4);

		Food_Label = new JLabel("€ 0");
		Food_Label.setHorizontalAlignment(SwingConstants.RIGHT);
		Food_Label.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Food_Label.setBounds(328, 36, 148, 14);
		contentPane.add(Food_Label);

		Transport_Label = new JLabel("€ 0");
		Transport_Label.setHorizontalAlignment(SwingConstants.RIGHT);
		Transport_Label.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Transport_Label.setBounds(328, 70, 148, 14);
		contentPane.add(Transport_Label);

		Entertainment_Label = new JLabel("€ 0");
		Entertainment_Label.setHorizontalAlignment(SwingConstants.RIGHT);
		Entertainment_Label.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Entertainment_Label.setBounds(328, 104, 148, 14);
		contentPane.add(Entertainment_Label);

		Utilities_Label = new JLabel("€ 0");
		Utilities_Label.setHorizontalAlignment(SwingConstants.RIGHT);
		Utilities_Label.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Utilities_Label.setBounds(328, 140, 148, 14);
		contentPane.add(Utilities_Label);

		Other_Label = new JLabel("€ 0");
		Other_Label.setHorizontalAlignment(SwingConstants.RIGHT);
		Other_Label.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Other_Label.setBounds(328, 177, 148, 14);
		contentPane.add(Other_Label);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(172, 0, 1, 254);
		contentPane.add(panel);

		showStatistics(userID);

	}

	public void showStatistics(int userID) throws ClassNotFoundException, SQLException {
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

		Food_Label.setText("€ " + food + " (" + food_percent + "%)");
		Transport_Label.setText("€ " + transport + " (" + transport_percent + "%)");
		Entertainment_Label.setText("€ " + entertainment + " (" + entertainment_percent + "%)");
		Utilities_Label.setText("€ " + utilities + " (" + utilities_percent + "%)");
		Other_Label.setText("€ " + other + " (" + other_percent + "%)");

		Total_Expenses_Label.setText("€ " + totalExpenses);
	}
}
