package org.csu.geneve.web.servlets.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditAccountFormServlet extends HttpServlet {
  /* jsp path */
  private static final String EDIT_ACCOUNT = "/WEB-INF/jsp/account/EditAccountForm.jsp";

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    doGet(request, response);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    /* call do account in register servlet */
    ReigsterFormServlet.doAccount(request, response, EDIT_ACCOUNT);
  }
}
