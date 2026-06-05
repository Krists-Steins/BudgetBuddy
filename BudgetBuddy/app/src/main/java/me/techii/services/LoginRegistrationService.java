package me.techii.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import me.techii.GUI.LoginRegistrationWindow;
import me.techii.GUI.MainWindow;
import me.techii.services.base.Database;

public class LoginRegistrationService {
	private static int userId;

	public static void setUserID(int userID) {
		userId = userID;
	}

	public static int getUserID() {
		return userId;
	}

	public static void login(JTextField loginUsernameField, JPasswordField loginPasswordField, JLabel loginError,
			double serialVersionUID, LoginRegistrationWindow frame) {
		if (loginUsernameField.getText().equals("") || loginPasswordField.getPassword().equals("")) {
			loginError.setText("ERROR: The username field or the password field can't be empty.");
		} else if (loginUsernameField.getText().contains(" ") || loginPasswordField.getText().contains(" ")) {
			loginError.setText("ERROR: The username field or the password field can't contain a space.");
		} else {
			try {
				String encodedPassword = Base64.getEncoder()
						.encodeToString(new String(loginPasswordField.getPassword()).getBytes());

				boolean loggedIn = false;
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
					frame.dispose();
				} else {
					loginError.setText("ERROR: Username or password (or both) are incorrect.");
				}
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public static void register(JPasswordField registrationPasswordField, JPasswordField registrationRePasswordField,
			JTextField registrationUsernameField, JLabel registrationUsernameError,
			boolean registrationUsernameErrorPresent, JLabel registrationPasswordError,
			boolean registrationPasswordErrorPresent, JPanel registrationPanel, JPanel loginPanel) {
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

					PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
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
}
