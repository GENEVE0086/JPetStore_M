package org.csu.geneve.persistence;

import java.util.List;

import org.csu.geneve.domain.Category;

public interface CategoryDao {

  List<Category> getCategoryList();

  Category getCategory(String categoryId);
}
