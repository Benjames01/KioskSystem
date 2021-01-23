package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import models.Credentials;

public class StaffLoginView extends JFrame implements ActionListener {

	
	private static final long serialVersionUID = -299886269054918983L;
	
	JTextField txtUsername;
	JPasswordField txtPassword;
	JButton btnLogin;
	Credentials employee;
	
    public StaffLoginView(){
        super("Login ");
 
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
 
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
    }
    public void actionPerformed(ActionEvent e) {
    }
 
    public Credentials getUser(){
    	employee = new Credentials(txtUsername.getText(), new String(txtPassword.getPassword()));
    	showMessage(txtUsername.getText() + " : " + new String(txtPassword.getPassword()));
        return employee;       
    }
 
    public void showMessage(String msg){
        JOptionPane.showMessageDialog(this, msg);
    }
 
    public void addLoginListener(ActionListener log) {
          btnLogin.addActionListener(log);
        }

}
