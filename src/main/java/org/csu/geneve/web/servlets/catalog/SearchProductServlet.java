package org.csu.geneve.web.servlets.catalog;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.csu.geneve.domain.Product;
import org.csu.geneve.service.CatalogService;

public class SearchProductServlet extends HttpServlet {
  /* jsp path */
  private static final String SEARCH_PRODUCT = "WEB-INF/jsp/catalog/SearchProduct.jsp";

  /* temp data */
  private String keyword;

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    doGet(request, response);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    /* get the list */
    keyword = request.getParameter("keyword");
    CatalogService service = new CatalogService();
    List<Product> productList = service.searchProductList(keyword);

    /* put the list into session */
    HttpSession session = request.getSession();
    session.setAttribute("productList", productList);

    /* transport and show search answer */
    request.getRequestDispatcher(SEARCH_PRODUCT).forward(request, response);
  }
}
