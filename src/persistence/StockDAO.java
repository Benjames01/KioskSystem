package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Stock;
import views.StockView;

public class StockDAO extends DAO {

	boolean hasData = false;
	boolean useDB = false;

	public StockDAO() throws SQLException, ClassNotFoundException  {
		super();		
		initialise();	
	}

	void initialise() throws SQLException {

		if(!hasData) {
			hasData = true;

			Statement sql = getConnection().createStatement();
			ResultSet results = sql.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='stock'");

			
			if(!results.next()) {
				// Create the stock table
				System.out.println("Building table with data");
				Statement sql2 = getConnection().createStatement();
				sql2.execute("CREATE TABLE IF NOT EXISTS stock(code integer PRIMARY KEY,"
						+ "name varchar(50),"
						+ "price float,"
						+ "quantity integer);");

				System.out.println("table created.");

				// Populate the table with data
				PreparedStatement preparedStatement = getConnection().prepareStatement("INSERT INTO stock(name, price, quantity) values(?,?,?);");

				System.out.println("Populating stock table.");
				preparedStatement.setString(1, "beans");
				preparedStatement.setFloat(2, 2.00f);
				preparedStatement.setInt(3, 15);
				preparedStatement.executeUpdate();

				preparedStatement.setString(1, "Bacon");
				preparedStatement.setFloat(2, 3.50f);
				preparedStatement.setInt(3, 3);
				preparedStatement.executeUpdate();

				preparedStatement.setString(1, "salt");
				preparedStatement.setFloat(2, 0.80f);
				preparedStatement.setInt(3, 10);		
				preparedStatement.executeUpdate();

				preparedStatement.setString(1, "ham sandwich");
				preparedStatement.setFloat(2, 2.60f);
				preparedStatement.setInt(3, 1);		
				preparedStatement.executeUpdate();

				preparedStatement.setString(1, "eggs");
				preparedStatement.setFloat(2, 1.12f);
				preparedStatement.setInt(3, 5);		
				preparedStatement.executeUpdate();

				preparedStatement.setString(1, "chicken");
				preparedStatement.setFloat(2, 3.80f);
				preparedStatement.setInt(3, 5);		
				preparedStatement.executeUpdate();

				preparedStatement.close();

				sql2.close();
				System.out.println("Stock table has been populated with data.");	
				
			} else {
				System.out.println("Table already exists");
			}
			
			sql.close();
			sql = getConnection().createStatement();
			results = sql.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='orders'");
			

			if(!results.next()) {
				// Create the stock table
				System.out.println("Building order table");
								
				Statement sql2 = getConnection().createStatement();
				sql2.executeUpdate("CREATE TABLE IF NOT EXISTS orders(id integer PRIMARY KEY,"
						+ "code integer,"
						+ "name varchar(50),"
						+ "quantity integer);");
				sql2.close();

				System.out.println("order table created.");
			} else {
				System.out.println("Order Table exists");
			}
			
			sql.close();

		}

	}

	public void addStock(Stock stock) {

		PreparedStatement addSQL = null;
		try {
			addSQL = getConnection().prepareStatement("Replace INTO stock(code, name, price, quantity) values(?,?,?,?);");
			addSQL.setInt(1, stock.getCode());
			addSQL.setString(2, stock.getName());
			addSQL.setFloat(3, stock.getPrice());
			addSQL.setInt(4, stock.getQuantity());
			addSQL.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(addSQL != null) {
				try {
					addSQL.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public void removeStock(Stock stock) {
		PreparedStatement removeSQL = null;

		try {
			removeSQL = getConnection().prepareStatement("DELETE FROM stock WHERE code=?;");

			removeSQL.setInt(1, stock.getCode());
			removeSQL.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(removeSQL != null) {
					removeSQL.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}


	}


	public ArrayList<Stock> getAllStock() {
		ArrayList<Stock> allStock = new ArrayList<Stock>();

		Statement sql = null;

		try {
			sql = getConnection().createStatement();
			ResultSet results = sql.executeQuery("SELECT code, name, price, quantity FROM stock WHERE 1=1;");

			while(results.next()) {

				Stock stock = new Stock(results.getInt("code"), results.getString("name"),
						results.getFloat("price"), results.getInt("quantity"));

				allStock.add(stock);
			}

			results.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(sql != null) {
				try {					
					sql.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}		

		return allStock;
	}
	
	
	public ArrayList<StockView> getStockDisplayContainers(ArrayList<Stock> stockList) {
		ArrayList<StockView> stockDisplayList = new ArrayList<>();
		
		for(Stock stock : stockList) {
			StockView temp = new StockView(stock);
			
			stockDisplayList.add(temp);
		}
		
		return stockDisplayList;
	}


	public ArrayList<Stock> getEmptyStock() throws SQLException{
		ArrayList<Stock> emptyStock = new ArrayList<Stock>();

		Statement sql = getConnection().createStatement();
		ResultSet results = sql.executeQuery("SELECT code, name, price, quantity FROM stock WHERE quantity=0;");


		while(results.next()) {

			Stock stock = new Stock(results.getInt("code"), results.getString("name"),
					results.getFloat("price"), results.getInt("quantity"));

			emptyStock.add(stock);
		}

		sql.close();

		return emptyStock;
	}


	public Stock getStockByCode(int code ) {
		return new Stock();
	}

}
