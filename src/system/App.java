package system;

import controllers.UserKioskViewController;
import controllers.StockDatabaseController;
import views.StockDatabaseView;
import views.UserKioskView;

public class App {
	public static void main(String[] args) {

		UserKioskView userView = new UserKioskView();
		UserKioskViewController shopController = new UserKioskViewController(userView);

		userView.setVisible(true);

	}
}
