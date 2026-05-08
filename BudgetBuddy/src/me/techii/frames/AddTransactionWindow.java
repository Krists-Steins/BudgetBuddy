package me.techii.frames;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Color;

public class AddTransactionWindow extends JFrame {
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	private int windowWidth = 450;
	private int windowHeight = 366;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddTransactionWindow frame = new AddTransactionWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddTransactionWindow() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		textField = new JTextField();
		textField.setBounds(122, 119, 242, 34);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDescription.setBounds(10, 176, 102, 22);
		contentPane.add(lblDescription);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(122, 172, 242, 99);
		contentPane.add(textField_1);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.setBackground(Color.GREEN);
		btnNewButton.setBounds(122, 282, 117, 34);
		contentPane.add(btnNewButton);
		
		JButton btnCancel = new JButton("Cancel");
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
