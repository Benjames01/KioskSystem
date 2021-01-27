package models;

import java.text.SimpleDateFormat;
import java.util.Date;

import models.lists.ShoppingBasket;
import views.gui.BasketView;

public class Receipt {

	ShoppingBasket basket;
	boolean cashPayment;
	float cashGiven;

	public Receipt(ShoppingBasket basket) {
		this.basket = basket;
		this.cashPayment = false;
	}

	public Receipt(ShoppingBasket basket, float cashGiven) {
		this.basket = basket;
		this.cashPayment = true;
		this.cashGiven = cashGiven;
	}

	public Receipt(ShoppingBasket basket, float cashGiven, boolean useCashGiven) {
		this.basket = basket;

		if (useCashGiven) {
			this.cashPayment = useCashGiven;
			this.cashGiven = cashGiven;
		}

	}


	public float getChange(float cashGiven) {
		return cashGiven - basket.getTotal();
	}

	public String getReceiptString() {

		System.out.println("RS: " + basket.getBasket().size());

		String LINESEPARATOR = System.getProperty("line.separator");
		String LINEDECORATOR = "====================================";
		StringBuilder str = new StringBuilder();

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();

		str.append("Company Name");
		str.append(LINESEPARATOR);
		str.append(formatter.format(date));
		str.append(LINESEPARATOR);
		str.append(LINEDECORATOR);
		str.append(LINESEPARATOR);


		for (BasketView bView : basket.getBasket()) {

			int quantity = bView.getStock().getQuantity();
			float price = bView.getStock().getPrice();
			float total = price * quantity;

			str.append(bView.getStock().getName());
			if (bView.getStock().getName().length() < 10) {
				str.append("\t\t");
			} else {
				str.append("\t");
			}


			str.append(quantity);
			str.append(" * ");
			str.append(String.format("%.2f", price));
			str.append("\t");
			str.append(String.format("%.2f", total));
			str.append(LINESEPARATOR);
		}

		str.append(LINEDECORATOR);
		str.append(LINESEPARATOR);
		str.append("Total price: \t\t\t");
		str.append(String.format("%.2f", basket.getTotal()));
		str.append(LINESEPARATOR);
		str.append(LINEDECORATOR);

		str.append(LINESEPARATOR);
		str.append("Payment Type: ");

		if (cashPayment) {
			str.append("CASH");
			str.append(LINESEPARATOR);
			str.append("Cash Given: \t\t\t");
			str.append(String.format("%.2f", cashGiven));
			str.append(LINESEPARATOR);
			str.append("Change Given: \t\t");
			str.append(String.format("%.2f", getChange(cashGiven)));
		} else {
			str.append("CARD");
		}


		return str.toString();

	}

}
