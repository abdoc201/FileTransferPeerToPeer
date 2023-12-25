package com;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;

public class SendRecieve extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public SendRecieve() {
        setTitle("File Transfer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Send");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Sender frame = new Sender();
				Main.setVisibility(frame);
				dispose();
			}
		});
		btnNewButton.setForeground(Color.RED);
		btnNewButton.setBackground(SystemColor.inactiveCaption);
		btnNewButton.setBounds(150, 189, 179, 87);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Recieve");
		btnNewButton_1.setBackground(SystemColor.inactiveCaption);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Reciever frame = new Reciever();
				Main.setVisibility(frame);
				dispose();
			}
		});
		btnNewButton_1.setForeground(Color.RED);
		btnNewButton_1.setBounds(439, 189, 179, 87);
		contentPane.add(btnNewButton_1);
	}
}