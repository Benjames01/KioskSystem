package views;

import java.awt.Dimension;

import javax.swing.JPanel;

import models.Stock;
import javax.swing.JLabel;
import javax.swing.JSeparator;

public class BasketView extends JPanel {


	static final long serialVersionUID = -9187630875786913470L;
	
	JLabel lblName;
	JLabel lblQuantity;
	JLabel lblTotal;
	
	Stock stock;
	private JSeparator separator;
	
	public BasketView(Stock stock) {
		this.stock = stock;
		this.setPreferredSize(new Dimension(400,40));
		setLayout(null);
		
		lblName = new JLabel("Name: " + stock.getName());
		lblName.setBounds(10, 11, 162, 14);
		add(lblName);
		
		lblQuantity = new JLabel("£" + String.format("%.2f",stock.getPrice()) + " * " + stock.getQuantity());
		lblQuantity.setBounds(234, 11, 73, 14);
		add(lblQuantity);
		
		lblTotal = new JLabel("= £" +  String.format("%.2f", (stock.getPrice() * stock.getQuantity())));
		lblTotal.setBounds(317, 11, 73, 14);
		add(lblTotal);
		
		separator = new JSeparator();
		separator.setBounds(0, 36, 400, 2);
		add(separator);
	}

	public JLabel getLblName() {
		return lblName;
	}

	public JLabel getLblQuantity() {
		return lblQuantity;
	}

	public JLabel getLblTotal() {
		return lblTotal;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}
	
	
	
	
	

}
