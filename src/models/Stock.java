package models;

public class Stock {

	int code = 1;
	int quantity = 1;

	String name = "";
	float price = 0.0f;


	public Stock() {

	}

	public Stock(int code, String name, float price, int quantity) {
		this.code = code;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	public Stock(int code, String name, float price) {
		this.code = code;
		this.name = name;
		this.price = price;
	}
	

	/*
	 * Getters and setters
	 */

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "code:[" + code + "] " + name;

	}


}
