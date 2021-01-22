package system;

import controllers.LoginController;
import views.StaffLoginView;

public class App {
    public static void main(String[] args) {
        StaffLoginView view       = new StaffLoginView();
        LoginController controller = new LoginController(view);        
        view.setVisible(true);
    }
}
