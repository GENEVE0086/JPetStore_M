package org.csu.geneve.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.csu.geneve.domain.Item;
import org.csu.geneve.domain.Product;
import org.csu.geneve.persistence.ItemDao;

public class ItemDaoImpl implements ItemDao {

  /*
  constants
  SQL Language
  */
  private static final String  GET_ITEM_LIST =
          "SELECT I.ITEMID,LISTPRICE,UNITCOST,SUPPLIER AS supplierId,I.PRODUCTID AS productId,NAME AS productName,DESCN AS productDescription,CATEGORY AS categoryId,STATUS,ATTR1 AS attribute1,ATTR2 AS attribute2,ATTR3 AS attribute3,ATTR4 AS attribute4,ATTR5 AS attribute5 FROM ITEM I, PRODUCT P WHERE P.PRODUCTID = I.PRODUCTID AND I.PRODUCTID = ?";

  private static final String GET_ITEM =
          "select I.ITEMID,LISTPRICE,UNITCOST,SUPPLIER AS supplierId,I.PRODUCTID AS productId,NAME AS productName,DESCN AS productDescription,CATEGORY AS CategoryId,STATUS,ATTR1 AS attribute1,ATTR2 AS attribute2,ATTR3 AS attribute3,ATTR4 AS attribute4,ATTR5 AS attribute5,QTY AS quantity from ITEM I, INVENTORY V, PRODUCT P where P.PRODUCTID = I.PRODUCTID and I.ITEMID = V.ITEMID and I.ITEMID=?";

  private static final String GET_INVENTORY_QUANTITY =
          "SELECT QTY AS QUANTITY FROM INVENTORY WHERE ITEMID = ?";

  private static final String UPDATE_INVENTORY_QUANTITY =
          "UPDATE INVENTORY SET QTY = QTY - ? WHERE ITEMID = ?";

  @Override
  public void updateInventoryQuantity(Map<String, Object> param) {
    try {
      /* connect to mysql and get data */
      Connection connection = DataBaseUtil.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_INVENTORY_QUANTITY);

      /* get info and prepare to update */
      Iterator<String> itr = param.keySet().iterator();
      String itemId = itr.next();
      String incr = itr.next();
      Integer increment = new Integer(param.get(incr).toString());

      /* update */
      preparedStatement.setInt(1, increment);
      preparedStatement.setString(2, itemId);
      preparedStatement.executeUpdate();

      /* disconnect to database */
      DataBaseUtil.closePreparedStatement(preparedStatement);
      DataBaseUtil.closeConnection(connection);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public int getInventoryQuantity(String itemId) {
    /* state result object and make it be -1 */
    int inventoryQuantity = -1;
    try {
      /* connect to mysql and get data */
      Connection connection = DataBaseUtil.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(GET_INVENTORY_QUANTITY);
      preparedStatement.setString(1, itemId);
      ResultSet resultSet = preparedStatement.executeQuery();

      /* progress data */
      if (resultSet.next()) {
        inventoryQuantity = resultSet.getInt(1);
      }

      /* disconnect to database */
      DataBaseUtil.closeResultSet(resultSet);
      DataBaseUtil.closePreparedStatement(preparedStatement);
      DataBaseUtil.closeConnection(connection);
    } catch (Exception e) {
      e.printStackTrace();
    }
    /* return the result object */
    return inventoryQuantity;
  }

  @Override
  public List<Item> getItemList(String productId) {
    //state result object
    List<Item> itemList = new ArrayList<Item>();
    try {
      //connect to mysql and get data
      Connection connection = DataBaseUtil.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(GET_ITEM_LIST);
      preparedStatement.setString(1, productId);
      ResultSet resultSet = preparedStatement.executeQuery();

      //progress data
      while (resultSet.next()) {
        Item item = new Item();
        setItem(item, resultSet);
        itemList.add(item);
      }

      //disconnect to database
      DataBaseUtil.closeResultSet(resultSet);
      DataBaseUtil.closePreparedStatement(preparedStatement);
      DataBaseUtil.closeConnection(connection);
    } catch (Exception e) {
      e.printStackTrace();
    }
    //return the result object
    return itemList;
  }

  @Override
  public Item getItem(String itemId) {
    //state result object
    Item item = null;
    try {
      //connect to mysql and get data
      Connection connection = DataBaseUtil.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(GET_ITEM);
      preparedStatement.setString(1,itemId);
      ResultSet resultSet = preparedStatement.executeQuery();

      //progress data
      if (resultSet.next()) {
        item = new Item();
        setItem(item, resultSet);
      }

      //disconnect to database
      DataBaseUtil.closeResultSet(resultSet);
      DataBaseUtil.closePreparedStatement(preparedStatement);
      DataBaseUtil.closeConnection(connection);
    } catch (Exception e) {
      e.printStackTrace();
    }
    //return the result object
    return item;
  }

  //turn a set of data from database into a Item
  private void setItem(Item item, ResultSet resultSet) throws SQLException {
    //sorry, there are too many data,and I'm sure it'ok, so no debug!!
    item.setItemId(resultSet.getString(1));
    item.setListPrice(resultSet.getBigDecimal(2));
    item.setUnitCost(resultSet.getBigDecimal(3));
    item.setSupplierId(resultSet.getInt(4));
    Product product = new Product();
    product.setProductId(resultSet.getString(5));
    product.setName(resultSet.getString(6));
    product.setDescription(resultSet.getString(7));
    product.setCategoryId(resultSet.getString(8));
    item.setProduct(product);
    item.setStatus(resultSet.getString(9));
    item.setAttribute1(resultSet.getString(10));
    item.setAttribute2(resultSet.getString(11));
    item.setAttribute3(resultSet.getString(12));
    item.setAttribute4(resultSet.getString(13));
    item.setAttribute5(resultSet.getString(14));
  }
}
