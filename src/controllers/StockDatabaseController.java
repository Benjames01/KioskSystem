package controllers;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import models.Order;
import models.OrderDatabase;
import models.Stock;
import models.StockDatabase;
import views.OrderView;
import views.StockDatabaseView;
import views.StockDisplayContainer;
import views.UserKioskView;

public class StockDatabaseController {

	StockDatabase model;
	OrderDatabase orderDB;
	StockDatabaseView view;

	ArrayList<OrderView> ordersList;

	public StockDatabaseController(StockDatabaseView view) {
		this.view = view;

		try {
			model = new StockDatabase(true);
			orderDB = new OrderDatabase();

			ordersList = orderDB.getOrderViews(orderDB.getOrders());

		} catch (SQLException e) {
			e.printStackTrace();
		}

		view.addStockListener(new StockListener());
		view.addEditListener(new EditListener());
		view.addDeleteListener(new DeleteListener());
		view.addOrderListener(new OrderListener());
		view.addPopupMenuListener(new PopupListener());
		view.addNewBtnListener(new NewBtnListener());
		view.addSwitchToKioskListener(new SwitchKioskListener());

		view.getStockList().setModel(model);
		view.setOrderList(ordersList);

		ArrayList<Stock> emptyStockList = model.getEmptyStock();


		if (!emptyStockList.isEmpty()) {
			String emptyStockString = "The following stock needs replenishing: \n\n";

			for(Stock stock : emptyStockList) {
				emptyStockString += stock.toString() + "\n";
			}

			view.displayMessage(emptyStockString);
		}		
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


	class OrderListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {		
			int index = view.getSelectedStockIndex();
			if (index > -1) {

				Stock stock = model.getStockAt(index);

				SpinnerNumberModel sModel = new SpinnerNumberModel(5, 0, 50, 1);
				JSpinner spinner = new JSpinner(sModel);

				int optionChosen = JOptionPane.showOptionDialog(null, spinner, "Enter quantity to order",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

				if (optionChosen == JOptionPane.OK_OPTION) {
					Order order = new Order(stock.getCode(), stock.getName(), stock.getPrice(), (int)spinner.getValue());
					orderDB.addOrder(order);

					ArrayList<Order> orders = orderDB.getOrders();

					ArrayList<OrderView> orderViews = orderDB.getOrderViews(orders);

					view.setOrderList(orderViews);		

					System.out.println(orderViews.size() + " order size: " + orders.size());
				}

			}		
		}
	}

	class SwitchKioskListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			UserKioskView userView = new UserKioskView();
			ShopStockDisplayController controller2 = new ShopStockDisplayController(userView);

			userView.setVisible(true);
			view.dispose();

		}

	}



	class PopupListener implements PopupMenuListener {

		@Override
		public void popupMenuCanceled(PopupMenuEvent e) {

		}

		@Override
		public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {		
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
