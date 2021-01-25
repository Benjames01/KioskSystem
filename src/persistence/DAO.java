package persistence;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAO {
	
	protected static Connection connection;
	
	
	public DAO() {
		if(connection == null) {
			String databaseURL = "jdbc:sqlite:database.db";
			String databaseDriver = "org.sqlite.JDBC";
			
			try {
				Class.forName(databaseDriver);
				connection = DriverManager.getConnection(databaseURL);
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			
		}
	}
	

}
