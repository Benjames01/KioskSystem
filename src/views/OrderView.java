package views;

import javax.swing.JPanel;

import models.Order;
import models.Stock;
import javax.swing.JLabel;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JSeparator;

public class OrderView extends JPanel {

	private static final long serialVersionUID = -8734411140364803452L;

	JLabel lblStockName;
	JLabel lblQuantity;
	JLabel lblOrderID;

	JButton btnAcceptOrder;

	Order order;


	public OrderView(Order order) {

		this.setPreferredSize(new Dimension(400,45));
		this.order = order;

		setLayout(null);

		lblStockName = new JLabel("Stock Name: " + order.getName());
		lblStockName.setBounds(10, 27, 186, 14);
		add(lblStockName);

		lblQuantity = new JLabel("Quantity: " + order.getQuantity());
		lblQuantity.setBounds(201, 27, 86, 14);
		add(lblQuantity);

		btnAcceptOrder = new JButton("Accept Order");
		btnAcceptOrder.setBounds(293, 7, 147, 34);
		add(btnAcceptOrder);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 43, 450, 2);
		add(separator);	

		lblOrderID = new JLabel("Order ID: " + order.getID());
		lblOrderID.setBounds(10, 7, 114, 14);
		add(lblOrderID);
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

	public Order getOrder() {
		return order;
	}




}
