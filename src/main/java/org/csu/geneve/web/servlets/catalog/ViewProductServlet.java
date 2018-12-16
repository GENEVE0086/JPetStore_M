package org.csu.geneve.web.servlets.catalog;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.csu.geneve.domain.Item;
import org.csu.geneve.domain.Product;
import org.csu.geneve.service.CatalogService;

public class ViewProductServlet extends HttpServlet {
  /* jsp path */
  private static final String VIEW_PRODUCTY =
          "/WEB-INF/jsp/catalog/Product.jsp";

  /* temp data */
  private String productId;

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    doGet(request, response);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    /* get list by productId store in request */
    productId = request.getParameter("productId");
    CatalogService service = new CatalogService();
    Product product = service.getProduct(productId);
    List<Item> itemList = service.getItemListByProduct(productId);

    /* put the product and the list into the session */
    HttpSession session = request.getSession();
    session.setAttribute("product", product);
    session.setAttribute("itemList", itemList);

    /* transport and show */
    request.getRequestDispatcher(VIEW_PRODUCTY).forward(request, response);
  }
}
