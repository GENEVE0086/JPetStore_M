package org.csu.geneve.web.servlets.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignonFormServlet extends HttpServlet {
  /* jsp path */
  private static final String SIGNON_FORM = "/WEB-INF/jsp/account/SignonForm.jsp";



  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    doGet(request, response);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    request.getSession().removeAttribute("message");
    request.getRequestDispatcher(SIGNON_FORM).forward(request, response);
  }
}