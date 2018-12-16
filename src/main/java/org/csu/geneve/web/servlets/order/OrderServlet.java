package org.csu.geneve.web.servlets.order;

import org.csu.geneve.domain.Cart;
import org.csu.geneve.domain.Order;
import org.csu.geneve.service.OrderService;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class OrderServlet extends HttpServlet {
  /* jsp path */
  private static final String VIEW_ORDER = "/WEB-INF/jsp/order/ViewOrder.jsp";

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    doGet(request, response);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    /*
    get order info from session
    and put the lineItems of the order into session
    */
    HttpSession session = request.getSession();
    Order order = (Order) session.getAttribute("order");
    session.setAttribute("lineItems",order.getLineItems());

    /* update data in data base */
    OrderService orderService = new OrderService();
    orderService.insertOrder(order);

    /* clear the cart and put the bull cart into session */
    Cart cart = new Cart();
    session.setAttribute("cart",cart);

    /* transport and show the order website */
    request.getRequestDispatcher(VIEW_ORDER).forward(request,response);
  }
}
