package org.csu.geneve.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.csu.geneve.domain.Item;
import org.csu.geneve.domain.LineItem;
import org.csu.geneve.domain.Order;
import org.csu.geneve.domain.Sequence;
import org.csu.geneve.persistence.ItemDao;
import org.csu.geneve.persistence.LineItemDao;
import org.csu.geneve.persistence.OrderDao;
import org.csu.geneve.persistence.SequenceDao;
import org.csu.geneve.persistence.impl.ItemDaoImpl;
import org.csu.geneve.persistence.impl.LineItemDaoImpl;
import org.csu.geneve.persistence.impl.OrderDaoImpl;
import org.csu.geneve.persistence.impl.SequenceDaoImpl;

public class OrderService {
  private ItemDao itemDao;
  private OrderDao orderDao;
  private SequenceDao sequenceDao;
  private LineItemDao lineItemDao;

  public OrderService() {
    itemDao = new ItemDaoImpl();
    lineItemDao = new LineItemDaoImpl();
    sequenceDao = new SequenceDaoImpl();
    orderDao = new OrderDaoImpl();
  }

  public void insertOrder(Order order) {
    order.setOrderId(getNextId("ordernum"));
    for (int i = 0; i < order.getLineItems().size(); i++) {
      LineItem lineItem = (LineItem) order.getLineItems().get(i);
      String itemId = lineItem.getItemId();
      Integer increment = lineItem.getQuantity();
      Map<String, Object> param = new HashMap<String, Object>(2);
      param.put("itemId", itemId);
      param.put("increment", increment);
      itemDao.updateInventoryQuantity(param);
    }

    orderDao.insertOrder(order);
    orderDao.insertOrderStatus(order);
    for (int i = 0; i < order.getLineItems().size(); i++) {
      LineItem lineItem = (LineItem) order.getLineItems().get(i);
      lineItem.setOrderId(order.getOrderId());
      lineItemDao.insertLineItem(lineItem);
    }
  }


  public Order getOrder(int orderId) {
    Order order = orderDao.getOrder(orderId);
    order.setLineItems(lineItemDao.getLineItemsByOrderId(orderId));

    for (int i = 0; i < order.getLineItems().size(); i++) {
      LineItem lineItem = (LineItem) order.getLineItems().get(i);
      Item item = itemDao.getItem(lineItem.getItemId());
      item.setQuantity(itemDao.getInventoryQuantity(lineItem.getItemId()));
      lineItem.setItem(item);
    }

    return order;
  }

  public List<Order> getOrdersByUsername(String username) {
    return orderDao.getOrdersByUsername(username);
  }

  public int getNextId(String name) {
    Sequence sequence = new Sequence(name, -1);
    sequence = (Sequence) sequenceDao.getSequence(sequence);
    if (sequence == null) {
      throw new RuntimeException(
              "Error: A null sequence was returned from the database (could not get next "
              + name + " sequence).");
    }
    Sequence parameterObject = new Sequence(name, sequence.getNextId() + 1);
    if (sequenceDao.updateSequence(parameterObject)) {
      return parameterObject.getNextId();
    } else {
      throw new RuntimeException("Can't updateSequence!");
    }

  }
}
