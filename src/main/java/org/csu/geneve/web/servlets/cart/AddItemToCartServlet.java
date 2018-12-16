package org.csu.geneve.web.servlets.cart;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.csu.geneve.domain.Cart;
import org.csu.geneve.domain.Item;
import org.csu.geneve.service.CatalogService;


public class AddItemToCartServlet extends HttpServlet {
  /* jsp path */
  private static final String VIRW_CART = "/WEB-INF/jsp/cart/Cart.jsp";

  private String workingItemId;
  private Cart cart;

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    doGet(request, response);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    /* get data form request */
    workingItemId = request.getParameter("workingItemId");

    /* get cart form session */
    HttpSession session = request.getSession();
    cart = (Cart)session.getAttribute("cart");

    /* if no cart creat one */
    if (cart == null) {
      cart = new Cart();
    }

    if (cart.containsItemId(workingItemId)) {
      /* one more */
      cart.incrementQuantityByItemId(workingItemId);
    } else {
      /* add new */
      CatalogService service = new CatalogService();
      boolean isInStock = service.isItemInStock(workingItemId);
      Item item = service.getItem(workingItemId);
      cart.addItem(item,isInStock);
    }
    /*
    add cart to session
    transport and show the cart
    */
    session.setAttribute("cart",cart);
    request.getRequestDispatcher(VIRW_CART).forward(request, response);
  }
}
