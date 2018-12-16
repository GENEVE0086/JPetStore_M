package org.csu.geneve.web.servlets.order;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.csu.geneve.domain.Order;

public class ConfirmShipServlet extends HttpServlet {
  private static final String CONFIRM_ORDER = "/WEB-INF/jsp/order/ConfirmOrder.jsp";

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    doGet(request, response);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    /* get nre order info from request */
    String shipToFirstName = request.getParameter("shipToFirstName");
    String shipToLastName = request.getParameter("shipToLastName");
    String shipAddress1 = request.getParameter("shipAddress1");
    String shipAddress2 = request.getParameter("shipAddress2");
    String shipCity = request.getParameter("shipCity");
    String shipState = request.getParameter("shipState");
    String shipZip = request.getParameter("shipZip");
    String shipCountry = request.getParameter("shipCountry");

    /* get order from session and change it with the info just get */
    HttpSession session = request.getSession();
    Order order = (Order)session.getAttribute("order");
    order.setShipToFirstName(shipToFirstName);
    order.setShipToLastName(shipToLastName);
    order.setShipAddress1(shipAddress1);
    order.setShipAddress2(shipAddress2);
    order.setShipCity(shipCity);
    order.setShipState(shipState);
    order.setShipZip(shipZip);
    order.setShipCountry(shipCountry);

    /* put the updated order into session  and transport and show the page*/
    session.setAttribute("order",order);
    request.getRequestDispatcher(CONFIRM_ORDER).forward(request,response);

  }
}
