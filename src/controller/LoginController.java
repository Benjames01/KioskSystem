package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import models.Credentials;
import models.Stock;
import models.StockDatabase;
import view.StaffLoginView;
import view.StockDatabaseView;

public class LoginController {
    Credentials model;
    StaffLoginView view;
 
    public LoginController(StaffLoginView view){
        this.view = view;
 
        view.addLoginListener(new LoginListener());
    }
 
    class LoginListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                model = view.getUser();
                if(checkCredentials(model)){
                    view.showMessage("Welcome, " + model.getUsername() + "!");
                              
                    StockDatabaseView stockView = new StockDatabaseView();
                    StockDatabaseController controller = new StockDatabaseController(stockView);  
                    
                    stockView.setVisible(true);
                    view.setVisible(false);
                    
                }else{
                    view.showMessage("Invalid Username/Password!");
                }                
            } catch (Exception ex) {
                view.showMessage(ex.getStackTrace().toString());
            }
        }
    }
 
    public boolean checkCredentials(Credentials user) {	
    	if (user.getUsername().equalsIgnoreCase("admin") && user.getPassword().equals("admin")) {
    		return true;
    	}
    	return false;
      }
} 
