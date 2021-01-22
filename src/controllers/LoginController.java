package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import models.Credentials;
import views.StaffLoginView;

public class LoginController {
    private Credentials model;
    private StaffLoginView view;
 
    public LoginController(StaffLoginView view){
        this.view = view;
 
        view.addLoginListener(new LoginListener());
    }
 
    class LoginListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                model = view.getUser();
                if(checkCredentials(model)){
                    view.showMessage("Login succesfully!");
                }else{
                    view.showMessage("Invalid Username/Password!");
                }                
            } catch (Exception ex) {
                view.showMessage(ex.getStackTrace().toString());
            }
        }
    }
 
    public boolean checkCredentials(Credentials user) throws Exception {	
    	if (user.getUsername().equalsIgnoreCase("admin") && user.getPassword().equals("admin")) {
    		return true;
    	}
    	return false;
      }
} 
