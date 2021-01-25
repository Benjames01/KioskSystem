package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import models.Stock;

public class StockDAO extends DAO {
	
	boolean hasData = false;
	
	public StockDAO(boolean useDB) {
		super();
		
		if (useDB) {
			try {
				initialise();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
		
	void initialise() throws SQLException {
		
		if(!hasData) {
			hasData = true;
			
			Statement sql = connection.createStatement();
			ResultSet results = sql.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='stock'");
			
			if(!results.next()) {
				// Create the stock table
				
				Statement sql2 = connection.createStatement();
				sql2.execute("CREATE TABLE stock(code integer,"
						+ "name varchar(50),"
						+ "price float,"
						+ "quantity integer");
				
				// Populate the table with data
				PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO stock values(?,?,?);");
				
				preparedStatement.setString(2, "beans");
				preparedStatement.setFloat(3, 2.00f);
				preparedStatement.setInt(4, 15);		
				preparedStatement.execute();
				
				preparedStatement.setString(2, "Bacon");
				preparedStatement.setFloat(3, 3.50f);
				preparedStatement.setInt(4, 3);
				preparedStatement.execute();
				
				preparedStatement.setString(2, "salt");
				preparedStatement.setFloat(3, 0.80f);
				preparedStatement.setInt(4, 10);		
				preparedStatement.execute();
				
				preparedStatement.setString(2, "ham sandwich");
				preparedStatement.setFloat(3, 2.60f);
				preparedStatement.setInt(4, 1);		
				preparedStatement.execute();
				
				preparedStatement.setString(2, "eggs");
				preparedStatement.setFloat(3, 1.12f);
				preparedStatement.setInt(4, 5);		
				preparedStatement.execute();
				
				preparedStatement.setString(2, "chicken");
				preparedStatement.setFloat(3, 3.80f);
				preparedStatement.setInt(4, 5);		
				preparedStatement.execute();
										
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
	
	public Stock getStockByCode(int code ) {
		return new Stock();
	}

}
