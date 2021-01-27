package models;

public class Card {


	String cardNumber;
	String cardType = "null";
	String pin;

	public Card(String cardNumber, String pin) {
		cardNumber.replaceAll("-", "");
		this.cardNumber = cardNumber;
		this.pin = pin;

	}

	/*
	 * Getters and setters
	 */
	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

}
