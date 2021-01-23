package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

import models.Stock;

import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class StockDatabaseView extends JFrame implements ActionListener {

	private static final long serialVersionUID = -8894773959386302085L;
	
	JTable stockList;
	JTextField txtCode;
	JTextField txtName;
	JTextField txtPrice;
	JTextField txtQuantity;
	
	JButton btnSaveButton;
	
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
    	
        stockList = new JTable();        
        stockList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
        
        this.pack();
    }
    
    public void actionPerformed(ActionEvent e) {
    }
    
    public JTable getStockList() {
    	return stockList;
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
    	txtCode.setText(Integer.toString(quantity));
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
    
    public void addStockListener(ActionListener listener) {
    	btnSaveButton.addActionListener(listener);
    }
    
    public void displayMessage(String msg) {
    	JOptionPane.showMessageDialog(this, msg);
    }
    
    public Stock getStock() {
    	editStock = new Stock(getCode(), getName(), getPrice(), getQuantity());	
    	return editStock;
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
 
      
}
