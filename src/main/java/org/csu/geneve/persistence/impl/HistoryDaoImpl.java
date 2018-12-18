package org.csu.geneve.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.csu.geneve.domain.Record;
import org.csu.geneve.persistence.HistoryDao;

public class HistoryDaoImpl implements HistoryDao {
  private static final String GET_LIST_BY_USERNAME =
          "SELECT USERNAME, ITEM, DATE FROM HISTORY WHERE USERNAME= ?";
  private static final String INSERT_HISTORY =
          "INSERT INTO HISTORY (USERNAME, ITEM, DATE) VALUES (?, ?, ?)";

  @Override
  public List<Record> getListsByUsername(String username) {
    /* state result object */
    List<Record> list = new ArrayList<Record>();

    try {
      /* connect to mysql and get data */
      Connection connection = DataBaseUtil.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(GET_LIST_BY_USERNAME);
      preparedStatement.setString(1,username);
      ResultSet resultSet = preparedStatement.executeQuery();

      /* progress data */
      while (resultSet.next()) {
        Record record = new Record();
        record.setItem(resultSet.getString(2));
        record.setDate(resultSet.getString(3));
        list.add(record);
      }

      /* disconnect to database */
      DataBaseUtil.closeResultSet(resultSet);
      DataBaseUtil.closePreparedStatement(preparedStatement);
      DataBaseUtil.closeConnection(connection);
    } catch (Exception e) {
      e.printStackTrace();
    }
    Collections.reverse(list);
    /* return the result object */
    return list;
  }

  @Override
  public void insertHistory(String username, String item) {
    try {
      /* connect to mysql and get data */
      Connection connection = DataBaseUtil.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(INSERT_HISTORY);

      /* config the update info */
      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      Date currentDate = new Date();
      String date = formatter.format(currentDate);
      preparedStatement.setString(1,username);
      preparedStatement.setString(2,item);
      preparedStatement.setString(3,date);

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
