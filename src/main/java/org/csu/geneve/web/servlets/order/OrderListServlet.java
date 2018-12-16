package org.csu.geneve.web.servlets.order;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.csu.geneve.domain.Account;
import org.csu.geneve.domain.Order;
import org.csu.geneve.service.OrderService;


public class OrderListServlet extends HttpServlet {
  //jsp path
  private static final String ORDER_LIST = "/WEB-INF/jsp/order/ListOrders.jsp";

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    doGet(request, response);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    /* get a account from session */
    HttpSession session = request.getSession();
    Account account = (Account)session.getAttribute("account");

    /* get order list in data base by account just get */
    OrderService orderService = new OrderService();
    List<Order> orderList = orderService.getOrdersByUsername(account.getUsername());

    /* put the list just get into session */
    session.setAttribute("orderList",orderList);

    //transport and show the order list
    request.getRequestDispatcher(ORDER_LIST).forward(request,response);
  }
}
