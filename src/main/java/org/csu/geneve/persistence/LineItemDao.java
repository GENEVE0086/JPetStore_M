package org.csu.geneve.persistence;

import java.util.List;

import org.csu.geneve.domain.LineItem;

public interface LineItemDao {

  List<LineItem> getLineItemsByOrderId(int orderId);

  void insertLineItem(LineItem lineItem);
}
