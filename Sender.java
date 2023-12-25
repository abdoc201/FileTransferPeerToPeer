package com;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JFileChooser;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class Sender extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
    private DefaultTableModel tableModel;
	private JTextField textField;

	public Sender() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				SendRecieve frame = new SendRecieve();
				Main.setVisibility(frame);
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(44, 34, 115, 46);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Reset Table");
		btnNewButton_1.setBounds(588, 34, 156, 46);
		contentPane.add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(129, 109, 545, 274);
		contentPane.add(scrollPane);


		tableModel = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Name", "Type", "Size", "Path"}
        ) {
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
        table = new JTable(tableModel);
		scrollPane.setViewportView(table);
		
		textField = new JTextField();
		textField.setBounds(150, 404, 156, 29);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("Upload");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setMultiSelectionEnabled(true);
				int result = fileChooser.showOpenDialog(getParent());
				if (result == JFileChooser.APPROVE_OPTION) {
					File[] selectedFiles = fileChooser.getSelectedFiles();
					for (File file : selectedFiles) {
						String Name = file.getName().split("[.]")[0];
                		String Type = file.getName().split("\\.",2)[1];
						long Size = file.length() / 1024 ;
						String Path = file.getAbsolutePath();
						Object[] rowData = {Name, Type, Size, Path};
						tableModel.addRow(rowData);
					}
				}
			}
		});
		btnNewButton_2.setBounds(412, 394, 115, 39);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Send");
		btnNewButton_3.setBounds(553, 394, 115, 39);
		contentPane.add(btnNewButton_3);
		
		JLabel lblNewLabel = new JLabel("IP Destination :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(44, 404, 96, 29);
		contentPane.add(lblNewLabel);
	}
}
