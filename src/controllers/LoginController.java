package controllers;

import models.Credentials;
import views.StaffLoginView;
import views.StockDatabaseView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
	Credentials model;
	StaffLoginView view;

	public LoginController(StaffLoginView view) {
		this.view = view;

		view.addLoginListener(new LoginListener());
	}

	public boolean checkCredentials(Credentials user) {
		return user.getUsername().equalsIgnoreCase("admin") && user.getPassword().equals("admin");
	}

	class LoginListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				model = view.getUser();
				if (checkCredentials(model)) {
					view.showMessage("Welcome, " + model.getUsername() + "!");

					StockDatabaseView stockView = new StockDatabaseView();
					StockDatabaseController controller = new StockDatabaseController(stockView);

					stockView.setVisible(true);
					view.dispose();

				} else {
					view.showMessage("Invalid Username/Password!");
				}
			} catch (Exception ex) {
				view.showMessage(ex.getMessage());
			}
		}
	}
} 
