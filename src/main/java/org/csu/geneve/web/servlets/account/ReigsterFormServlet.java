package org.csu.geneve.web.servlets.account;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ReigsterFormServlet extends HttpServlet {
  /* jsp path */
  private static final String REGISTER_FORM = "/WEB-INF/jsp/account/ReigsterForm.jsp";

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    doGet(request, response);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    doAccount(request, response, REGISTER_FORM);
  }

  static void doAccount(HttpServletRequest request, HttpServletResponse response, String jspPath)
          throws ServletException, IOException {

    /* edit data */
    List<String> languages = new ArrayList<String>();
    languages.add("english");
    languages.add("japanese");

    List<String> categories = new ArrayList<>();
    categories.add("FISH");
    categories.add("DOGS");
    categories.add("REPTILES");
    categories.add("CATS");
    categories.add("BIRDS");

    /* get session */
    HttpSession session = request.getSession();

    /* config info in session */
    session.setAttribute("languages",languages);
    session.setAttribute("categories",categories);
    session.removeAttribute("message");

    /* transport and show  jpsPath -> web*/
    request.getRequestDispatcher(jspPath).forward(request,response);
  }
}
