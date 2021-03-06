package org.csu.geneve.persistence.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class DataBaseUtil {

  /* constants information about the database */
  private static final String DRIVER = "com.mysql.jdbc.Driver";
  private static final String CONNECTION_NAME =
          "jdbc:mysql://47.101.180.71:3306/mypetstore?useUnicode=true&amp;characterEncoding=UTF-8";
  private static final String USER_NAME = "root";
  private static final String PASSWORD = "0k0k69??";

  /*
  connect to database
  I don't know how to make a javadoc and I'm unhappy
  */
  public static Connection getConnection() throws Exception {
    Connection connection = null;
    Class.forName(DRIVER);
    connection = DriverManager.getConnection(CONNECTION_NAME, USER_NAME, PASSWORD);
    return connection;
  }

  public static void closeStatement(Statement statement) throws Exception {
    statement.close();
  }

  public static void closePreparedStatement(PreparedStatement preparedStatement)
          throws Exception {
    preparedStatement.close();
  }

  public static void closeResultSet(ResultSet resultSet) throws Exception {
    resultSet.close();
  }

  public static void closeConnection(Connection connection) throws Exception {
    connection.close();
  }

  /*
  @test 数据库测试链接
  public static void main(String[] args) throws Exception {
    Connection connection = DataBaseUtil.getConnection();
    System.out.println(connection);
  }
  */
}
