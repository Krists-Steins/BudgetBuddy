package me.techii.frames;

import java.awt.EventQueue;
import java.awt.FlowLayout;

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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;

public class MainWindow extends JFrame {
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    
    private int windowWidth = 1002;
    private int windowHeight = 470;

    private static final double serialVersionUID = 0.1;
    private JPanel contentPane;

    // Container for scrollable transactions
    private JPanel transactionsContainer;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainWindow frame = new MainWindow();
                    frame.setTitle("Budget Buddy - v" + serialVersionUID);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     * @throws SQLException 
     * @throws ClassNotFoundException 
     */
    public MainWindow() throws ClassNotFoundException, SQLException {
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds((screenSize.width / 2) - (windowWidth / 2), (screenSize.height / 2) - (windowHeight / 2), windowWidth, windowHeight);
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
        
        JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Utilites");
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
        
        JLabel Income_Label = new JLabel("€ 0");
        Income_Label.setHorizontalAlignment(SwingConstants.RIGHT);
        Income_Label.setFont(new Font("Tahoma", Font.PLAIN, 17));
        Income_Label.setBounds(113, 15, 76, 14);
        categories_panel2.add(Income_Label);
        
        JLabel Food_label = new JLabel("€ 0");
        Food_label.setHorizontalAlignment(SwingConstants.RIGHT);
        Food_label.setFont(new Font("Tahoma", Font.PLAIN, 17));
        Food_label.setBounds(113, 68, 76, 14);
        categories_panel2.add(Food_label);
        
        JLabel Transport_label = new JLabel("€ 0");
        Transport_label.setHorizontalAlignment(SwingConstants.RIGHT);
        Transport_label.setFont(new Font("Tahoma", Font.PLAIN, 17));
        Transport_label.setBounds(113, 121, 76, 14);
        categories_panel2.add(Transport_label);
        
        JLabel Entertainment_label = new JLabel("€ 0");
        Entertainment_label.setHorizontalAlignment(SwingConstants.RIGHT);
        Entertainment_label.setFont(new Font("Tahoma", Font.PLAIN, 17));
        Entertainment_label.setBounds(113, 176, 76, 14);
        categories_panel2.add(Entertainment_label);
        
        JLabel Utilities_label = new JLabel("€ 0");
        Utilities_label.setHorizontalAlignment(SwingConstants.RIGHT);
        Utilities_label.setFont(new Font("Tahoma", Font.PLAIN, 17));
        Utilities_label.setBounds(113, 230, 76, 14);
        categories_panel2.add(Utilities_label);
        
        JLabel Other_label = new JLabel("€ 0");
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
        panel.setLayout(new GridLayout(0, 5, 0, 0));
        
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
                AddTransactionWindow transactionWindow;
                try {
                    transactionWindow = new AddTransactionWindow();
                    transactionWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                    transactionWindow.setVisible(true);
                    
                    addTransactionRow("Groceries", "Food", "Expense", 25);
                } catch (ClassNotFoundException | SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        addNewTransactionBtn.setBackground(new Color(85, 170, 255));
        addNewTransactionBtn.setForeground(new Color(0, 0, 0));
        addNewTransactionBtn.setBounds(194, 11, 143, 23);
        transactions_panel.add(addNewTransactionBtn);
        
        // Scrollable container for transactions
        transactionsContainer = new JPanel();
        transactionsContainer.setLayout(new BoxLayout(transactionsContainer, BoxLayout.Y_AXIS));
        
        JScrollPane transaction_scrollPane = new JScrollPane(transactionsContainer);
        transaction_scrollPane.setToolTipText("");
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
        
        JLabel Income_Label_1 = new JLabel("€ 0");
        Income_Label_1.setHorizontalAlignment(SwingConstants.RIGHT);
        Income_Label_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
        Income_Label_1.setBounds(106, 18, 76, 14);
        panel_2.add(Income_Label_1);
        
        JLabel lblNewLabel_1_2_1 = new JLabel("Total Expenses");
        lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1_2_1.setBounds(10, 53, 103, 14);
        panel_2.add(lblNewLabel_1_2_1);
        
        JLabel Income_Label_1_1 = new JLabel("€ 0");
        Income_Label_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
        Income_Label_1_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
        Income_Label_1_1.setBounds(106, 53, 76, 14);
        panel_2.add(Income_Label_1_1);
        
        JPanel panel_3 = new JPanel();
        panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel_3.setBounds(10, 88, 172, 1);
        panel_2.add(panel_3);
        
        JLabel lblNewLabel_1_2_1_1 = new JLabel("Balance");
        lblNewLabel_1_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1_2_1_1.setBounds(10, 106, 103, 14);
        panel_2.add(lblNewLabel_1_2_1_1);
        
        JLabel Income_Label_1_1_1 = new JLabel("€ 999");
        Income_Label_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
        Income_Label_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        Income_Label_1_1_1.setBounds(10, 131, 172, 26);
        panel_2.add(Income_Label_1_1_1);
        
        JPanel panel_2_1 = new JPanel();
        panel_2_1.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel_2_1.setBounds(10, 223, 192, 123);
        panel_1.add(panel_2_1);
        panel_2_1.setLayout(null);
        
        JLabel lblNewLabel_1_2_1_1_1 = new JLabel("Budget");
        lblNewLabel_1_2_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_1_2_1_1_1.setBounds(10, 11, 61, 17);
        panel_2_1.add(lblNewLabel_1_2_1_1_1);
        
        JLabel lblNewLabel_1_2_1_2 = new JLabel("(month year)");
        lblNewLabel_1_2_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1_2_1_2.setBounds(70, 12, 88, 14);
        panel_2_1.add(lblNewLabel_1_2_1_2);
        
        JLabel lblNewLabel_1_2_1_2_1 = new JLabel("Limit:");
        lblNewLabel_1_2_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1_2_1_2_1.setBounds(10, 34, 40, 14);
        panel_2_1.add(lblNewLabel_1_2_1_2_1);
        
        JLabel Income_Label_2 = new JLabel("€ 999");
        Income_Label_2.setHorizontalAlignment(SwingConstants.LEFT);
        Income_Label_2.setFont(new Font("Tahoma", Font.BOLD, 17));
        Income_Label_2.setBounds(60, 34, 122, 14);
        panel_2_1.add(Income_Label_2);
        
        JLabel lblNewLabel_1_2_1_2_1_1 = new JLabel("Spent:");
        lblNewLabel_1_2_1_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1_2_1_2_1_1.setBounds(10, 57, 50, 14);
        panel_2_1.add(lblNewLabel_1_2_1_2_1_1);
        
        JLabel Income_Label_2_1 = new JLabel("€ 0");
        Income_Label_2_1.setHorizontalAlignment(SwingConstants.LEFT);
        Income_Label_2_1.setFont(new Font("Tahoma", Font.BOLD, 17));
        Income_Label_2_1.setBounds(60, 57, 122, 14);
        panel_2_1.add(Income_Label_2_1);
        
        JPanel BudgetMeter_Outline = new JPanel();
        BudgetMeter_Outline.setBorder(new LineBorder(new Color(0, 0, 0)));
        BudgetMeter_Outline.setBounds(10, 82, 172, 30);
        panel_2_1.add(BudgetMeter_Outline);
        BudgetMeter_Outline.setLayout(null);
        
        JPanel BudgetMeterGreenThingy = new JPanel();
        BudgetMeterGreenThingy.setBorder(new LineBorder(new Color(0, 0, 0)));
        BudgetMeterGreenThingy.setBackground(new Color(128, 255, 0));
        BudgetMeterGreenThingy.setBounds(0, 0, 0, 30);
        BudgetMeter_Outline.add(BudgetMeterGreenThingy);
        
        JLabel lblNewLabel_2 = new JLabel("0%");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setBounds(62, 0, 46, 29);
        BudgetMeter_Outline.add(lblNewLabel_2);
        
        JButton btnViewStatistics = new JButton("View Statistics");
        btnViewStatistics.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ViewStatisticsWindow vSWindow;
                try {
                    vSWindow = new ViewStatisticsWindow();
                    vSWindow.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                    vSWindow.setVisible(true);
                } catch (ClassNotFoundException | SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnViewStatistics.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnViewStatistics.setForeground(Color.BLACK);
        btnViewStatistics.setBackground(new Color(85, 170, 255));
        btnViewStatistics.setBounds(10, 357, 192, 41);
        panel_1.add(btnViewStatistics);
    }

    public void addTransactionRow(String desc, String category, String type, double amount) throws SQLException, ClassNotFoundException {
    	LocalDate dateObj = LocalDate.now();
    	Date date = Date.valueOf(dateObj);
    	
        JPanel row = new JPanel();
        row.setLayout(new GridLayout(1, 5));
        row.setPreferredSize(new Dimension(500, 25));
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
        
        Database db = new Database();
        Connection conn = db.getConn();
        
        String sql = "INSERT INTO Transactions (amount, date, description, user_id, category_id) VALUES (?, ?, ?, ?, ?)";
		
		PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		stmt.setDouble(1, amount);
		stmt.setDate(2, date);
		stmt.setString(3, desc);
		stmt.setInt(4, 1);
		stmt.setInt(5, 1);

		boolean isInserted = stmt.execute();
		
		if (!isInserted) {
			System.out.println("Inserted");
		} else {
			System.out.println("Not inserted");
		}

        JLabel dateLabel = new JLabel(dateObj.toString());
        JLabel descLabel = new JLabel(desc);
        JLabel categoryLabel = new JLabel(category);
        JLabel typeLabel = new JLabel(type);
        JLabel amountLabel = new JLabel("€ " + amount);

        row.add(dateLabel);
        row.add(descLabel);
        row.add(categoryLabel);
        row.add(typeLabel);
        row.add(amountLabel);

        transactionsContainer.add(row);
        transactionsContainer.revalidate();
        transactionsContainer.repaint();
    }
}
