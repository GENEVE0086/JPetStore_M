package org.csu.geneve.web.servlets.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.csu.geneve.domain.Account;
import org.csu.geneve.service.AccountService;

public class LoginServlet extends HttpServlet {
  /* jsp path */
  private static final String MAIN = "/WEB-INF/jsp/catalog/Main.jsp";
  private static final String SIGNON_FORM = "/WEB-INF/jsp/account/SignonForm.jsp";

  /* temp data */
  private String username;
  private String password;
  private Account account1;
  private Account account2;

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    doGet(request, response);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    HttpSession session = request.getSession();
    /* get data from request */
    username = request.getParameter("username");
    password = request.getParameter("password");

    if (username.equals("")) {
      session.setAttribute("message", "please input right id and password");
      request.getRequestDispatcher(SIGNON_FORM).forward(request, response);
      return;
    }

    if (password.equals("")) {
      session.setAttribute("message", "the password can not be null");
      request.getRequestDispatcher(SIGNON_FORM).forward(request, response);
      return;
    }

    String piccode = (String) request.getSession().getAttribute("piccode");
    String checkCode = request.getParameter("checkCode");  //取值
    //把字符全部转换为大写的（此语句可以用于验证码不区分大小写）
    checkCode = checkCode.toUpperCase();
    if (!checkCode.equals(piccode)) {
      session.setAttribute("message", "verify code wrong");
      request.getRequestDispatcher(SIGNON_FORM).forward(request, response);
      return;
    }

    /* service to handle */
    AccountService accountService = new AccountService();
    account1 = accountService.getAccount(username, password);
    account2 = accountService.getAccount(username);

    if (account2 == null) {
      /* not registered */
      session.setAttribute("message", "please input right id and password");
      request.getRequestDispatcher(SIGNON_FORM).forward(request, response);
      return;
    }
    if (account1 == null) {
      /* wrong password */
      session.setAttribute("username", username);
      session.setAttribute("message", "please input right id and password");
      request.getRequestDispatcher(SIGNON_FORM).forward(request, response);
    } else {
      /*
      right account
      put data into session
      show the main page with current account
      */
      session.setAttribute("account", account1);
      session.removeAttribute("username");
      session.removeAttribute("message");
      request.getRequestDispatcher(MAIN).forward(request, response);
    }
  }
}
