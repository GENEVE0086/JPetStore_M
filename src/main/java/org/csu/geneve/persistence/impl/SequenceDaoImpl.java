package org.csu.geneve.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.csu.geneve.domain.Sequence;
import org.csu.geneve.persistence.SequenceDao;

public class SequenceDaoImpl implements SequenceDao {
  /*
  constants
  SQL Language
  */
  private static final String GET_SEQUENCE =
          "SELECT name, nextid FROM SEQUENCE WHERE NAME = ?";

  private static final String UPDATE_SEQUENCE =
          "UPDATE SEQUENCE SET NEXTID = ? WHERE NAME = ?";

  @Override
  public Sequence getSequence(Sequence sequence) {
    /* state result object */
    Sequence checkSequence = null;

    try {
      /* connect to mysql and get data */
      Connection connection = DataBaseUtil.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(GET_SEQUENCE);
      preparedStatement.setString(1,sequence.getName());
      ResultSet resultSet = preparedStatement.executeQuery();

      /* progress data */
      if (resultSet.next()) {
        checkSequence = new Sequence();
        checkSequence.setName(resultSet.getString(1));
        checkSequence.setNextId(resultSet.getInt(2));
      }

      /* disconnect to database */
      DataBaseUtil.closeResultSet(resultSet);
      DataBaseUtil.closePreparedStatement(preparedStatement);
      DataBaseUtil.closeConnection(connection);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return checkSequence;
  }

  @Override
  public boolean updateSequence(Sequence sequence) {
    boolean flag = false;
    try {
      /* connect to mysql and get data */
      Connection connection = DataBaseUtil.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SEQUENCE);

      /* config the update info */
      preparedStatement.setString(2,sequence.getName());
      preparedStatement.setInt(1,sequence.getNextId());

      /* update */
      flag = preparedStatement.executeUpdate() == 1;

      /* disconnect to database */
      DataBaseUtil.closePreparedStatement(preparedStatement);
      DataBaseUtil.closeConnection(connection);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return flag;
  }
}
