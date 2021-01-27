package controllers;

import models.Card;
import utilities.CardValidator;
import views.CardEntryView;
import views.gui.ReceiptView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CardController {

	CardEntryView view;
	ReceiptView rView;

	public CardController(CardEntryView view, ReceiptView rView) {
		this.view = view;
		this.rView = rView;

		view.addButtonListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String number = view.getTextCardNumberField().getText();
				String pin = view.getTextPinField().getText();

				Card card = new Card(number, pin);

				if (CardValidator.validateCardType(card)) {
					rView.initialise();
					view.displayMessage("Thank you for paying with: " + card.getCardType());
					view.dispose();
				} else {
					view.displayMessage("Invalid card, please try again.");
				}
			}

		});


	}


}
