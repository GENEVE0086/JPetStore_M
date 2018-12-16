package org.csu.geneve.web.servlets.order;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.csu.geneve.domain.Order;
import org.csu.geneve.service.OrderService;

public class CheckOrderServlet extends HttpServlet {
  /* jsp path */
  private static final String VIEW_ORDER = "/WEB-INF/jsp/order/ViewOrder.jsp";

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    doGet(request, response);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    /* get orderId in session and get a order by iy */
    HttpSession session = request.getSession();
    Integer orderId = Integer.valueOf(request.getParameter("orderId"));
    OrderService orderService = new OrderService();
    Order order = orderService.getOrder(orderId);

    /* put the session just get into session */
    session.setAttribute("order",order);

    /* transport and show the order page */
    request.getRequestDispatcher(VIEW_ORDER).forward(request,response);
  }
}
