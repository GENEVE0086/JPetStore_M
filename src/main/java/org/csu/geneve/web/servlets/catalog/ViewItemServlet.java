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

public class ViewItemServlet extends HttpServlet {

  private static final String VIEW_ITEM = "/WEB-INF/jsp/catalog/Item.jsp";

  private String itemId;

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    doGet(request, response);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    //get item by itemId stored in request
    itemId = request.getParameter("itemId");
    CatalogService service = new CatalogService();
    Item item = service.getItem(itemId);

    /* put the item into session */
    HttpSession session = request.getSession();
    session.setAttribute("item", item);

    Product product = (Product) session.getAttribute("product");
    if (product != null) {
      product = service.getProductByItemId(itemId);
      session.setAttribute("product",product);
      List<Item> itemList = service.getItemListByProduct(product.getProductId());
      session.setAttribute("itemList", itemList);
    }

    /* transport and show the item */
    request.getRequestDispatcher(VIEW_ITEM).forward(request, response);
  }
}
