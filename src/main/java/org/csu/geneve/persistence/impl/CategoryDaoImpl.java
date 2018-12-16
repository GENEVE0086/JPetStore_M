package org.csu.geneve.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.csu.geneve.domain.Category;
import org.csu.geneve.persistence.CategoryDao;

public class CategoryDaoImpl implements CategoryDao {
  /*
  constants
  SQL Language
  */
  private static final String GET_CATEGORY_LIST =
          "SELECT CATID AS categoryId, NAME, DESCN AS description FROM CATEGORY";

  private static final String GET_CATEGORY =
          "SELECT CATID AS categoryId, NAME, DESCN AS description FROM CATEGORY WHERE CATID = ?";

  @Override
  public List<Category> getCategoryList() {
    /* state result object */
    List<Category> categoryList = new ArrayList<Category>();

    try {
      /* connect to mysql and get data */
      Connection connection = DataBaseUtil.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(GET_CATEGORY_LIST);
      ResultSet resultSet = preparedStatement.executeQuery();

      /* progress data */
      while (resultSet.next()) {
        Category category = new Category();
        setCategory(resultSet, category);
        categoryList.add(category);
      }

      /* debug and out */
      System.out.println("category" + categoryList.size());

      /* disconnect to database */
      DataBaseUtil.closeResultSet(resultSet);
      DataBaseUtil.closePreparedStatement(preparedStatement);
      DataBaseUtil.closeConnection(connection);
    } catch (Exception e) {
      e.printStackTrace();
    }
    /* return the result object */
    return categoryList;
  }

  /* turn a set of data from database into a category */
  private void setCategory(ResultSet resultSet, Category category) throws SQLException {
    /* deal each data */
    category.setCategoryId(resultSet.getString(1));
    category.setName(resultSet.getString(2));
    category.setDescription(resultSet.getString(3));

    /* debug and out */
    System.out.println("CatCategoryId " + category.getCategoryId());
    System.out.println("Name " + category.getName());
    System.out.println("Description " + category.getDescription());
  }

  @Override
  public Category getCategory(String categoryId) {
    /* state result object */
    Category category = null;

    try {
      /* connect to mysql and get data */
      Connection connection = DataBaseUtil.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(GET_CATEGORY);
      preparedStatement.setString(1, categoryId);
      ResultSet resultSet = preparedStatement.executeQuery();

      /* progress data */
      if (resultSet.next()) {
        category = new Category();
        setCategory(resultSet, category);
      }

      /* disconnect to database */
      DataBaseUtil.closeResultSet(resultSet);
      DataBaseUtil.closePreparedStatement(preparedStatement);
      DataBaseUtil.closeConnection(connection);
    } catch (Exception e) {
      e.printStackTrace();
    }
    /* return the result object */
    return category;
  }
}
