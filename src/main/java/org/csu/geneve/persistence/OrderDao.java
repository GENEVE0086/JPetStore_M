package org.csu.geneve.persistence;

import java.util.List;

import org.csu.geneve.domain.Order;

public interface OrderDao {

  List<Order> getOrdersByUsername(String username);

  Order getOrder(int orderId);

  void insertOrder(Order order);

  void insertOrderStatus(Order order);
}
