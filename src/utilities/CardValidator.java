package utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import models.Card;

public class CardValidator {

	static final String regex = "^(?:(?<visa>4[0-9]{12}(?:[0-9]{3})?)|" +
			"(?<mastercard>5[1-5][0-9]{14}))$";
	static Pattern pattern = Pattern.compile(regex);

	private CardValidator() {
	}

	static public boolean validateCardType(Card card) {
		String cardNumber = card.getCardNumber();

		Matcher matcher = pattern.matcher(cardNumber);

		if (matcher.matches()) {
			if (matcher.group("visa") != null) {
				card.setCardType("Visa");
			} else if (matcher.group("mastercard") != null) {
				card.setCardType("Mastercard");
			}
			return true;

		}
		return false;
	}

}
