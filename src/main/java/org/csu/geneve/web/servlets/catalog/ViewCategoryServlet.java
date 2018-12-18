package org.csu.geneve.web.servlets.catalog;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.csu.geneve.domain.Category;
import org.csu.geneve.domain.Product;
import org.csu.geneve.service.CatalogService;

public class ViewCategoryServlet extends HttpServlet {
  /* jsp path */
  private static final String VIEW_CATEGORY =
          "/WEB-INF/jsp/catalog/Category.jsp";

  private String categoryId;

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    doGet(request, response);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    /* get the list by categoryId stored in request */
    categoryId = request.getParameter("categoryId");
    CatalogService service = new CatalogService();
    Category category = service.getCategory(categoryId);
    List<Product> productList = service.getProductListByCategory(categoryId);

    //pur the list and category into session
    HttpSession session = request.getSession();
    session.setAttribute("category",category);
    session.setAttribute("productList",productList);
    
    //transport and show the category
    request.getRequestDispatcher(VIEW_CATEGORY).forward(request, response);
  }
}
