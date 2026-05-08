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
	 */
	public ViewStatisticsWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds((screenSize.width / 2) - (windowWidth / 2), (screenSize.height / 2) - (windowHeight / 2), windowWidth, windowHeight);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnClose.setBounds(306, 274, 118, 42);
		contentPane.add(btnClose);
		btnClose.setBackground(Color.RED);
		
		JLabel lblNewLabel = new JLabel("Total Expenses:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBounds(45, 148, 118, 14);
		contentPane.add(lblNewLabel);

	}

}
