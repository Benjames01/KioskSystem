package controllers;

import models.Order;
import models.Stock;
import models.persistence.OrderDatabase;
import models.persistence.StockDatabase;
import views.StockDatabaseView;
import views.UserKioskView;
import views.gui.OrderView;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class StockDatabaseController {

	StockDatabase model;
	OrderDatabase orderDB;
	StockDatabaseView view;


	public StockDatabaseController(StockDatabaseView view) {
		this.view = view;
		view.setLocationRelativeTo(null);

		try {
			model = new StockDatabase(true);
			orderDB = new OrderDatabase();

			orderDB.getOrderViews(orderDB.getOrders());

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
		view.setOrderList(orderDB.getOrderViews(orderDB.getOrders()));

		view.addOrderButtonListener(new OrderButtonListener());

		ArrayList<Stock> emptyStockList = model.getEmptyStock();


		if (!emptyStockList.isEmpty()) {
			String emptyStockString = "The following stock needs replenishing: \n\n";

			for (Stock stock : emptyStockList) {
				emptyStockString += stock.toString() + "\n";
			}

			view.displayMessage(emptyStockString);
		}
	}
	
	/*
	 * Listeners for MVC pattern
	 *
	 */

	class StockListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Stock stock = view.getStock();

			model.addStock(stock);
			model.fireTableDataChanged();

		}
	}

	class NewBtnListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Stock stock = new Stock();
			view.displayStock(stock);
		}
	}

	class EditListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int index = view.getSelectedStockIndex();
			if (index > -1) {
				view.displayStock(model.getStockAt(index));
			}

		}
	}

	class DeleteListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int index = view.getSelectedStockIndex();
			if (index > -1 && view.getConfirmation("Are you sure you want to delete?") == 0) {
				model.removeStockAt(index);
				view.displayMessage("Deleted Successfully!");
				model.fireTableDataChanged();
			}
		}
	}

	class OrderButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			JButton pressedButton = (JButton) e.getSource();
			OrderView display = view.getOrderViewFromButton(pressedButton);

			if (display != null) {

				Order order = display.getOrder();
				Stock stock = model.getStockFromCode(order.getCode());

				stock.setQuantity(order.getQuantity() + stock.getQuantity());

				model.addStock(stock);
				model.fireTableDataChanged();

				orderDB.removeOrder(order.getID());

				view.setOrderList(orderDB.getOrderViews(orderDB.getOrders()));
				view.addOrderButtonListener(new OrderButtonListener());
			}

		}
	}

	class OrderListener implements ActionListener {

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
					Order order = new Order(stock.getCode(), stock.getName(), stock.getPrice(), (int) spinner.getValue());
					orderDB.addOrder(order);

					ArrayList<Order> orders = orderDB.getOrders();

					ArrayList<OrderView> orderViews = orderDB.getOrderViews(orders);

					view.setOrderList(orderViews);
					view.addOrderButtonListener(new OrderButtonListener());
				}

			}
		}
	}

	class SwitchKioskListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			UserKioskView userView = new UserKioskView();
			UserKioskViewController controller2 = new UserKioskViewController(userView);

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
		 */
		@Override
		public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					int rowAtSelect = view.getStockList()
							.rowAtPoint(SwingUtilities.convertPoint(view.getPopup(), new Point(0, 0), view.getStockList()));
					if (rowAtSelect > -1) {
						view.getStockList().setRowSelectionInterval(rowAtSelect, rowAtSelect);
					}

				}

			});

		}
	}

}
