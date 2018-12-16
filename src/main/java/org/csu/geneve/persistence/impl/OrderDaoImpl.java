package org.csu.geneve.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.csu.geneve.domain.Order;
import org.csu.geneve.persistence.OrderDao;

public class OrderDaoImpl implements OrderDao {
  /*
  constants
  SQL Language
  */
  private static final String GET_ORDER =
          "select BILLADDR1 AS billAddress1, BILLADDR2 AS billAddress2, BILLCITY, "
                  + "BILLCOUNTRY, BILLSTATE, BILLTOFIRSTNAME, BILLTOLASTNAME, BILLZIP, "
                  + "SHIPADDR1 AS shipAddress1, SHIPADDR2 AS shipAddress2, SHIPCITY,"
                  + "SHIPCOUNTRY, SHIPSTATE, SHIPTOFIRSTNAME, SHIPTOLASTNAME, SHIPZIP, "
                  + "CARDTYPE, COURIER, CREDITCARD, EXPRDATE AS expiryDate, LOCALE, ORDERDATE, "
                  + "ORDERS.ORDERID, TOTALPRICE, USERID AS username, STATUS FROM ORDERS, "
                  + "ORDERSTATUS WHERE ORDERS.ORDERID = ? AND ORDERS.ORDERID = ORDERSTATUS.ORDERID";

  private static final String GET_ORDER_BY_USERNAME =
          "SELECT BILLADDR1 AS billAddress1, BILLADDR2 AS billAddress2, BILLCITY, "
                  + "BILLCOUNTRY, BILLSTATE, BILLTOFIRSTNAME, BILLTOLASTNAME, BILLZIP,"
                  + "SHIPADDR1 AS shipAddress1, SHIPADDR2 AS shipAddress2, SHIPCITY, "
                  + "SHIPCOUNTRY, SHIPSTATE, SHIPTOFIRSTNAME, SHIPTOLASTNAME, SHIPZIP, "
                  + "CARDTYPE, COURIER, CREDITCARD, EXPRDATE AS expiryDate,LOCALE,ORDERDATE, "
                  + "ORDERS.ORDERID, TOTALPRICE, USERID AS username,STATUS FROM ORDERS, "
                  + "ORDERSTATUS WHERE ORDERS.USERID = ? AND "
                  + "ORDERS.ORDERID = ORDERSTATUS.ORDERID ORDER BY ORDERDATE";

  private static final String INSERT_ORDER =
          "INSERT INTO ORDERS "
                  + "(ORDERID, USERID, ORDERDATE, SHIPADDR1, SHIPADDR2, SHIPCITY, SHIPSTATE, "
                  + "SHIPZIP, SHIPCOUNTRY,BILLADDR1, BILLADDR2, BILLCITY, BILLSTATE, BILLZIP, "
                  + "BILLCOUNTRY, COURIER, TOTALPRICE, BILLTOFIRSTNAME, BILLTOLASTNAME, "
                  + "SHIPTOFIRSTNAME, SHIPTOLASTNAME, CREDITCARD, EXPRDATE, CARDTYPE, LOCALE) "
                  + "VALUES"
                  + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

  private static final String INSERT_ORDER_STATUS =
          "INSERT INTO ORDERSTATUS (ORDERID, LINENUM, TIMESTAMP, STATUS) VALUES (?, ?, ?, ?)";

