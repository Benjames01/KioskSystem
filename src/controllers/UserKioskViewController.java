package controllers;

import models.Stock;
import models.lists.ShoppingBasket;
import models.lists.StockViewList;
import models.persistence.StockDatabase;
import views.*;
import views.gui.BasketView;
import views.gui.ReceiptView;
import views.gui.StockView;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class UserKioskViewController {

	StockViewList userStockView;
	ShoppingBasket basket;
	StockDatabase stockDB;

	UserKioskView view;

	ReceiptView rView;

	public UserKioskViewController(UserKioskView view) {

		this.view = view;
		view.setLocationRelativeTo(null);

		basket = new ShoppingBasket();
		userStockView = new StockViewList();
		try {
			stockDB = new StockDatabase(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		view.setStockDisplayList(userStockView.getStock());
		view.addButtonListener(new DisplayButtonListener());
		view.addQuantityListener(new DisplayQuantityListener());
		view.addAdminButtonListener(new AdminButtonListener());
		view.addCheckoutCardButtonListener(new CardCheckoutListener());
		view.addCheckoutCashButtonListener(new CashCheckoutListener());
	}
	
	/*
	 * Listeners for MVC pattern
	 */

	class DisplayQuantityListener implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			JSpinner spinner = (JSpinner) e.getSource();
			StockView display = view.getStockDisplayFromSpinner(spinner);

			if (display != null) {

				int quantity = (int) spinner.getValue();
				float total = quantity * display.getStock().getPrice();

				if (quantity == 0) {
					display.getButton().setText("Remove");
				} else {
					display.getButton().setText("Add Basket");
				}

				display.getStock().setQuantity(quantity);
				display.setQuantity((int) spinner.getValue());
				display.setTotal(total);
			}

		}

	}

	class DisplayButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equalsIgnoreCase("passStock")) {

				JButton pressedButton = (JButton) e.getSource();
				StockView display = view.getStockDisplayFromButton(pressedButton);

				if (display != null) {


					if ((int) display.getQuantity().getValue() == 0) {
						for (BasketView bView : basket.getBasket()) {
							if (bView.getStock().getCode() == display.getStock().getCode()) {
								int index = basket.getBasket().indexOf(bView);
								basket.getBasket().remove(index);
								break;
							}
						}
					} else {

						Stock stock = display.getStock();
						stock.setQuantity((int) display.getQuantity().getValue());

						BasketView basketView = new BasketView(stock);

						boolean isEdited = false;
						for (BasketView bView : basket.getBasket()) {
							if (bView.getStock().getCode() == display.getStock().getCode()) {
								int index = basket.getBasket().indexOf(bView);
								basket.getBasket().set(index, basketView);
								isEdited = true;
								break;
							}
						}

						if (!isEdited) {
							basket.getBasket().add(basketView);
						}

					}

					view.setBasketDisplayList(basket.getBasket());
					view.getTotalLabel().setText("Total Price: £" + String.format("%.2f", basket.getTotal()));

				}
			}

		}
	}

	class CardCheckoutListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (basket.getBasket().isEmpty()) {
				return;
			}

			CardEntryView cView = new CardEntryView();
			rView = new ReceiptView(0, false, basket);
			CardController controller = new CardController(cView, rView);

			cView.setVisible(true);
			cView.setLocationRelativeTo(null);


			Thread thread = new Thread() {
				@Override
				public void run() {
					while (!rView.hasBeenCreated()) {
						try {
							Thread.sleep(100);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

					if (rView.hasBeenCreated()) {

						stockDB.updateStockFromBasket(basket);
						basket.getBasket().clear();

						view.setBasketDisplayList(basket.getBasket());
						view.getTotalLabel().setText("Total Price: ï¿½" + String.format("%.2f", basket.getTotal()));

						view.restart(rView);
					}
				}
			};
			thread.start();
		}
	}

	class CashCheckoutListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (basket.getBasket().isEmpty()) {
				return;
			}

			SpinnerNumberModel sModel = new SpinnerNumberModel(basket.getTotal(), basket.getTotal(), 1000, 0.01f);
			JSpinner spinner = new JSpinner(sModel);

			int optionChosen = JOptionPane.showOptionDialog(null, spinner, "Enter cash given: ",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

			if (optionChosen == JOptionPane.OK_OPTION) {
				System.out.println((float) ((double) spinner.getValue()));
				float cashGiven = (float) ((double) spinner.getValue());

				rView = new ReceiptView(cashGiven, true, basket);
				rView.initialise();

				JFrame frame = new JFrame();
				frame.setPreferredSize(new Dimension(350, 600));
				frame.add(rView);
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);

				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						if (rView.hasBeenCreated()) {
							stockDB.updateStockFromBasket(basket);
							basket.getBasket().clear();
							view.setBasketDisplayList(basket.getBasket());
							view.getTotalLabel().setText("Total Price: ï¿½" + String.format("%.2f", basket.getTotal()));

							view.restart(rView);
						}

						try {
							Thread.sleep(100);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});


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
			staffView.setLocationRelativeTo(null);

		}
	}

}
