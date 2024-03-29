package views;

import models.Credentials;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class StaffLoginView extends JFrame implements ActionListener {


	private static final long serialVersionUID = -299886269054918983L;

	JTextField txtUsername;
	JPasswordField txtPassword;
	JButton btnLogin;
	Credentials employee;

	
	/**
	 * Setup and creates the StaffLoginView
	 */
	public StaffLoginView() {
		super("use username & password: admin");
		this.setLocationRelativeTo(null);

		txtUsername = new JTextField(15);
		txtPassword = new JPasswordField(15);
		txtPassword.setEchoChar('*');
		btnLogin = new JButton("Login");

		JPanel content = new JPanel();
		content.setLayout(new FlowLayout());
		content.add(new JLabel("Username:"));
		content.add(txtUsername);
		content.add(new JLabel("Password:"));
		content.add(txtPassword);
		content.add(btnLogin);

		btnLogin.addActionListener(this);

		this.setContentPane(content);
		this.pack();

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}
	
	
	/**
	 * returns a new Credentials model from the view
	 * @return
	 */
	public Credentials getUser() {
		employee = new Credentials(txtUsername.getText(), new String(txtPassword.getPassword()));
		return employee;
	}

	
	/**
	 * Displays the given String msg to the user
	 */
	public void showMessage(String msg) {
		JOptionPane.showMessageDialog(this, msg);
	}

	/**
	 * Used to add the given ActionListener log to the btnLogin
	 * @param log
	 */
	public void addLoginListener(ActionListener log) {
		btnLogin.addActionListener(log);
	}

}
