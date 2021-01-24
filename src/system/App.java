package system;

import java.util.ArrayList;

import controller.LoginController;
import controller.StockDatabaseController;
import models.Stock;
import models.StockDatabase;
import view.StaffLoginView;
import view.StockDatabaseView;

public class App {
	public static void main(String[] args) {
		
		  StaffLoginView view = new StaffLoginView(); LoginController controller = new
		  LoginController(view);
		 

		/*
		 * StockDatabase stockDatabase = new StockDatabase(new ArrayList<Stock>());
		 * 
		 * stockDatabase.addStock( new Stock(1, "beans", 2.00f, 15));
		 * stockDatabase.addStock( new Stock(2, "bacon", 3.50f, 3));
		 * stockDatabase.addStock( new Stock(3, "salt", 0.75f, 5));
		 * stockDatabase.addStock( new Stock(4, "pepper", 0.80f, 10));
		 * stockDatabase.addStock( new Stock(5, "ham sandwich", 2.60f, 15));
		 * stockDatabase.addStock( new Stock(6, "eggs", 1.12f, 10));
		 * stockDatabase.addStock( new Stock(7, "chicken", 3.80f, 11));
		 * 
		 * 
		 * StockDatabaseView view = new StockDatabaseView(); StockDatabaseController
		 * controller = new StockDatabaseController(view, stockDatabase);
		 */

		view.setVisible(true);
	}
}
