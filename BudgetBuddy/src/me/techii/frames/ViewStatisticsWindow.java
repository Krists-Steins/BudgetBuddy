package me.techii.frames;

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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewStatisticsWindow frame = new ViewStatisticsWindow();
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
	public ViewStatisticsWindow() throws ClassNotFoundException, SQLException {
		Database db = new Database();
		Connection conn = db.getConn();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds((screenSize.width / 2) - (windowWidth / 2), (screenSize.height / 2) - (windowHeight / 2), 450, 293);
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
		btnClose.setBounds(306, 201, 118, 42);
		contentPane.add(btnClose);
		btnClose.setBackground(Color.RED);
		
		JLabel lblNewLabel = new JLabel("Total Expenses");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(0, 105, 173, 14);
		contentPane.add(lblNewLabel);
		
		JLabel Income_Label_1_1_1 = new JLabel("€ 0");
		Income_Label_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		Income_Label_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Income_Label_1_1_1.setBounds(0, 130, 173, 26);
		contentPane.add(Income_Label_1_1_1);
		
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
		
		JLabel Food_Label = new JLabel("€ 0");
		Food_Label.setHorizontalAlignment(SwingConstants.RIGHT);
		Food_Label.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Food_Label.setBounds(348, 36, 76, 14);
		contentPane.add(Food_Label);
		
		JLabel Transport_Label = new JLabel("€ 0");
		Transport_Label.setHorizontalAlignment(SwingConstants.RIGHT);
		Transport_Label.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Transport_Label.setBounds(348, 70, 76, 14);
		contentPane.add(Transport_Label);
		
		JLabel Entertainment_Label = new JLabel("€ 0");
		Entertainment_Label.setHorizontalAlignment(SwingConstants.RIGHT);
		Entertainment_Label.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Entertainment_Label.setBounds(348, 104, 76, 14);
		contentPane.add(Entertainment_Label);
		
		JLabel Utilities_Label = new JLabel("€ 0");
		Utilities_Label.setHorizontalAlignment(SwingConstants.RIGHT);
		Utilities_Label.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Utilities_Label.setBounds(348, 140, 76, 14);
		contentPane.add(Utilities_Label);
		
		JLabel Other_Label = new JLabel("€ 0");
		Other_Label.setHorizontalAlignment(SwingConstants.RIGHT);
		Other_Label.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Other_Label.setBounds(348, 177, 76, 14);
		contentPane.add(Other_Label);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(172, 0, 1, 254);
		contentPane.add(panel);

	}
}
