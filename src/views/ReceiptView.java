package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;


import models.Receipt;
import models.ShoppingBasket;

public class ReceiptView extends JPanel {
	
	private static final long serialVersionUID = -962147709332386892L;
	
	boolean receiptCreated = false;
	boolean isVisible = false;
	
	float cashGiven;
	boolean useCashGiven;
	ShoppingBasket basket;

	public ReceiptView(float cashGiven, boolean useCashGiven, ShoppingBasket basket) {
		
		this.cashGiven = cashGiven;
		this.useCashGiven = useCashGiven;
		this.basket = basket;	
	}
	
	public void initialise() {
		
		JTextPane lbl = new JTextPane();
		
		this.setPreferredSize(new Dimension(350,600));
		lbl.setPreferredSize(new Dimension(350,600));
		lbl.setMargin(new Insets(20,20,20,20));
		this.setLayout(new BorderLayout());		
		
		
		new Thread(new Runnable() {
			@Override
			public void run() {

				final Receipt receipt = new Receipt(basket, cashGiven, useCashGiven);
				
			
				final Control control = new Control();
				
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {		
														
							if(!control.flag) {
								lbl.setText(receipt.getReceiptString());
								lbl.setEditable(false);

								getPanel().add(lbl);								

								lbl.setVisible(true);
								getPanel().setVisible(true);

								control.flag = true;
								receiptCreated = true;
							}
						}
					});
				

			}
		}).start();
	}

	public boolean hasBeenCreated() {
		return receiptCreated;
	}
	
	JPanel getPanel() {
		return this;
	}
	
	class Control {
		public volatile boolean flag = false;
	}

}
