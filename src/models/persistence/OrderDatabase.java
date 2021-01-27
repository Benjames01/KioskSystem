package models.persistence;

import persistence.OrderDAO;
import views.gui.OrderView;

import java.sql.SQLException;
import java.util.ArrayList;

import models.Order;

public class OrderDatabase {

	OrderDAO dao;

	public OrderDatabase() {
		try {
			dao = new OrderDAO();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Order> getOrders() {
		return dao.getAllOrders();
	}

	public void removeOrder(int id) {
		dao.removeOrder(id);
	}

	public void addOrder(Order order) {
		dao.addOrder(order);
	}

	public ArrayList<OrderView> getOrderViews(ArrayList<Order> orderList) {
		ArrayList<OrderView> orderDisplayList = new ArrayList<>();

		for (Order order : orderList) {
			OrderView temp = new OrderView(order);

			orderDisplayList.add(temp);
		}

		return orderDisplayList;
	}


}
