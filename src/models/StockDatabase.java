package models;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class StockDatabase extends AbstractTableModel {
	
	static final long serialVersionUID = 3883657244070052671L;
	String[] columnNames = {"code", "name", "price", "quantity"}; 
	
	ArrayList<Stock> stockDatabase = new ArrayList<Stock>();
	
	public StockDatabase() {
		
	}
	
	public StockDatabase(ArrayList<Stock> stockDatabase) {
		this.stockDatabase = stockDatabase;
	}
	
	
	public float getTotal() {
		
		float total = 0f;
		
		for(Stock stock : stockDatabase) {
			total += stock.getPrice();
		}
		
		return total;
	}
	
	public ArrayList<Stock> getShoppingBasket(){
		return stockDatabase;
	}
	
	public void addStock(Stock stock) {
		Stock oldStock = getStockFromCode(stock.getCode());
		if( oldStock != null) {
			editStock(oldStock, stock);
		} else {
			stockDatabase.add(stock);
		}		
	}
	
	public void removeStock(Stock stock) {
		stockDatabase.remove(stock);
	}

	public void editStock(Stock stock, Stock newStock) {
		int index = stockDatabase.indexOf(stock);
		stockDatabase.set(index, newStock);
	}
	
	
	public Stock getStockFromCode(int code) {	
		for(Stock stock: stockDatabase) {
			if(stock.code == code) {
				return stock;
			}
		}
		return null;
	}
	
	public Stock getStockAt(int index) {	
		return stockDatabase.get(index);
	}
	
	public ArrayList<Stock> getEmptyStock(){
		
		ArrayList<Stock> emptyStock = new ArrayList<Stock>();
		
		for(Stock stock : stockDatabase) {
			if(stock.quantity == 0) {
				emptyStock.add(stock);
			}
		}
		
		return emptyStock;
	}

	@Override
	public Class<?> getColumnClass(int col) {
		switch (col){
		case 1:
			return String.class;
		case 2:
			return float.class;
		default:
			return int.class;
		}
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public int getRowCount() {
		int rows;
		
		if (stockDatabase == null) {
			return 0;
		} else {
			rows = stockDatabase.size();
		}
		
		return rows;
	}

	@Override
	public Object getValueAt(int row, int col) {
		Object obj = null;
		switch (col) {
		case 0: 
			obj = stockDatabase.get(row).getCode();
			break;
		case 1:
			obj = stockDatabase.get(row).getName();
			break;
		case 2:
			obj = stockDatabase.get(row).getPrice();
			break;
		case 3:
			obj = stockDatabase.get(row).getQuantity();
			break;
		}
		
		return obj;
	}
	
}
