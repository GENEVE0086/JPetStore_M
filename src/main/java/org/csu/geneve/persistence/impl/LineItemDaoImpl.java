package org.csu.geneve.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.csu.geneve.domain.LineItem;
import org.csu.geneve.persistence.LineItemDao;

public class LineItemDaoImpl implements LineItemDao {
  /*
  constants
  SQL Language
  */
  private static final String GET_LINE_ITEMS_BY_ORDER_ID =
          "SELECT ORDERID, LINENUM AS lineNumber, ITEMID, QUANTITY, "
                  + "UNITPRICE FROM LINEITEM WHERE ORDERID = ?";
  private static final String INSERT_ITEM_LINE =
          "INSERT INTO LINEITEM (ORDERID, LINENUM, ITEMID, QUANTITY, "
                  + "UNITPRICE) VALUES (?, ?, ?, ?, ?)";


  @Override
  public List<LineItem> getLineItemsByOrderId(int orderId) {
    /* state result object */
    List<LineItem> lineItemList = new ArrayList<LineItem>();

    try {
      /* connect to mysql and get data */
      Connection connection = DataBaseUtil.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(GET_LINE_ITEMS_BY_ORDER_ID);
      preparedStatement.setString(1,orderId + "");
      ResultSet resultSet = preparedStatement.executeQuery();

      /* progress data */
      while (resultSet.next()) {
        LineItem lineItem = new LineItem();
        lineItem.setOrderId(resultSet.getInt(1));
        lineItem.setLineNumber(resultSet.getInt(2));
        lineItem.setItemId(resultSet.getString(3));
        lineItem.setQuantity(resultSet.getInt(4));
        lineItem.setUnitPrice(resultSet.getBigDecimal(5));
        lineItemList.add(lineItem);
      }
      /* disconnect to database */
      DataBaseUtil.closeResultSet(resultSet);
      DataBaseUtil.closePreparedStatement(preparedStatement);
      DataBaseUtil.closeConnection(connection);
    } catch (Exception e) {
      e.printStackTrace();
    }
    /* return the result object */
    return lineItemList;
  }

  @Override
  public void insertLineItem(LineItem lineItem) {
    try {
      /* connect to mysql and get data */
      Connection connection = DataBaseUtil.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ITEM_LINE);

      /* config the update info */
      preparedStatement.setString(1,lineItem.getOrderId() + "");
      preparedStatement.setString(2,lineItem.getLineNumber() + "");
      preparedStatement.setString(3,lineItem.getItemId());
      preparedStatement.setString(4,lineItem.getQuantity() + "");
      preparedStatement.setString(5,lineItem.getUnitPrice().toString());

      /* update */
      preparedStatement.executeUpdate();

      /* disconnect to database */
      DataBaseUtil.closePreparedStatement(preparedStatement);
      DataBaseUtil.closeConnection(connection);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
