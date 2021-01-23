package controller;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

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
		view.addEditListener(new EditListener());
		view.addPopupMenuListener(new PopupListener());
		
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
	
	class EditListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {		
			int index = view.getSelectedStockIndex();
			if (index > -1) {

				view.displayMessage("Edit clicked at: " + index);	
				view.displayStock( model.getStockAt(index));
			}
		
		}
	}
	
	class PopupListener implements PopupMenuListener {

		@Override
		public void popupMenuCanceled(PopupMenuEvent e) {
			
		}

		@Override
		public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
			// TODO Auto-generated method stub
			
		}

		
		/**
		 * Select the row where the user right clicks on the table
		 * 
		 */
		@Override
		public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					int rowAtSelect = view.getStockList()
							.rowAtPoint(SwingUtilities.convertPoint(view.getPopup(), new Point(0,0), view.getStockList()));
					if(rowAtSelect > -1 ) {
						view.getStockList().setRowSelectionInterval(rowAtSelect, rowAtSelect);
					}
					
				}
				
			});
			
		}
		
	}
}
