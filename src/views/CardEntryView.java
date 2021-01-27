package views;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;

public class CardEntryView extends JFrame {

	private static final long serialVersionUID = -3706812152329687887L;
	
	
	JTextField textCardNumberField;
	JTextField textPinField;
	
	JButton btnNewButton;
	
	JLabel lblCompanyName;

	public CardEntryView() {
		this.setPreferredSize(new Dimension(600,400));
		getContentPane().setPreferredSize(new Dimension(600,100));
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Card Number:");
		lblNewLabel.setBounds(58, 100, 82, 14);
		getContentPane().add(lblNewLabel);
		
		textCardNumberField = new JTextField();
		textCardNumberField.setBounds(150, 97, 189, 20);
		textCardNumberField.setDocument(new JTextFieldLimited(16));
		getContentPane().add(textCardNumberField);
		textCardNumberField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Pin :");
		lblNewLabel_1.setBounds(103, 123, 37, 14);
		getContentPane().add(lblNewLabel_1);
		
		textPinField = new JTextField();
		textPinField.setBounds(150, 120, 45, 20);
		textPinField.setDocument(new JTextFieldLimited(6));
		getContentPane().add(textPinField);
		textPinField.setColumns(10);
		
		btnNewButton = new JButton("Enter Card");
		btnNewButton.setBounds(136, 212, 155, 23);
		getContentPane().add(btnNewButton);
		
		lblCompanyName = new JLabel("Company Name Payments");
		lblCompanyName.setBounds(10, 11, 155, 14);
		getContentPane().add(lblCompanyName);
		
		textCardNumberField.setEnabled(true);
		textPinField.setEnabled(true);
		btnNewButton.setEnabled(true);
		
		
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.out.println("Dispose");
				getFrame().dispose();
			}
		});

		this.pack();
		
	}
	
	
	JFrame getFrame() {
		return this;
	}

	public JTextField getTextCardNumberField() {
		return textCardNumberField;
	}

	public JTextField getTextPinField() {
		return textPinField;
	}

	public JLabel getCardCompany() {
		return lblCompanyName;
	}
	
	public JButton getButton() {
		return btnNewButton;
	}
	
	public void addButtonListener(ActionListener listener) {
		this.btnNewButton.addActionListener(listener);
	}
	
	public void displayMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}
}
