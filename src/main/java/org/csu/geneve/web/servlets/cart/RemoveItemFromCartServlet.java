package org.csu.geneve.web.servlets.cart;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.csu.geneve.domain.Cart;
import org.csu.geneve.domain.Item;

public class RemoveItemFromCartServlet extends HttpServlet {
  /* jsp path */
  private static final String VIRW_CART = "/WEB-INF/jsp/cart/Cart.jsp";
  private static final String ERROR = "/WEB-INF/jsp/common/Error.jsp";

  /* temp data */
  private String workingItemId;
  private Cart cart;

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    doGet(request, response);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    /* get data from request */
    workingItemId = request.getParameter("workingItemId");

    /* get cart form session */
    HttpSession session = request.getSession();
    cart = (Cart)session.getAttribute("cart");

    /* get the item should be remove */
    Item item = cart.removeItemById(workingItemId);

    /* remove */
    if (item == null) {
      /*
      cant remove
      show the error page
      */
      session.setAttribute("message","Attempted to remove null CartItem form Cart.");
      request.getRequestDispatcher(ERROR).forward(request, response);
    } else {
      /* transport and show the cart after remove */
      request.getRequestDispatcher(VIRW_CART).forward(request, response);
    }
  }
}
