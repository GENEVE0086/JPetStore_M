package org.csu.geneve.service;

import java.util.List;

import org.csu.geneve.domain.Category;
import org.csu.geneve.domain.Item;
import org.csu.geneve.domain.Product;
import org.csu.geneve.persistence.CategoryDao;
import org.csu.geneve.persistence.ItemDao;
import org.csu.geneve.persistence.ProductDao;
import org.csu.geneve.persistence.impl.CategoryDaoImpl;
import org.csu.geneve.persistence.impl.ItemDaoImpl;
import org.csu.geneve.persistence.impl.ProductDaoImpl;

public class CatalogService {
  private CategoryDao categoryDao;
  private ProductDao productDao;
  private ItemDao itemDao;

  /*
  Constructor  and I still don't know how to write a right javadoc f**k!
  checkstyle always show underline
  It even feel my notes is too long
  */
  public CatalogService() {
    categoryDao = new CategoryDaoImpl();
    productDao = new ProductDaoImpl();
    itemDao = new ItemDaoImpl();
  }

  public List<Category> getCategoryList() {
    return categoryDao.getCategoryList();
  }

  public Category getCategory(String categoryId) {
    return categoryDao.getCategory(categoryId);
  }

  public Product getProduct(String productId) {
    return productDao.getProduct(productId);
  }

  public List<Product> getProductListByCategory(String categoryId) {
    return productDao.getProductList(categoryId);
  }

  public List<Product> searchProductList(String keyword) {
    return productDao.searchProductList("%" + keyword.toLowerCase() + "%");
  }

  public List<Item> getItemListByProduct(String productId) {
    return itemDao.getItemList(productId);
  }

  public Item getItem(String itemId) {
    return itemDao.getItem(itemId);
  }

  public boolean isItemInStock(String itemId) {
    return itemDao.getInventoryQuantity(itemId) > 0;
  }
}
