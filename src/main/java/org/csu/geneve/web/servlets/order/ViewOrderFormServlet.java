package org.csu.geneve.web.servlets.order;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.csu.geneve.domain.Account;
import org.csu.geneve.domain.Cart;
import org.csu.geneve.domain.Order;

public class ViewOrderFormServlet extends HttpServlet {
  /* jsp path */
  private static final String VIEW_ORDER_FORM = "/WEB-INF/jsp/order/OrderForm.jsp";
  private static final String SIGN_ON = "/WEB-INF/jsp/account/SignonForm.jsp";

  /* temp data */
  private Cart cart;
  private Account account;

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    doGet(request, response);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    /* get cart info from session */
    HttpSession session = request.getSession();
    cart = (Cart) session.getAttribute("cart");
    if (cart == null) {
      cart = new Cart();
      session.setAttribute("cart",cart);
    }

    /* get account info from session */
    account = (Account) session.getAttribute("account");
    if (account == null) {
      /* null account (not log in) jump to login */
      request.getRequestDispatcher(SIGN_ON).forward(request,response);
    } else {
      /* creative a order of account include the cart */
      Order order = new Order();
      order.initOrder(account,cart);

      /* put order and payment info into session */
      session.setAttribute("creditCardTypes",order.getCardType());
      session.setAttribute("order",order);

      /* transport and show the order */
      request.getRequestDispatcher(VIEW_ORDER_FORM).forward(request,response);
    }
  }
}
