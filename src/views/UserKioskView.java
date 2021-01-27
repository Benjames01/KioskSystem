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
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;

import models.StockViewList;

import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;

public class UserKioskView extends JFrame implements ActionListener{


	static final long serialVersionUID = 5251742558110660647L;

	StockViewList stockList;
	ArrayList<BasketView> shoppingBasketList;


	JScrollPane basketScrollPane;
	JPanel basketDisplayPanel;

	JScrollPane shopScrollPane;
	JPanel shopPanel;

	JPanel leftPanel;

	JButton btnAdminLogin;
	JLabel lblTotalPrice;

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

		basketPanel.setLayout(new BoxLayout(basketPanel, BoxLayout.Y_AXIS));
		basketPanel.setPreferredSize(new Dimension(600, 720));

		basketDisplayPanel = new JPanel();
		basketDisplayPanel.setPreferredSize(new Dimension(400, 720));
		basketPanel.add(basketDisplayPanel, BorderLayout.NORTH);

		JPanel basketInformationPanel = new JPanel();
		basketPanel.add(basketInformationPanel, BorderLayout.SOUTH);
		basketInformationPanel.setPreferredSize(new Dimension(400,50));
		basketInformationPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		lblTotalPrice = new JLabel("Total Price: ");
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

	
	public JLabel getTotalLabel()
	{
		return lblTotalPrice;
	}
	public void setBasketDisplayList(ArrayList<BasketView> shoppingBasketList) {	
		this.shoppingBasketList = shoppingBasketList;


		if(shopScrollPane != null) {
			basketDisplayPanel.remove(shopScrollPane);
		}

		JPanel basketContainer = new JPanel();
		basketContainer.setLayout(new GridLayout(100, 1, 0, 10));


		for (BasketView display : shoppingBasketList) {	
			basketContainer.add(display);
		}

		shopScrollPane = new JScrollPane(basketContainer);
		shopScrollPane.setPreferredSize(new Dimension(640, 720));
		basketDisplayPanel.add(shopScrollPane);

		this.pack();

	}



	public void setStockDisplayList(ArrayList<StockView> stockDisplayList) {	
		stockList.setStock(stockDisplayList);

		JPanel shopContainer = new JPanel();
		shopContainer.setLayout(new GridLayout(100, 1, 0, 10));

		for (StockView display : stockDisplayList) {	
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
		for(StockView container: stockList.getStock()) {

			if(container.getQuantity() != null) {
				container.getQuantity().addChangeListener(listener);
			} else {
				System.out.println("Didn't add listener (out of stock)");
			}

		}	
	}

	public void addButtonListener(ActionListener listener) {
		for(StockView container: stockList.getStock()) {
			container.getButton().addActionListener(listener);
		}
	}

	public void addAdminButtonListener(ActionListener listener) {
		btnAdminLogin.addActionListener(listener);
	}

	public StockView getStockDisplayFromButton(JButton button) {
		for(StockView container : stockList.getStock()) {
			if(container.getButton() == button) {
				return container;
			}
		}

		return null;
	}

	public StockView getStockDisplayFromSpinner(JSpinner spinner) {
		for(StockView container : stockList.getStock()) {
			if(container.getQuantity() == spinner) {
				return container;
			}
		}

		return null;
	}


}
