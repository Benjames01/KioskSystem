package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Order;
import models.Stock;

public class OrderDAO extends DAO {

	boolean hasData = false;

	public OrderDAO() throws SQLException, ClassNotFoundException {
		super();

	}

	public void addOrder(Order order) {
		PreparedStatement addSQL = null;
		System.out.println("Creating order..");
		try {

			addSQL = getConnection()
					.prepareStatement("INSERT INTO orders(code, name, quantity) values(?,?,?);",
							addSQL.RETURN_GENERATED_KEYS);

			addSQL.setInt(1, order.getCode());
			addSQL.setString(2, order.getName());
			addSQL.setInt(3, order.getQuantity());

			int rows = addSQL.executeUpdate();

			if(rows == 0 ) {
				System.out.println("Failed to insert order into database");
			}

			try (ResultSet generatedID = addSQL.getGeneratedKeys()){
				if(generatedID.next()) {
					order.setID(generatedID.getInt(1));
					System.out.println("Order created with id: " + order.getID());
				} else {
					System.out.println("Failed to create order.");
				}
			} 	
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

	public void removeOrder(int id) {
		PreparedStatement removeSQL = null;

		try {
			removeSQL = getConnection().prepareStatement("DELETE FROM orders WHERE id=?;");

			removeSQL.setInt(1, id);
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

	public ArrayList<Order> getAllOrders() {
		ArrayList<Order> allOrders= new ArrayList<Order>();

		Statement sql = null;

		try {
			getConnection().close();
			sql = getConnection().createStatement();
			ResultSet results = sql.executeQuery("SELECT id, code, name, quantity FROM orders WHERE 1=1;");

			while(results.next()) {

				Order order = new Order(results.getInt("id"), results.getInt("code"), results.getString("name"),
						0, results.getInt("quantity"));

				allOrders.add(order);
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

		return allOrders;
	}
}


