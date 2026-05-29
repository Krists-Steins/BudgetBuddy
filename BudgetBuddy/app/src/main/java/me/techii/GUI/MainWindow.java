package me.techii.GUI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import me.techii.services.base.Database;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;

public class MainWindow extends JFrame {
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	private int windowWidth = 1002;
	private int windowHeight = 470;

	private static final double serialVersionUID = 0.1;
	private JPanel contentPane;
	private JPanel transactionsContainer;

	private String[] kategorijuMasivs = { "", "Income", "Food", "Transport", "Entertainment", "Utilities", "Other" };

	private JLabel Income_Label;
	private JLabel Food_label;
	private JLabel Transport_label;
	private JLabel Entertainment_label;
	private JLabel Utilities_label;
	private JLabel Other_label;

	private JLabel Total_Income_Label;
	private JLabel Total_Expenses_Label;
	private JLabel Balance_Label;

	private JLabel Budget_Limit_Label;
	private JLabel Budget_Spent_Label;
	private JPanel BudgetMeterGreenThingy;
	private JLabel Budget_Percent_Label;

	public static int userId;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow(userId);
					frame.setTitle("Budget Buddy - v" + serialVersionUID + " | userID = " + userId);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainWindow(int userID) throws ClassNotFoundException, SQLException {
		this.userId = userID;

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds((screenSize.width / 2) - (windowWidth / 2), (screenSize.height / 2) - (windowHeight / 2), windowWidth,
				windowHeight);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel categories_panel = new JPanel();
		categories_panel.setBorder(new LineBorder(new Color(0, 128, 255), 3));
		categories_panel.setBounds(10, 11, 199, 49);
		contentPane.add(categories_panel);

		JLabel lblNewLabel = new JLabel("Categories");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		categories_panel.add(lblNewLabel);

		JPanel categories_panel2 = new JPanel();
		categories_panel2.setBorder(new LineBorder(new Color(0, 0, 0)));
		categories_panel2.setBounds(10, 71, 199, 349);
		contentPane.add(categories_panel2);
		categories_panel2.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Income");
		lblNewLabel_1.setBounds(10, 16, 76, 14);
		categories_panel2.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));

		JLabel lblNewLabel_1_1 = new JLabel("Food");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1_1.setBounds(10, 68, 76, 14);
		categories_panel2.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Transport");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1_1_1.setBounds(10, 121, 76, 14);
		categories_panel2.add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Entertainment");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1_1_1_1.setBounds(10, 176, 120, 14);
		categories_panel2.add(lblNewLabel_1_1_1_1);

		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Utilities");
		lblNewLabel_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1_1_1_1_1.setBounds(10, 230, 120, 14);
		categories_panel2.add(lblNewLabel_1_1_1_1_1);

		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Other");
		lblNewLabel_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1_1_1_1_1_1.setBounds(10, 280, 120, 14);
		categories_panel2.add(lblNewLabel_1_1_1_1_1_1);

		JLabel lblNewLabel_1_1_1_1_1_1_1 = new JLabel("* Amounts are for current month");
		lblNewLabel_1_1_1_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_1_1_1_1_1_1.setBounds(10, 305, 179, 33);
		categories_panel2.add(lblNewLabel_1_1_1_1_1_1_1);

		Income_Label = new JLabel("€ 0");
		Income_Label.setHorizontalAlignment(SwingConstants.RIGHT);
		Income_Label.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Income_Label.setBounds(113, 15, 76, 14);
		categories_panel2.add(Income_Label);

		Food_label = new JLabel("€ 0");
		Food_label.setHorizontalAlignment(SwingConstants.RIGHT);
		Food_label.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Food_label.setBounds(113, 68, 76, 14);
		categories_panel2.add(Food_label);

		Transport_label = new JLabel("€ 0");
		Transport_label.setHorizontalAlignment(SwingConstants.RIGHT);
		Transport_label.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Transport_label.setBounds(113, 121, 76, 14);
		categories_panel2.add(Transport_label);

		Entertainment_label = new JLabel("€ 0");
		Entertainment_label.setHorizontalAlignment(SwingConstants.RIGHT);
		Entertainment_label.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Entertainment_label.setBounds(113, 176, 76, 14);
		categories_panel2.add(Entertainment_label);

		Utilities_label = new JLabel("€ 0");
		Utilities_label.setHorizontalAlignment(SwingConstants.RIGHT);
		Utilities_label.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Utilities_label.setBounds(113, 230, 76, 14);
		categories_panel2.add(Utilities_label);

		Other_label = new JLabel("€ 0");
		Other_label.setHorizontalAlignment(SwingConstants.RIGHT);
		Other_label.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Other_label.setBounds(113, 280, 76, 14);
		categories_panel2.add(Other_label);

		JPanel transactions_panel = new JPanel();
		transactions_panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		transactions_panel.setBounds(219, 11, 535, 409);
		contentPane.add(transactions_panel);
		transactions_panel.setLayout(null);

		JLabel lblTransactions = new JLabel("Transactions");
		lblTransactions.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTransactions.setBounds(10, 11, 116, 22);
		transactions_panel.add(lblTransactions);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 44, 515, 35);
		transactions_panel.add(panel);
		panel.setLayout(new GridLayout(1, 5, 0, 0));

		JLabel date_label = new JLabel("Date");
		date_label.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panel.add(date_label);

		JLabel description_label = new JLabel("Description");
		description_label.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panel.add(description_label);

		JLabel category_label = new JLabel("Category");
		category_label.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panel.add(category_label);

		JLabel type_label = new JLabel("Type");
		type_label.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panel.add(type_label);

		JLabel amount_label = new JLabel("Amount");
		amount_label.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panel.add(amount_label);

		JButton addNewTransactionBtn = new JButton("Add Transaction");
		addNewTransactionBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					AddTransactionWindow transactionWindow = new AddTransactionWindow(MainWindow.this);
					transactionWindow.setTitle("Add transaction form - BudgetBuddy");
					transactionWindow.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		addNewTransactionBtn.setBackground(new Color(85, 170, 255));
		addNewTransactionBtn.setBounds(194, 11, 143, 23);
		transactions_panel.add(addNewTransactionBtn);

		transactionsContainer = new JPanel();
		transactionsContainer.setLayout(new BoxLayout(transactionsContainer, BoxLayout.Y_AXIS));

		JScrollPane transaction_scrollPane = new JScrollPane(transactionsContainer);
		transaction_scrollPane.setBounds(10, 77, 515, 321);
		transactions_panel.add(transaction_scrollPane);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(764, 11, 212, 409);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblSummary = new JLabel("Summary");
		lblSummary.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSummary.setBounds(10, 11, 116, 22);
		panel_1.add(lblSummary);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(10, 44, 192, 168);
		panel_1.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel_1_2 = new JLabel("Total Income");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2.setBounds(10, 18, 86, 14);
		panel_2.add(lblNewLabel_1_2);

		Total_Income_Label = new JLabel("€ 0");
		Total_Income_Label.setHorizontalAlignment(SwingConstants.RIGHT);
		Total_Income_Label.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Total_Income_Label.setBounds(106, 18, 76, 14);
		panel_2.add(Total_Income_Label);

		JLabel lblNewLabel_1_2_1 = new JLabel("Total Expenses");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2_1.setBounds(10, 53, 103, 14);
		panel_2.add(lblNewLabel_1_2_1);

		Total_Expenses_Label = new JLabel("€ 0");
		Total_Expenses_Label.setHorizontalAlignment(SwingConstants.RIGHT);
		Total_Expenses_Label.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Total_Expenses_Label.setBounds(106, 53, 76, 14);
		panel_2.add(Total_Expenses_Label);

		JPanel Line = new JPanel();
		Line.setBorder(new LineBorder(new Color(0, 0, 0)));
		Line.setBounds(10, 88, 172, 1);
		panel_2.add(Line);

		JLabel lblNewLabel_1_2_1_1 = new JLabel("Balance");
		lblNewLabel_1_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2_1_1.setBounds(10, 106, 103, 14);
		panel_2.add(lblNewLabel_1_2_1_1);

		Balance_Label = new JLabel("€ 0");
		Balance_Label.setHorizontalAlignment(SwingConstants.RIGHT);
		Balance_Label.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Balance_Label.setBounds(10, 131, 172, 26);
		panel_2.add(Balance_Label);

		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2_1.setBounds(10, 223, 192, 123);
		panel_1.add(panel_2_1);
		panel_2_1.setLayout(null);

		JLabel lblNewLabel_1_2_1_1_1 = new JLabel("Budget");
		lblNewLabel_1_2_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_1_1_1.setBounds(10, 11, 61, 17);
		panel_2_1.add(lblNewLabel_1_2_1_1_1);

		JLabel lblNewLabel_1_2_1_2 = new JLabel("(This month)");
		lblNewLabel_1_2_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2_1_2.setBounds(70, 12, 88, 14);
		panel_2_1.add(lblNewLabel_1_2_1_2);

		JLabel lblNewLabel_1_2_1_2_1 = new JLabel("Limit:");
		lblNewLabel_1_2_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2_1_2_1.setBounds(10, 34, 40, 14);
		panel_2_1.add(lblNewLabel_1_2_1_2_1);

		Budget_Limit_Label = new JLabel("€ 500");
		Budget_Limit_Label.setFont(new Font("Tahoma", Font.BOLD, 17));
		Budget_Limit_Label.setBounds(60, 34, 122, 14);
		panel_2_1.add(Budget_Limit_Label);

		JLabel lblNewLabel_1_2_1_2_1_1 = new JLabel("Spent:");
		lblNewLabel_1_2_1_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2_1_2_1_1.setBounds(10, 57, 50, 14);
		panel_2_1.add(lblNewLabel_1_2_1_2_1_1);

		Budget_Spent_Label = new JLabel("€ 0");
		Budget_Spent_Label.setFont(new Font("Tahoma", Font.BOLD, 17));
		Budget_Spent_Label.setBounds(60, 57, 122, 14);
		panel_2_1.add(Budget_Spent_Label);

		JPanel BudgetMeter_Outline = new JPanel();
		BudgetMeter_Outline.setBorder(new LineBorder(new Color(0, 0, 0)));
		BudgetMeter_Outline.setBounds(10, 82, 172, 30);
		panel_2_1.add(BudgetMeter_Outline);
		BudgetMeter_Outline.setLayout(null);

		Budget_Percent_Label = new JLabel("0%");
		Budget_Percent_Label.setFont(new Font("Tahoma", Font.BOLD, 11));
		Budget_Percent_Label.setHorizontalAlignment(SwingConstants.CENTER);
		Budget_Percent_Label.setBounds(0, 0, 172, 30);
		BudgetMeter_Outline.add(Budget_Percent_Label);

		BudgetMeterGreenThingy = new JPanel();
		BudgetMeterGreenThingy.setBackground(new Color(255, 0, 0));
		BudgetMeterGreenThingy.setBounds(0, 0, 0, 30);
		BudgetMeter_Outline.add(BudgetMeterGreenThingy);

		JButton btnViewStatistics = new JButton("View Statistics");
		btnViewStatistics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ViewStatisticsWindow statsWindow = new ViewStatisticsWindow(userID);
					statsWindow.setTitle("Statistics Window - BudgetBuddy");
					statsWindow.setVisible(true);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnViewStatistics.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnViewStatistics.setBackground(new Color(85, 170, 255));
		btnViewStatistics.setBounds(10, 357, 192, 41);
		panel_1.add(btnViewStatistics);

		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userId = 0;
				dispose();
				LoginRegistrationWindow LRWindow = new LoginRegistrationWindow();
				LRWindow.setTitle("Login/Registration Form - BudgetBuddy");
				LRWindow.setVisible(true);
			}
		});
		btnLogout.setBackground(new Color(85, 170, 255));
		btnLogout.setBounds(125, 11, 77, 23);
		panel_1.add(btnLogout);

		refreshData();
	}

	public void refreshData() {
		try {
			showTransactions();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void showTransactions() throws SQLException, ClassNotFoundException {
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
}