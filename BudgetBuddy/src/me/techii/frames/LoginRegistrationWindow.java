package me.techii.frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import me.techii.services.base.Database;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class LoginRegistrationWindow extends JFrame {
	private static String windowName = "Login/Registration Form - BudgetBuddy";

	private int userId;
	
	private static final double serialVersionUID = 0.1D;
	private JPanel contentPane;
	private JPasswordField loginPasswordField;
	private JTextField loginUsernameField;
	private JPasswordField registrationPasswordField;
	private JTextField registrationUsernameField;
	private JPasswordField registrationRePasswordField;
	
	private static LoginRegistrationWindow frame;
	
	private boolean registrationUsernameErrorPresent = false;
	private boolean registrationPasswordErrorPresent = false;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new LoginRegistrationWindow();
					frame.setTitle(windowName);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void setUserID(int userID) {
		this.userId = userID;
	}
	
	public int getUserID() {
		return userId;
	}

	/**
	 * Create the frame.
	 */
	public LoginRegistrationWindow() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel loginPanel = new JPanel();
		loginPanel.setBounds(10, 11, 414, 239);
		contentPane.add(loginPanel);
		loginPanel.setLayout(null);
		
		JPanel registrationPanel = new JPanel();
		registrationPanel.setVisible(false);
		registrationPanel.setBounds(10, 11, 414, 239);
		contentPane.add(registrationPanel);
		registrationPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 394, 35);
		loginPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 58, 394, 14);
		loginPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Re-enter password");
		lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1_1.setBounds(224, 128, 180, 14);
		registrationPanel.add(lblNewLabel_1_1_1_1);
		
		registrationRePasswordField = new JPasswordField();
		registrationRePasswordField.setBounds(224, 149, 180, 35);
		registrationPanel.add(registrationRePasswordField);
		
		JLabel registrationPasswordError = new JLabel("");
		registrationPasswordError.setFont(new Font("Tahoma", Font.PLAIN, 9));
		registrationPasswordError.setForeground(Color.RED);
		registrationPasswordError.setHorizontalAlignment(SwingConstants.CENTER);
		registrationPasswordError.setBounds(10, 184, 394, 14);
		registrationPanel.add(registrationPasswordError);
		
		JLabel registrationUsernameError = new JLabel("");
		registrationUsernameError.setFont(new Font("Tahoma", Font.PLAIN, 9));
		registrationUsernameError.setHorizontalAlignment(SwingConstants.CENTER);
		registrationUsernameError.setForeground(Color.RED);
		registrationUsernameError.setBounds(10, 116, 394, 14);
		registrationPanel.add(registrationUsernameError);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setBounds(10, 128, 394, 14);
		loginPanel.add(lblNewLabel_1_1);
		
		JLabel loginError = new JLabel("");
		loginError.setFont(new Font("Tahoma", Font.PLAIN, 9));
		loginError.setHorizontalAlignment(SwingConstants.CENTER);
		loginError.setForeground(Color.RED);
		loginError.setBounds(10, 184, 394, 14);
		loginPanel.add(loginError);
		
		loginPasswordField = new JPasswordField();
		loginPasswordField.setBounds(95, 149, 225, 35);
		loginPanel.add(loginPasswordField);
		
		loginUsernameField = new JTextField();
		loginUsernameField.setBounds(95, 80, 225, 35);
		loginPanel.add(loginUsernameField);
		loginUsernameField.setColumns(10);
		
		JButton LoginButton = new JButton("Login");
		LoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (loginUsernameField.getText().equals("") || loginPasswordField.getPassword().equals("")) {
					loginError.setText("ERROR: The username field or the password field can't be empty.");
				} else if (loginUsernameField.getText().contains(" ") || loginPasswordField.getText().contains(" ")) {
					loginError.setText("ERROR: The username field or the password field can't contain a space.");
				} else {
					try {
						String encodedPassword = Base64.getEncoder().encodeToString(new String(loginPasswordField.getPassword()).getBytes());
						
						boolean loggedIn = false;
						String passwordS = new String(loginPasswordField.getPassword());
						
						Database db = new Database();
						Connection conn = db.getConn();
						
						String sql = "SELECT * FROM Users";
						
						PreparedStatement stmt = conn.prepareStatement(sql);
						ResultSet rs = stmt.executeQuery();
						
						while (rs.next()) {
							int userID = rs.getInt("user_id");
							String username = rs.getString("username");
							String password = rs.getString("password");
							
							if (password.equals(encodedPassword) && username.equals(loginUsernameField.getText())) {
								setUserID(userID);
								loggedIn = true;
							}
						}
						
						if (loggedIn == true) {
							MainWindow window = new MainWindow(getUserID());
							window.setTitle("Budget Buddy - v" + serialVersionUID + " | userID = " + getUserID());
							window.setVisible(true);
							
							frame.setVisible(false);
						}
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
					loginError.setText("");
				}
			}
		});
		LoginButton.setBounds(161, 205, 89, 23);
		loginPanel.add(LoginButton);
		
		JButton RegistrationFormButton = new JButton("Register");
		RegistrationFormButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginPanel.setVisible(false);
				registrationPanel.setVisible(true);
			}
		});
		
		RegistrationFormButton.setBounds(315, 205, 89, 23);
		loginPanel.add(RegistrationFormButton);
		
		JLabel lblRegister = new JLabel("REGISTER");
		lblRegister.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegister.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblRegister.setBounds(10, 11, 394, 35);
		registrationPanel.add(lblRegister);
		
		JLabel lblNewLabel_1_2 = new JLabel("Username");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setBounds(10, 58, 394, 14);
		registrationPanel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Password");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setBounds(10, 128, 180, 14);
		registrationPanel.add(lblNewLabel_1_1_1);
		
		registrationPasswordField = new JPasswordField();
		registrationPasswordField.setBounds(10, 149, 180, 35);
		registrationPanel.add(registrationPasswordField);
		
		registrationUsernameField = new JTextField();
		registrationUsernameField.setBounds(95, 80, 225, 35);
		registrationPanel.add(registrationUsernameField);
		
		JButton RegisterButton = new JButton("Register");
		RegisterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String passwordS = new String(registrationPasswordField.getPassword());
				String rePasswordS = new String(registrationRePasswordField.getPassword());
				
				if (registrationUsernameField.getText().equals("")) {
					registrationUsernameError.setText("ERROR: Username field can't be empty.");
					registrationUsernameErrorPresent = true;
				} else if (registrationUsernameField.getText().contains(" ")) {
					registrationUsernameError.setText("ERROR: Username can't contain spaces.");
					registrationUsernameErrorPresent = true;
				} else {
					registrationUsernameError.setText("");
					registrationUsernameErrorPresent = false;
				}
				
				if (!passwordS.equals(rePasswordS)) {
					registrationPasswordError.setText("ERROR: Both of the password fields have to be the same.");
					registrationPasswordErrorPresent = true;
				} else if (passwordS.isEmpty()) {
					registrationPasswordError.setText("ERROR: The password field can't be empty.");
					registrationPasswordErrorPresent = true;
				} else {
					registrationPasswordError.setText("");
					registrationPasswordErrorPresent = false;
				}
				
				if (registrationPasswordErrorPresent == false && registrationUsernameErrorPresent == false) {
					try {
						boolean userExists = false;
						
						String encodedPassword = Base64.getEncoder().encodeToString(passwordS.getBytes());
						
						Database db = new Database();
						Connection conn = db.getConn();
						
						String checkSQL = "SELECT * FROM Users";
						PreparedStatement checkSTMT = conn.prepareStatement(checkSQL);
						ResultSet checkRS = checkSTMT.executeQuery();
						
						while (checkRS.next()) {
							String username = checkRS.getString("username");
							
							if (registrationUsernameField.getText().equals(username)) {
								registrationPasswordError.setText("ERROR: A user with this username already exists.");
								userExists = true;
								return;
							}
						}
						if (userExists == false) {
							String sql = "INSERT INTO Users (username, password, balance) VALUES (?, ?, ?)";
							
							PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
							stmt.setString(1, registrationUsernameField.getText());
							stmt.setString(2, encodedPassword);
							stmt.setDouble(3, 0);
	
							boolean isInserted = stmt.execute();
							
							if (!isInserted) {
								System.out.println("Inserted");
								registrationPanel.setVisible(false);
								loginPanel.setVisible(true);
							} else {
								System.out.println("Not inserted");
							}
						}
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		RegisterButton.setBounds(161, 205, 89, 23);
		registrationPanel.add(RegisterButton);
		
		JButton LoginFormButton = new JButton("Login");
		LoginFormButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registrationPanel.setVisible(false);
				loginPanel.setVisible(true);
			}
		});
		LoginFormButton.setBounds(315, 205, 89, 23);
		registrationPanel.add(LoginFormButton);
		
	}
}
