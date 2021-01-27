package views;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

import models.Order;
import models.Stock;

import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.PopupMenuListener;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class StockDatabaseView extends JFrame implements ActionListener {

	private static final long serialVersionUID = -8894773959386302085L;

	
	ArrayList<OrderView> orderList;
	
	JTable stockList;

	JPopupMenu tableControls;
	JMenuItem editItem;
	JMenuItem deleteItem;
	JMenuItem orderItem;

	JTextField txtCode;
	JTextField txtName;
	JTextField txtPrice;
	JTextField txtQuantity;

	JButton btnSaveButton;
	JButton btnSwitchToKiosk;
	JButton btnNewButton;

	JScrollPane scrollOrderPane;
	JPanel orderPanel;
	
	Stock editStock = new Stock();

	public StockDatabaseView(){
		super("Stock Database");   	    

		setupLayout();
		setupContent();

		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
	}

	public void setupLayout() {    	
		this.setResizable(false);
		this.setPreferredSize(new Dimension(500,500));    
	}

	public void setupContent() {
		JPanel content = new JPanel();

		this.setContentPane(content);
		content.setLayout(new GridLayout(2, 1, 0, 0));

		stockList = new JTable();        
		stockList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrollPane = new JScrollPane(stockList);
		scrollPane.setPreferredSize(new Dimension(400, 450));
		content.add(scrollPane);
		stockList.setFillsViewportHeight(true);

		JPanel editPanel = new JPanel();
		content.add(editPanel);
		editPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Code: ");
		lblNewLabel.setBounds(10, 11, 39, 14);
		editPanel.add(lblNewLabel);

		txtCode = new JTextField();
		txtCode.setBounds(51, 8, 48, 20);
		editPanel.add(txtCode);
		txtCode.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Name: ");
		lblNewLabel_1.setBounds(107, 11, 39, 14);
		editPanel.add(lblNewLabel_1);

		txtName = new JTextField();
		txtName.setBounds(160, 8, 118, 20);
		editPanel.add(txtName);
		txtName.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Price: ");
		lblNewLabel_2.setBounds(288, 11, 39, 14);
		editPanel.add(lblNewLabel_2);

		txtPrice = new JTextField();
		txtPrice.setBounds(330, 8, 48, 20);
		editPanel.add(txtPrice);
		txtPrice.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Quantity: ");
		lblNewLabel_3.setBounds(388, 11, 60, 14);
		editPanel.add(lblNewLabel_3);

		txtQuantity = new JTextField();
		txtQuantity.setBounds(445, 8, 39, 20);
		editPanel.add(txtQuantity);
		txtQuantity.setColumns(10);

		btnSaveButton = new JButton("Save");
		btnSaveButton.setBounds(395, 36, 89, 23);
		editPanel.add(btnSaveButton);

		btnNewButton = new JButton("New");
		btnNewButton.setBounds(298, 36, 89, 23);
		editPanel.add(btnNewButton);

		JLabel lblNewLabel_4 = new JLabel("Right click item to edit/delete/order.");
		lblNewLabel_4.setBounds(10, 40, 180, 14);
		editPanel.add(lblNewLabel_4);

		orderPanel = new JPanel();
		orderPanel.setBounds(0, 81, 494, 154);
		editPanel.add(orderPanel);
		orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.Y_AXIS));


		JPanel panel = new JPanel();
		orderPanel.add(panel, BorderLayout.SOUTH);
		orderPanel.setPreferredSize(new Dimension(400, 720));
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnNewButton_1 = new JButton("Accept Order");
		panel.add(btnNewButton_1);

		btnSwitchToKiosk = new JButton("Switch to Kiosk View");
		panel.add(btnSwitchToKiosk);

		tableControls = new JPopupMenu();
		
		editItem = new JMenuItem("Edit");
		deleteItem = new JMenuItem("Delete");
		orderItem = new JMenuItem("Order");

		tableControls.add(editItem);
		tableControls.add(deleteItem);
		tableControls.add(orderItem);
		stockList.setComponentPopupMenu(tableControls);


		this.pack();
	}

	public void actionPerformed(ActionEvent e) {
	}

	public JTable getStockList() {
		return stockList;
	}
	
	public void setOrderList(ArrayList<OrderView> orderList) {	
		this.orderList = orderList;
		
	    JPanel orderContainer = new JPanel();
	    orderContainer.setLayout(new GridLayout(100, 1, 0, 10));
	    	
		for (OrderView display : orderList) {	
			orderContainer.add(display);
			display.setVisible(true);
		}
	
		scrollOrderPane = new JScrollPane(orderContainer);
	    orderPanel.add(scrollOrderPane);
	   
		
		this.pack();
	}
	
	public OrderView getOrderViewFromButton(JButton button) {
		for(OrderView container : orderList) {
			if(container.getButton() == button) {
				return container;
			}
		}
		
		return null;
	}
	
	
	public void setStockCode(int code) {
		txtCode.setText(Integer.toString(code));
	}

	public void setStockName(String name) {
		txtName.setText(name);
	}

	public void setStockPrice(float price) {
		txtPrice.setText(Float.toString(price));
	}

	public void setStockQuantity(int quantity) {
		txtQuantity.setText(Integer.toString(quantity));
	}

	public int getCode() {
		return parseInt(txtCode.getText());
	}

	public String getName() {
		return txtName.getText();
	}

	public float getPrice() {
		return parseFloat(txtPrice.getText());
	}

	public int getQuantity() {
		return parseInt(txtQuantity.getText());
	}

	public Stock getStock() {
		editStock = new Stock(getCode(), getName(), getPrice(), getQuantity());	
		return editStock;
	}

	public void displayStock(Stock stock) {
		setStockCode(stock.getCode());
		setStockName(stock.getName());
		setStockPrice(stock.getPrice());
		setStockQuantity(stock.getQuantity());
	}

	public int getSelectedStockIndex() {
		return stockList.convertRowIndexToModel(stockList.getSelectedRow());
	}

	public JPopupMenu getPopup() {
		return tableControls;
	}

	int parseInt(String text) {
		int parsedInt = 0;
		try {
			parsedInt = Integer.parseInt(text);
		} catch (Exception e){
			displayMessage(e.getMessage());
		}
		return parsedInt;
	}

	float parseFloat(String text) {
		float parsedFloat = 0;
		try {
			parsedFloat = Float.parseFloat(text);
		} catch (Exception e){
			displayMessage(e.getMessage());
		}
		return parsedFloat;
	}

	public void addStockListener(ActionListener listener) {
		btnSaveButton.addActionListener(listener);
	}

	public void addNewBtnListener(ActionListener listener) {
		btnNewButton.addActionListener(listener);
	}

	public void addEditListener(ActionListener listener) {
		editItem.addActionListener(listener);
	}

	public void addDeleteListener(ActionListener listener) {
		deleteItem.addActionListener(listener);
	}
	
	public void addOrderListener(ActionListener listener) {
		orderItem.addActionListener(listener);
	}

	public void addSwitchToKioskListener(ActionListener listener) {
		btnSwitchToKiosk.addActionListener(listener);
	}
	public void addPopupMenuListener(PopupMenuListener listener) {
		tableControls.addPopupMenuListener(listener);
	}

	public void displayMessage(String msg) {
		JOptionPane.showMessageDialog(this, msg);
	}

	public int getConfirmation(String msg) {	
		return JOptionPane.showConfirmDialog(this, msg);
	}
}
