package org.csu.geneve.web.servlets.order;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.csu.geneve.domain.Order;

public class ConfirmOrderServlet extends HttpServlet {
  /* jsp path */
  private static final String CONFIRM_ORDER = "/WEB-INF/jsp/order/ConfirmOrder.jsp";
  private static final String SHIPPING_FORM = "/WEB-INF/jsp/order/ShippingForm.jsp";

  /* temp data */
  private String cardType;
  private String creditCard;
  private String expiryDate;
  private String firstName;
  private String lastName;
  private String address1;
  private String address2;
  private String city;
  private String state;
  private String zip;
  private String country;


  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    doGet(request, response);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    /* get account info from session */
    cardType = request.getParameter("cardType");
    creditCard = request.getParameter("creditCard");
    expiryDate = request.getParameter("expiryDate");
    firstName = request.getParameter("firstName");
    lastName = request.getParameter("lastName");
    address1 = request.getParameter("address1");
    address2 = request.getParameter("address2");
    city = request.getParameter("city");
    state = request.getParameter("state");
    zip = request.getParameter("zip");
    country = request.getParameter("country");
    HttpSession session = request.getSession();

    /* get order from session and change it with the info just get */
    Order order = (Order)session.getAttribute("order");
    order.setCardType(cardType);
    order.setCreditCard(creditCard);
    order.setExpiryDate(expiryDate);
    order.setBillToFirstName(firstName);
    order.setBillToLastName(lastName);
    order.setShipToFirstName(firstName);
    order.setShipToLastName(lastName);
    order.setBillAddress1(address1);
    order.setBillAddress2(address2);
    order.setShipAddress1(address1);
    order.setShipAddress2(address2);
    order.setBillCity(city);
    order.setShipCity(city);
    order.setBillState(state);
    order.setShipState(state);
    order.setBillZip(zip);
    order.setShipZip(zip);
    order.setBillCountry(country);
    order.setShipCountry(country);

    /* put the updated order into session */
    session.setAttribute("order",order);

    /* transport and show the page */
    if (request.getParameter("shippingAddressRequired") != null) {
      request.getRequestDispatcher(SHIPPING_FORM).forward(request,response);
    } else {
      request.getRequestDispatcher(CONFIRM_ORDER).forward(request,response);
    }
  }
}
