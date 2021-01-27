package models;

import java.sql.SQLException;
import java.util.ArrayList;

import persistence.StockDAO;
import views.StockView;

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

	public ArrayList<StockView> getStock() {
		return stock;
	}

	public void setStock(ArrayList<StockView> stock) {
		this.stock = stock;
	}


}
