package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import models.Stock;
import models.StockDatabase;
import persistence.StockDAO;
import views.StaffLoginView;
import views.StockDisplayContainer;
import views.UserKioskView;

public class ShopStockDisplayController {
	
	ArrayList<StockDisplayContainer> models;
	UserKioskView view;
	
	public ShopStockDisplayController(UserKioskView view) {
		
		try {
			this.view = view;
			StockDAO stockDB = new StockDAO();
			
			models = getStockDisplayContainers(stockDB.getAllStock());
			
			view.setStockDisplayList(models);
			
			view.addButtonListener(new DisplayButtonListener());
			view.addQuantityListener(new DisplayQuantityListener());
			view.addAdminButtonListener(new AdminButtonListener());			
					
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public ArrayList<StockDisplayContainer> getStockDisplayContainers(ArrayList<Stock> stockList) {
		ArrayList<StockDisplayContainer> stockDisplayList = new ArrayList<>();
		
		for(Stock stock : stockList) {
			StockDisplayContainer temp = new StockDisplayContainer(stock);
			
			stockDisplayList.add(temp);
		}
		
		return stockDisplayList;
	}
	
	
	class DisplayQuantityListener implements ChangeListener{

		@Override
		public void stateChanged(ChangeEvent e) {
			JSpinner spinner = (JSpinner) e.getSource();
			StockDisplayContainer display = view.getStockDisplayFromSpinner(spinner);
			
			System.out.println("Value changed.");
			
			if (display != null) {
				
				int quantity = (int) spinner.getValue();
				float total = (float) quantity * display.getStock().getPrice();
				
				display.setQuantity((int) spinner.getValue());
				display.setTotal(total);
			}
			
		}
		
	}
	
	
	class DisplayButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equalsIgnoreCase("passStock")) {
				
				JButton pressedButton = (JButton) e.getSource();
				StockDisplayContainer display = view.getStockDisplayFromButton(pressedButton);
				
				if (display != null) {
					
				}
			}
			
		}	
	}
	
	class AdminButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			  StaffLoginView staffView = new StaffLoginView();
			  LoginController controller = new LoginController(staffView);		  
			  
			  view.dispose();
			  staffView.setVisible(true);
			
		}
		
	}
	
}
