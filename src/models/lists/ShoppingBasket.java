package models.lists;

import java.util.ArrayList;

import views.gui.BasketView;

public class ShoppingBasket {

	ArrayList<BasketView> basket;


	public ShoppingBasket() {
		basket = new ArrayList<BasketView>();
	}

	public ShoppingBasket(ShoppingBasket basket) {
		this.basket = basket.getBasket();
	}


	
	/*
	 * Getters
	 */
	public ArrayList<BasketView> getBasket() {
		return basket;
	}

	public float getTotal() {
		float total = 0.00f;

		for (BasketView bView : basket) {
			total += bView.getStock().getQuantity() * bView.getStock().getPrice();
		}

		return total;
	}

}
