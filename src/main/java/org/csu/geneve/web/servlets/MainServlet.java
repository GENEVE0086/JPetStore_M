package org.csu.geneve.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class MainServlet extends HttpServlet {
  //jsp path
  private static final String MAIN = "WEB-INF/jsp/catalog/Main.jsp";

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    doGet(request, response);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    HttpSession session = request.getSession();
    //deal the request
    request.getRequestDispatcher(MAIN).forward(request, response);
  }
}
