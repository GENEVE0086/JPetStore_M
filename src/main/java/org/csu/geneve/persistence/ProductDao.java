package org.csu.geneve.persistence;

import java.util.List;

import org.csu.geneve.domain.Product;

public interface ProductDao {

  List<Product> getProductList(String categoryId);

  Product getProduct(String productId);

  List<Product> searchProductList(String keywords);

}

