package system;

import controllers.UserKioskViewController;
import views.UserKioskView;

public class App {
	
	
	/**
	 * System entry point
	 * @param args unused
	 */
	public static void main(String[] args) {

		UserKioskView userView = new UserKioskView();
		UserKioskViewController shopController = new UserKioskViewController(userView);

		userView.setVisible(true);

	}
}
