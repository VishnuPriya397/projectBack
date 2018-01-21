package com.dao;

import java.util.List;

import com.model.Order;

public interface OrderDAO {

public boolean saveOrUpdate(Order order);
	
	public Order getOrderById(int orderId);
	
	public boolean deleteOrderById(int orderId);
	
	public List<Order> getAllOrdersOfUser(int userId);
	

}
