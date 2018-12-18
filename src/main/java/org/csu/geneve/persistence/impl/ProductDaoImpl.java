package org.csu.geneve.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.csu.geneve.domain.Product;
import org.csu.geneve.persistence.ProductDao;
import org.jetbrains.annotations.NotNull;


public class ProductDaoImpl implements ProductDao {

  /*
  constants
  SQL Language
  */
  private static final String GET_PRODUCT =
          "SELECT PRODUCTID, NAME, DESCN as description, CATEGORY as "
                  + "categoryId FROM PRODUCT WHERE PRODUCTID = ?";
  private static final String GET_PRODUCT_LIST =
          "SELECT PRODUCTID, NAME, DESCN as description, CATEGORY as "
                  + "categoryId FROM PRODUCT WHERE CATEGORY = ?";
  private static final String SEARCH_PRODUCT_LIST =
          "SELECT PRODUCTID, NAME, DESCN as description, CATEGORY as "
                  + "categoryId FROM PRODUCT WHERE lower(name) like ?";

  @Override
  public List<Product> getProductList(String categoryId) {
    return getProductListInner(categoryId, GET_PRODUCT_LIST);
  }

  @Override
  public Product getProduct(String productId) {
    /* state result object */
    Product product = null;
    try {
      /* connect to mysql and get data */
      Connection connection = DataBaseUtil.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCT);
      preparedStatement.setString(1, productId);
      ResultSet resultSet = preparedStatement.executeQuery();

      /* progress data */
      if (resultSet.next()) {
        product = new Product();
        setProduct(product, resultSet);
      }

      /* disconnect to database */
      DataBaseUtil.closeResultSet(resultSet);
      DataBaseUtil.closePreparedStatement(preparedStatement);
      DataBaseUtil.closeConnection(connection);
    } catch (Exception e) {
      e.printStackTrace();
    }
    /* return the result object */
    return product;
  }

  @Override
  public List<Product> searchProductList(String keywords) {
    return getProductListInner(keywords, SEARCH_PRODUCT_LIST);
  }

  @NotNull
  private List<Product> getProductListInner(String keywords, String searchProductList) {
    /* state result object */
    List<Product> productList = new ArrayList<Product>();
    try {
      //connect to mysql and get data
      Connection connection = DataBaseUtil.getConnection();
      PreparedStatement preparedStatement =
              connection.prepareStatement(searchProductList);
      preparedStatement.setString(1, keywords);
      ResultSet resultSet = preparedStatement.executeQuery();

      /* progress data */
      while (resultSet.next()) {
        Product product = new Product();
        setProduct(product, resultSet);
        productList.add(product);
      }

      /* debug and out */
      System.out.println("product" + productList.size());

      /* disconnect to database */
      DataBaseUtil.closeResultSet(resultSet);
      DataBaseUtil.closePreparedStatement(preparedStatement);
      DataBaseUtil.closeConnection(connection);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return productList;
  }

  /* turn a set of data from database into a product */
  private void setProduct(Product product, ResultSet resultSet) throws SQLException {
    /* deal each data */
    product.setProductId(resultSet.getString(1));
    product.setCategoryId(resultSet.getString(4));
    product.setName(resultSet.getString(2));
    product.setDescription(resultSet.getString(3));

    /* debug and out */
    System.out.println("ProductId " + product.getProductId());
    System.out.println("CategoryId " + product.getCategoryId());
    System.out.println("Name " + product.getName());
    System.out.println("Description " + product.getDescription());
  }
}
