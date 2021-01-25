package persistence;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JFileChooser;

public class DAO {
	
	protected static Connection connection;
	
	
	public DAO() throws SQLException, ClassNotFoundException {
		if(connection == null) {
			
			String databasePath = new JFileChooser().getFileSystemView().getDefaultDirectory().toString();
			
			databasePath =  databasePath + File.separatorChar + "KioskSystem";
								
			try {
				Files.createDirectories(Paths.get(databasePath));
				
				System.out.println(databasePath);
				
				String databaseURL = "jdbc:sqlite:" + databasePath + File.separatorChar + "database.db";
				
				System.out.println(databaseURL);
				
				String databaseDriver = "org.sqlite.JDBC";
				
				Class.forName(databaseDriver);
				connection = DriverManager.getConnection(databaseURL);

			} catch (IOException e1) {
				e1.printStackTrace();
			}			
		}
	}
	

}
