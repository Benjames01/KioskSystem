package controller;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.SwingUtilities;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import models.Stock;
import models.StockDatabase;
import view.StockDatabaseView;

public class StockDatabaseController {
	
	StockDatabase model;
	StockDatabaseView view;
	
	public StockDatabaseController(StockDatabaseView view) {
		this.view = view;
				
         model = new StockDatabase(new ArrayList<Stock>());
        
         model.addStock( new Stock(1, "beans", 2.00f, 15));
         model.addStock( new Stock(2, "bacon", 3.50f, 3));
         model.addStock( new Stock(3, "salt", 0.75f, 5));
         model.addStock( new Stock(4, "pepper", 0.80f, 10));
         model.addStock( new Stock(5, "ham sandwich", 2.60f, 15));
         model.addStock( new Stock(6, "eggs", 1.12f, 10));
         model.addStock( new Stock(7, "chicken", 3.80f, 11));
		
		view.addStockListener(new StockListener());
		view.addEditListener(new EditListener());
		view.addDeleteListener(new DeleteListener());
		view.addPopupMenuListener(new PopupListener());
		view.addNewBtnListener(new NewBtnListener());
		
		view.getStockList().setModel(model);
		
		
	}
	
	class StockListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Stock stock = view.getStock();
					
			model.addStock(stock);
			model.fireTableDataChanged();
			
		}
	}
	
	class NewBtnListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			Stock stock = new Stock();
			view.displayStock(stock);			
		}
	}
	
	class EditListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {		
			int index = view.getSelectedStockIndex();
			if (index > -1) {
				view.displayStock(model.getStockAt(index));
			}
		
		}
	}
	
	class DeleteListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {		
			int index = view.getSelectedStockIndex();
			if (index > -1) {
				if(view.getConfirmation("Are you sure you want to delete?") == 0) {
					model.removeStockAt(index);
					view.displayMessage("Deleted Successfully!");	
					model.fireTableDataChanged();
				} 	
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
