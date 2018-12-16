package org.csu.geneve.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import org.csu.geneve.persistence.LogDao;

public class LogDaoImpl implements LogDao {
  private static final String INSERT_LOG =
          "INSERT INTO LOG (COUNT , USER , URI, DATE)"
                  + "VALUES(?, ?, ?, ?)";

  @Override
  public void insertLog(int count, String username, String uri, String date) {
    try {
      /* connect to mysql and get data */
      Connection connection = DataBaseUtil.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LOG);

      /* config the update info */
      preparedStatement.setInt(1,count);
      preparedStatement.setString(2,username);
      preparedStatement.setString(3,uri);
      preparedStatement.setString(4,date);

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
