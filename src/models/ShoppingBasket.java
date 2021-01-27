package models;

import java.util.ArrayList;

import views.BasketView;

public class ShoppingBasket {

	ArrayList<BasketView> basket;


	public ShoppingBasket() {
		basket = new ArrayList<BasketView>();
	}

	public ArrayList<BasketView> getBasket(){
		return basket;
	}

	public float getTotal() {
		float total = 0.00f;

		for(BasketView bView : basket) {
			total += bView.getStock().getQuantity() * bView.getStock().getPrice();
		}

		return total;
	}


}
