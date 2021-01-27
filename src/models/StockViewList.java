package models;

import java.sql.SQLException;
import java.util.ArrayList;

import persistence.StockDAO;
import views.StockView;

public class StockViewList {

	ArrayList<StockView> stock;
	
	public StockViewList() {
		
		try {
			StockDAO stockDB = new StockDAO();
			stock = stockDB.getStockDisplayContainers(stockDB.getAllStock());
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
