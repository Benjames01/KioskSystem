package controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import models.StockDatabase;
import view.StockDatabaseView;

public class StockDatabaseController {
	
	StockDatabase model;
	StockDatabaseView view;
	
	public StockDatabaseController(StockDatabaseView view, StockDatabase model) {
		this.view = view;
		
		view.addDBListener(new DBListener());
		
		view.getStockList().setModel(model);
		
		
	}
	
	class DBListener implements PropertyChangeListener{

		@Override
		public void propertyChange(PropertyChangeEvent arg0) {
			
			
		}
	
	}
}
