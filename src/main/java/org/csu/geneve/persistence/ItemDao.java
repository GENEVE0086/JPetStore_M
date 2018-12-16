package org.csu.geneve.persistence;

import java.util.List;
import java.util.Map;

import org.csu.geneve.domain.Item;

public interface ItemDao {

  void updateInventoryQuantity(Map<String, Object> param);

  int getInventoryQuantity(String itemId);

  List<Item> getItemList(String productId);

  Item getItem(String itemId);
}