  @Override
  public List<Order> getOrdersByUsername(String username) {
    /* state result object */
    List<Order> orderList = new ArrayList<Order>();

    try {
      /* connect to mysql and get data */
      Connection connection = DataBaseUtil.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER_BY_USERNAME);
      preparedStatement.setString(1,username);
      ResultSet resultSet = preparedStatement.executeQuery();

      /* progress data */
      while (resultSet.next()) {
        Order order = new Order();
        setOrder(order, resultSet);
        orderList.add(order);
      }
      /* disconnect to database */
      DataBaseUtil.closeResultSet(resultSet);
      DataBaseUtil.closePreparedStatement(preparedStatement);
      DataBaseUtil.closeConnection(connection);

    } catch (Exception e) {
      e.printStackTrace();
    }
    return orderList;
  }

  @Override
  public Order getOrder(int orderId) {
    /* state result object */
    Order order = null;

    try {
      /* connect to mysql and get data */
      Connection connection = DataBaseUtil.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER);
      preparedStatement.setString(1,orderId + "");
      ResultSet resultSet = preparedStatement.executeQuery();

      /* progress data */
      if (resultSet.next()) {
        order = new Order();
        setOrder(order, resultSet);
      }

      /* disconnect to database */
      DataBaseUtil.closeResultSet(resultSet);
      DataBaseUtil.closePreparedStatement(preparedStatement);
      DataBaseUtil.closeConnection(connection);
    } catch (Exception e) {
      e.printStackTrace();
    }

    /* return the result object */
    return order;
  }

  private void setOrder(Order order, ResultSet resultSet) throws SQLException {
    order.setBillAddress1(resultSet.getString(1));
    order.setBillAddress2(resultSet.getString(2));
    order.setBillCity(resultSet.getString(3));
    order.setBillCountry(resultSet.getString(4));
    order.setBillState(resultSet.getString(5));
    order.setBillToFirstName(resultSet.getString(6));
    order.setBillToLastName(resultSet.getString(7));
    order.setBillZip(resultSet.getString(8));
    order.setShipAddress1(resultSet.getString(9));
    order.setShipAddress2(resultSet.getString(10));
    order.setShipCity(resultSet.getString(11));
    order.setShipCountry(resultSet.getString(12));
    order.setShipState(resultSet.getString(13));
    order.setShipToFirstName(resultSet.getString(14));
    order.setShipToLastName(resultSet.getString(15));
    order.setShipZip(resultSet.getString(16));
    order.setCardType(resultSet.getString(17));
    order.setCourier(resultSet.getString(18));
    order.setCreditCard(resultSet.getString(19));
    order.setExpiryDate(resultSet.getString(20));
    order.setLocale(resultSet.getString(21));
    order.setOrderDate(resultSet.getDate(22));
    order.setOrderId(resultSet.getInt(23));
    order.setTotalPrice(resultSet.getBigDecimal(24));
    order.setUsername(resultSet.getString(25));
    order.setStatus(resultSet.getString(26));
  }

  @Override
  public void insertOrder(Order order) {
    try {
      /* connect to mysql and get data */
      Connection connection = DataBaseUtil.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER);

      /* config the update info */
      preparedStatement.setString(1, order.getOrderId() + "");
      preparedStatement.setString(2, order.getUsername());
      preparedStatement.setString(3, order.getOrderDate().toString());
      preparedStatement.setString(4, order.getShipAddress1());
      preparedStatement.setString(5, order.getShipAddress2());
      preparedStatement.setString(6, order.getShipCity());
      preparedStatement.setString(7, order.getShipState());
      preparedStatement.setString(8, order.getShipZip());
      preparedStatement.setString(9, order.getShipCountry());
      preparedStatement.setString(10, order.getBillAddress1());
      preparedStatement.setString(11, order.getBillAddress2());
      preparedStatement.setString(12, order.getBillCity());
      preparedStatement.setString(13, order.getBillState());
      preparedStatement.setString(14, order.getBillZip());
      preparedStatement.setString(15, order.getBillCountry());
      preparedStatement.setString(16, order.getCourier());
      preparedStatement.setString(17, order.getTotalPrice().toString());
      preparedStatement.setString(18, order.getBillToFirstName());
      preparedStatement.setString(19, order.getBillToLastName());
      preparedStatement.setString(20, order.getShipToFirstName());
      preparedStatement.setString(21, order.getShipToLastName());
      preparedStatement.setString(22, order.getCreditCard());
      preparedStatement.setString(23, order.getExpiryDate());
      preparedStatement.setString(24, order.getCardType());
      preparedStatement.setString(25, order.getLocale());

      /* update */
      preparedStatement.executeUpdate();

      /* disconnect to database */
      DataBaseUtil.closePreparedStatement(preparedStatement);
      DataBaseUtil.closeConnection(connection);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void insertOrderStatus(Order order) {
    try {
      /* connect to mysql and get data */
      Connection connection = DataBaseUtil.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER_STATUS);

      /* config the update info */
      preparedStatement.setString(1, order.getOrderId() + "");
      preparedStatement.setString(2, order.getOrderId() + "");
      preparedStatement.setString(3, order.getOrderDate().toString());
      preparedStatement.setString(4, order.getStatus());

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
