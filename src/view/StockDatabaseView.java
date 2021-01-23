package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

import models.StockDatabase;

import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class StockDatabaseView extends JFrame implements ActionListener {

	private static final long serialVersionUID = -8894773959386302085L;

	StockDatabase stockDatabase;
	
	JTable stockList;
	private JTextField txtCode;
	private JTextField txtName;
	private JTextField textField;
	private JTextField textField_1;
	
    public StockDatabaseView(){
    	super("Stock Database");
    	     
    	this.setResizable(false);
        
       this.setPreferredSize(new Dimension(500,500));
 
        JPanel content = new JPanel();
 
        this.setContentPane(content);
        content.setLayout(new GridLayout(2, 1, 0, 0));
        
        stockList = new JTable();        
        stockList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JScrollPane scrollPane = new JScrollPane(stockList);
        scrollPane.setPreferredSize(new Dimension(500, 300));
        content.add(scrollPane);
        stockList.setFillsViewportHeight(true);
        
        JPanel panel = new JPanel();
        content.add(panel);
        panel.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Code: ");
        lblNewLabel.setBounds(10, 11, 32, 14);
        panel.add(lblNewLabel);
        
        txtCode = new JTextField();
        txtCode.setBounds(40, 8, 48, 20);
        panel.add(txtCode);
        txtCode.setColumns(10);
        
        JLabel lblNewLabel_1 = new JLabel("Name: ");
        lblNewLabel_1.setBounds(91, 11, 39, 14);
        panel.add(lblNewLabel_1);
        
        txtName = new JTextField();
        txtName.setBounds(126, 8, 144, 20);
        panel.add(txtName);
        txtName.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("Price: ");
        lblNewLabel_2.setBounds(280, 11, 32, 14);
        panel.add(lblNewLabel_2);
        
        textField = new JTextField();
        textField.setBounds(322, 8, 48, 20);
        panel.add(textField);
        textField.setColumns(10);
        
        JLabel lblNewLabel_3 = new JLabel("Quantity: ");
        lblNewLabel_3.setBounds(385, 11, 60, 14);
        panel.add(lblNewLabel_3);
        
        textField_1 = new JTextField();
        textField_1.setBounds(445, 8, 39, 20);
        panel.add(textField_1);
        textField_1.setColumns(10);
        
        this.pack();
 
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
    }
    
    public void actionPerformed(ActionEvent e) {
    }
    
    public void addDBListener(PropertyChangeListener log) {
    	stockList.addPropertyChangeListener(log);
    }
    
    public JTable getStockList() {
    	return stockList;
    }
}
