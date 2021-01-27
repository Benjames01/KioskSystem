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

import models.ShoppingBasket;
import models.Stock;
import models.StockDatabase;
import models.StockViewList;
import persistence.StockDAO;
import views.BasketView;
import views.OrderView;
import views.StaffLoginView;
import views.StockView;
import views.UserKioskView;

public class UserKioskViewController {

	StockViewList userStockView;
	ShoppingBasket basket;

	UserKioskView view;

	public UserKioskViewController(UserKioskView view) {

		this.view = view;

		basket = new ShoppingBasket();
		userStockView = new StockViewList();

		view.setStockDisplayList(userStockView.getStock());
		view.addButtonListener(new DisplayButtonListener());
		view.addQuantityListener(new DisplayQuantityListener());
		view.addAdminButtonListener(new AdminButtonListener());
	}

	class DisplayQuantityListener implements ChangeListener{

		@Override
		public void stateChanged(ChangeEvent e) {
			JSpinner spinner = (JSpinner) e.getSource();
			StockView display = view.getStockDisplayFromSpinner(spinner);			

			if (display != null) {

				int quantity = (int) spinner.getValue();
				float total = (float) quantity * display.getStock().getPrice();

				if(quantity == 0) {
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


	class DisplayButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equalsIgnoreCase("passStock")) {

				JButton pressedButton = (JButton) e.getSource();
				StockView display = view.getStockDisplayFromButton(pressedButton);

				if (display != null) {


					if((int)display.getQuantity().getValue() == 0) {
						for(BasketView bView : basket.getBasket()) {
							if (bView.getStock().getCode() == display.getStock().getCode()) {
								int index = basket.getBasket().indexOf(bView);
								basket.getBasket().remove(index);
								break;
							}
						}
					} else {

						Stock stock = display.getStock();
						stock.setQuantity((int)display.getQuantity().getValue());

						BasketView basketView = new BasketView(stock);

						boolean isEdited = false;
						for(BasketView bView : basket.getBasket()) {
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
