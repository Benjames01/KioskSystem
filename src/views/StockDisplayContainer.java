package views;

import javax.swing.JPanel;

import models.Stock;
import javax.swing.JLabel;
import javax.swing.JSlider;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.DefaultFormatter;
import javax.swing.JSeparator;

public class StockDisplayContainer extends JPanel {

	static final long serialVersionUID = 4732565248494723792L;

	JLabel lblName; 
	JLabel lblCode;

	JLabel lblPrice;
	JLabel lblNewLabel;

	JButton btnAddButton;

	JLabel lblTotal;
	JLabel lblQuantityDisplay;

	JSpinner sliderQuantity;

	Stock stock;

	public StockDisplayContainer(Stock stock) {		
		this.setPreferredSize(new Dimension(200,100));
		setLayout(null);

		lblName = new JLabel("Name: ");
		lblName.setBounds(10, 26, 181, 14);
		add(lblName);

		lblCode = new JLabel("Code: ");
		lblCode.setBounds(10, 11, 195, 14);
		add(lblCode);

		lblPrice = new JLabel("Price: ");
		lblPrice.setBounds(10, 42, 181, 14);
		add(lblPrice);

		lblNewLabel = new JLabel("Quantity: ");
		lblNewLabel.setBounds(10, 63, 66, 14);
		add(lblNewLabel);

		btnAddButton = new JButton("Add Basket");
		btnAddButton.setBounds(267, 59, 123, 23);

		btnAddButton.setActionCommand("passStock");

		add(btnAddButton);

		lblTotal = new JLabel("Total: ");
		lblTotal.setBounds(267, 42, 123, 14);
		add(lblTotal);

		lblQuantityDisplay = new JLabel("Quantity:");
		lblQuantityDisplay.setBounds(267, 26, 123, 14);
		add(lblQuantityDisplay);	

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 93, 400, 7);
		add(separator);

		setFromStock(stock);	
		JComponent comp = sliderQuantity.getEditor();
		JFormattedTextField field = (JFormattedTextField) comp.getComponent(0);
		DefaultFormatter formatter = (DefaultFormatter) field.getFormatter();
		formatter.setCommitsOnValidEdit(true);

	}


	public void setName(String name) {
		lblName.setText("Name: " + name);
	}

	public void setCode(int code) {
		lblCode.setText("Code: " + code);
	}

	public void setPrice(float price) {
		lblPrice.setText("Price: " + price);
	}

	public void setSliderMax(int max) {

		if (max == 0) {
			sliderQuantity = new JSpinner();
			sliderQuantity.setEnabled(false);
			String temp = lblCode.getText();
			temp += " (OUT OF STOCK)";
			lblCode.setText(temp);
			btnAddButton.setEnabled(false);
		} else {
			SpinnerModel sm = new SpinnerNumberModel(0, 0, max, 1);

			sliderQuantity = new JSpinner(sm);
			sliderQuantity.setBounds(75, 60, 170, 20);

			add(sliderQuantity);
		}



	}

	public void setTotal(float total) {
		lblTotal.setText("Total: " + total);
	}

	public void setQuantity(int quantity) {
		lblQuantityDisplay.setText("Quantity: " + quantity);
	}

	public void setFromStock(Stock stock) {
		this.stock = stock;
		setName(stock.getName());
		setCode(stock.getCode());
		setPrice(stock.getPrice());
		setSliderMax(stock.getQuantity());
		setTotal(0);
		setQuantity(0);
	}

	public Stock getStock() {
		return stock;
	}

	public JSpinner getQuantity() {
		return sliderQuantity;
	}

	public JButton getButton() {
		return btnAddButton;
	}

}
