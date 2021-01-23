package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.Stock;
import models.StockDatabase;
import view.StockDatabaseView;

public class StockDatabaseController {
	
	StockDatabase model;
	StockDatabaseView view;
	
	public StockDatabaseController(StockDatabaseView view, StockDatabase model) {
		this.view = view;
		this.model = model;
		
		view.addStockListener(new StockListener());
		
		view.getStockList().setModel(model);
		
		
	}
	
	class StockListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Stock stock = view.getStock();
					
			view.displayMessage("Stock: " + stock.getCode() + " " + stock.getName() + " " +  stock.getPrice() + " " +  stock.getQuantity());
			model.addStock(stock);
			model.fireTableDataChanged();
			
		}


	
	}
}
