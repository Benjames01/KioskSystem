package system;

import controllers.UserKioskViewController;
import controllers.StockDatabaseController;
import views.StockDatabaseView;
import views.UserKioskView;

public class App {
	public static void main(String[] args) {

		//StaffLoginView view = new StaffLoginView();
		//LoginController controller = new LoginController(view);


		UserKioskView userView = new UserKioskView();
		UserKioskViewController shopController = new UserKioskViewController(userView);

		userView.setVisible(true);


		/*
		 * StockDatabaseView view = new StockDatabaseView(); StockDatabaseController
		 * controller = new StockDatabaseController(view);
		 * 
		 * view.setVisible(true);
		 */
	}
}
