package views;

import javax.swing.JPanel;

import models.Stock;
import javax.swing.JLabel;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JSeparator;

public class OrderView extends JPanel {

	private static final long serialVersionUID = -8734411140364803452L;

	JLabel lblStockName;
	JLabel lblQuantity;

	JButton btnAcceptOrder;

	Stock stock;

	public OrderView(Stock stock) {
		this.setPreferredSize(new Dimension(400,45));

		this.stock = stock;
		setLayout(null);

		lblStockName = new JLabel("Stock Name: " + stock.getName());
		lblStockName.setBounds(10, 11, 187, 14);
		add(lblStockName);

		lblQuantity = new JLabel("Quantity: " + stock.getQuantity());
		lblQuantity.setBounds(10, 27, 175, 14);
		add(lblQuantity);

		btnAcceptOrder = new JButton("Accept Order");
		btnAcceptOrder.setBounds(293, 7, 147, 34);
		add(btnAcceptOrder);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 43, 450, 2);
		add(separator);	
	}

	public JLabel getLblStockName() {
		return lblStockName;
	}

	public JLabel getLblQuantity() {
		return lblQuantity;
	}

	public JButton getButton() {
		return btnAcceptOrder;
	}




}
