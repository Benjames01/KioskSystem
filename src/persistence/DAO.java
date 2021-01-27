package persistence;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {

	protected static Connection connection;

	static String databasePath;
	static String databaseURL;
	static String databaseDriver = "org.sqlite.JDBC";

	public DAO() throws SQLException, ClassNotFoundException {
		setupConnection();
	}

	private static void setupConnection() {
		if (connection == null) {
			databasePath = new JFileChooser().getFileSystemView().getDefaultDirectory().toString();

			databasePath = databasePath + File.separatorChar + "KioskSystem";

			try {
				Files.createDirectories(Paths.get(databasePath));

				System.out.println(databasePath);

				databaseURL = "jdbc:sqlite:" + databasePath + File.separatorChar + "database.db";

				System.out.println(databaseURL);

				Class.forName(databaseDriver);
				connection = DriverManager.getConnection(databaseURL);

			} catch (IOException | ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public Connection getConnection() {
		try {
			if (connection != null && !connection.isClosed()) {
				return connection;
			} else {
				return connection = DriverManager.getConnection(databaseURL);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return connection;
	}

}
