package me.techii.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import me.techii.services.LoginRegistrationService;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;

public class LoginRegistrationWindow extends JFrame {
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	private static String windowName = "Login/Registration Form - BudgetBuddy";

	private int userId;

	private int windowWidth = 450;
	private int windowHeight = 295;

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
		setBounds((screenSize.width / 2) - (windowWidth / 2), (screenSize.height / 2) - (windowHeight / 2), windowWidth,
				windowHeight);
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
				LoginRegistrationService.login(loginUsernameField, loginPasswordField, loginError, serialVersionUID, frame);
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
				LoginRegistrationService.register(registrationPasswordField, registrationRePasswordField, registrationUsernameField, registrationUsernameError, registrationUsernameErrorPresent, registrationPasswordError, registrationPasswordErrorPresent, registrationPanel, loginPanel);
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
