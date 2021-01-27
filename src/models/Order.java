package models;

public class Order extends Stock{ 

	int id;

	public Order(int id, int code, String name, float price, int quantity) {
		super(code, name, price, quantity);
		this.id = id;
	}
	
	public Order(int code, String name, float price, int quantity) {
		super(code, name, price, quantity);
	}


	public int getID() {
		return id;
	}


	public void setID(int id) {
		this.id = id;
	}
}
