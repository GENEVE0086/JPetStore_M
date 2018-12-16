package org.csu.geneve.web.servlets.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.csu.geneve.domain.Account;

public class SignoffServlet extends HttpServlet {
  /* jsp path */
  private static final String MAIN = "/WEB-INF/jsp/catalog/Main.jsp";

  /* temp  data */
  private Account account;

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    doGet(request, response);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    /* cleat the account */
    account = null;

    /*
    get session
    config info in session
    transport and show the main page with null account
    */
    HttpSession session = request.getSession();
    session.setAttribute("account",account);
    request.getRequestDispatcher(MAIN).forward(request,response);
  }
}
