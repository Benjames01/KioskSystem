package views;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import models.Stock;
import models.StockDatabase;

import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;

public class UserKioskView extends JFrame implements ActionListener{
	
	
	static final long serialVersionUID = 5251742558110660647L;
	
	ArrayList<StockDisplayContainer> stockDisplayList;
	
	JScrollPane shopScrollPane;
	JPanel shopPanel;
	
	 JPanel leftPanel;
	 
	 JButton btnAdminLogin;

	public UserKioskView() {
		super("Kiosk - User View");
		
		Dimension prefSize = new Dimension(1080,720);
		this.setSize(prefSize);
		this.setPreferredSize(prefSize);
		
		JPanel content = new JPanel();
	       	     
	    this.setContentPane(content);
	    content.setLayout(new BoxLayout(content, BoxLayout.X_AXIS));
	    
	    leftPanel = new JPanel();
	    content.add(leftPanel);
	    leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
	    leftPanel.setPreferredSize(new Dimension(400, 720));
	    	    
	    
	    JPanel rightPanel = new JPanel();
	    content.add(rightPanel);
	    rightPanel.setLayout(new BorderLayout(0, 0));
	    
	    JPanel basketPanel = new JPanel();
	    rightPanel.add(basketPanel, BorderLayout.CENTER);
	    basketPanel.setLayout(new BorderLayout(0, 0));
	    
	    JPanel basketDisplayPanel = new JPanel();
	    basketPanel.add(basketDisplayPanel, BorderLayout.NORTH);
	    
	    JPanel basketInformationPanel = new JPanel();
	    basketPanel.add(basketInformationPanel, BorderLayout.SOUTH);
	    basketInformationPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	    
	    JLabel lblTotalPrice = new JLabel("Total Price: ");
	    basketInformationPanel.add(lblTotalPrice);
	    
	    JPanel controlsPanel = new JPanel();
	    rightPanel.add(controlsPanel, BorderLayout.NORTH);
	    controlsPanel.setLayout(new BorderLayout(0, 0));
	    
	    btnAdminLogin = new JButton("Admin Login");
	    controlsPanel.add(btnAdminLogin, BorderLayout.EAST);
	    
	    JSeparator separator = new JSeparator();
	    controlsPanel.add(separator, BorderLayout.SOUTH);
	    
	    JPanel panel = new JPanel();
	    rightPanel.add(panel, BorderLayout.SOUTH);
	    
	    JButton btnCheckoutCash = new JButton("Checkout Cash");
	    panel.add(btnCheckoutCash);
	    
	    JButton btnCheckoutCard = new JButton("Checkout Card");
	    panel.add(btnCheckoutCard);
	    
	    JButton btnClearBasket = new JButton("Clear Basket");
	    panel.add(btnClearBasket);
	    
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
	}
	
	public void setStockDisplayList(ArrayList<StockDisplayContainer> stockDisplayList) {	
	    this.stockDisplayList = stockDisplayList;
	    
	    JPanel shopContainer = new JPanel();
	    shopContainer.setLayout(new GridLayout(100, 1, 0, 10));
	    	
		for (StockDisplayContainer display : stockDisplayList) {	
			shopContainer.add(display);
		}
	
	    shopScrollPane = new JScrollPane(shopContainer);
	    leftPanel.add(shopScrollPane);
		
		this.pack();
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
	public void addQuantityListener(ChangeListener listener) {
		for(StockDisplayContainer container: stockDisplayList) {
			
			if(container.getQuantity() != null) {
				container.getQuantity().addChangeListener(listener);
			} else {
				System.out.println("Didn't add listener (out of stock)");
			}
			
		}	
	}
	
	public void addButtonListener(ActionListener listener) {
		for(StockDisplayContainer container: stockDisplayList) {
			container.getButton().addActionListener(listener);
		}
	}
	
	public void addAdminButtonListener(ActionListener listener) {
		btnAdminLogin.addActionListener(listener);
	}
	
	public StockDisplayContainer getStockDisplayFromButton(JButton button) {
		for(StockDisplayContainer container : stockDisplayList) {
			if(container.getButton() == button) {
				return container;
			}
		}
		
		return null;
	}
	
	public StockDisplayContainer getStockDisplayFromSpinner(JSpinner spinner) {
		for(StockDisplayContainer container : stockDisplayList) {
			if(container.getQuantity() == spinner) {
				return container;
			}
		}
		
		return null;
	}
	

}
