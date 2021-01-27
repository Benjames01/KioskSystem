package models.lists;

import persistence.StockDAO;
import views.gui.StockView;

import java.sql.SQLException;
import java.util.ArrayList;

public class StockViewList {

	StockDAO stockDB;

	ArrayList<StockView> stock = new ArrayList<StockView>();

	public StockViewList() {

		try {
			stockDB = new StockDAO();
			stock = stockDB.getStockDisplayContainers(stockDB.getAllStock());

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Getters and Setters
	 */

	public ArrayList<StockView> getStock() {
		return stock;
	}

	public void setStock(ArrayList<StockView> stock) {
		this.stock = stock;
	}


}
