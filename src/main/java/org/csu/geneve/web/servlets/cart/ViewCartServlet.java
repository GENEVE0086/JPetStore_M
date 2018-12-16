package org.csu.geneve.web.servlets.cart;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.csu.geneve.domain.Cart;

public class ViewCartServlet extends HttpServlet {
  /* jsp path */
  private static final String VIRW_CART = "/WEB-INF/jsp/cart/Cart.jsp";
  
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    doGet(request, response);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    /* get cart form session */
    HttpSession session = request.getSession();
    Cart cart = (Cart)session.getAttribute("cart");

    /* no cart */
    if (cart == null) {
      cart = new Cart();
      session.setAttribute("cart", cart);
    }
    /* transport and show cart */
    request.getRequestDispatcher(VIRW_CART).forward(request, response);
  }
}
