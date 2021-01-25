package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Stock;

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
					
			Statement sql = connection.createStatement();
			ResultSet results = sql.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='stock'");
			
			if(!results.next()) {
				// Create the stock table
				System.out.println("Building table with data");
				Statement sql2 = connection.createStatement();
				sql2.execute("CREATE TABLE IF NOT EXISTS stock(code integer,"
						+ "name varchar(50),"
						+ "price float,"
						+ "quantity integer);");
				
				System.out.println("table created.");
				
				// Populate the table with data
				PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO stock(name, price, quantity) values(?,?,?);");
				
				System.out.println("Populating stock table.");
				preparedStatement.setString(1, "beans");
				preparedStatement.setFloat(2, 2.00f);
				preparedStatement.setInt(3, 15);
				preparedStatement.execute();
				
				preparedStatement.setString(1, "Bacon");
				preparedStatement.setFloat(2, 3.50f);
				preparedStatement.setInt(3, 3);
				preparedStatement.execute();
				
				preparedStatement.setString(1, "salt");
				preparedStatement.setFloat(2, 0.80f);
				preparedStatement.setInt(3, 10);		
				preparedStatement.execute();
				
				preparedStatement.setString(1, "ham sandwich");
				preparedStatement.setFloat(2, 2.60f);
				preparedStatement.setInt(3, 1);		
				preparedStatement.execute();
				
				preparedStatement.setString(1, "eggs");
				preparedStatement.setFloat(2, 1.12f);
				preparedStatement.setInt(3, 5);		
				preparedStatement.execute();
				
				preparedStatement.setString(1, "chicken");
				preparedStatement.setFloat(2, 3.80f);
				preparedStatement.setInt(3, 5);		
				preparedStatement.execute();
				
				System.out.println("Stock table has been populated with data.");
										
			} else {
				System.out.println("Table already exists");
			}
			
		}
		
	}
	
	public void addStock(Stock stock) throws SQLException {
		PreparedStatement addSQL = connection.prepareStatement("Replace INTO stock(code, name, price, quantity) values(?,?,?,?);");
		
		addSQL.setInt(1, stock.getCode());
		addSQL.setString(2, stock.getName());
		addSQL.setFloat(3, stock.getPrice());
		addSQL.setInt(4, stock.getQuantity());
		
		addSQL.execute();
	}
	
	public void removeStock(Stock stock) throws SQLException {
		PreparedStatement addSQL = connection.prepareStatement("DELETE FROM stock WHERE code=?;");
		
		addSQL.setInt(1, stock.getCode());
		addSQL.execute();
	}
	
	
	public ArrayList<Stock> getAllStock() throws SQLException{
		ArrayList<Stock> allStock = new ArrayList<Stock>();
		
		Statement sql = connection.createStatement();
		ResultSet results = sql.executeQuery("SELECT code, name, price, quantity FROM stock WHERE 1=1;");
		
		
		while(results.next()) {
			
			Stock stock = new Stock(results.getInt("code"), results.getString("name"),
					results.getFloat("price"), results.getInt("quantity"));
			
			allStock.add(stock);
		}
		
		return allStock;
	}
	
	
	public ArrayList<Stock> getEmptyStock() throws SQLException{
		ArrayList<Stock> emptyStock = new ArrayList<Stock>();
		
		Statement sql = connection.createStatement();
		ResultSet results = sql.executeQuery("SELECT code, name, price, quantity FROM stock WHERE quantity=0;");
		
		
		while(results.next()) {
			
			Stock stock = new Stock(results.getInt("code"), results.getString("name"),
					results.getFloat("price"), results.getInt("quantity"));
			
			emptyStock.add(stock);
		}
		
		return emptyStock;
	}
	
	
	public Stock getStockByCode(int code ) {
		return new Stock();
	}

}
