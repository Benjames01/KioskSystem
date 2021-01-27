package models.persistence;

import persistence.StockDAO;
import views.gui.BasketView;

import javax.swing.table.AbstractTableModel;

import models.Stock;
import models.lists.ShoppingBasket;

import java.sql.SQLException;
import java.util.ArrayList;

public class StockDatabase extends AbstractTableModel {

	static final long serialVersionUID = 3883657244070052671L;
	String[] columnNames = {"code", "name", "price", "quantity"};

	StockDAO dao;

	ArrayList<Stock> stockDatabase;

	boolean useDB;


	public StockDatabase(boolean useDB) throws SQLException {
		this.useDB = useDB;
		if (useDB) {
			try {
				dao = new StockDAO();
				stockDatabase = dao.getAllStock();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
	}

	
	/*
	 * Used to interact with the database for CRUD operations
	 */
	
	public void updateStockFromBasket(ShoppingBasket basket) {
		for (BasketView bView : basket.getBasket()) {
			Stock stock = getStockFromCode(bView.getStock().getCode());
			stock.setQuantity(stock.getQuantity() - bView.getStock().getQuantity());
			addStock(stock);
		}
	}


	public void addStock(Stock stock) {
		Stock oldStock = getStockFromCode(stock.getCode());
		if (oldStock != null) {
			editStock(oldStock, stock);
		} else {
			stockDatabase.add(stock);
		}

		if (useDB) {
			dao.addStock(stock);
		}

	}

	void editStock(Stock stock, Stock newStock) {
		int index = stockDatabase.indexOf(stock);
		stockDatabase.set(index, newStock);

		if (useDB) {
			dao.addStock(stock);
		}
	}


	public Stock getStockFromCode(int code) {
		for (Stock stock : stockDatabase) {
			if (stock.getCode() == code) {
				return stock;
			}
		}
		return null;
	}

	public Stock getStockAt(int index) {
		return stockDatabase.get(index);
	}

	public void removeStockAt(int index) {
		dao.removeStock(stockDatabase.get(index));
		stockDatabase.remove(index);

	}

	public ArrayList<Stock> getEmptyStock() {

		ArrayList<Stock> emptyStock = new ArrayList<>();

		for (Stock stock : stockDatabase) {
			if (stock.getQuantity() == 0) {
				emptyStock.add(stock);
			}
		}

		return emptyStock;
	}
	
	
	/*
	 * Used to display stock the the JTable view
	 */

	@Override
	public Class<?> getColumnClass(int col) {
		switch (col) {
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
