package org.csu.geneve.persistence;

import org.csu.geneve.domain.Order;

import java.util.List;

public interface HistoryDao {
  public List<Order> getOrdersByUsername(String username);
}
