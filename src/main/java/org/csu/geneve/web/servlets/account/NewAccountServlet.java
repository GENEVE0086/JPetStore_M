package org.csu.geneve.web.servlets.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.csu.geneve.domain.Account;
import org.csu.geneve.service.AccountService;

public class NewAccountServlet extends HttpServlet {
  /* jsp path */
  private static final String SIGN_ON = "/WEB-INF/jsp/account/SignonForm.jsp";
  private static final String REGISTER = "/WEB-INF/jsp/account/ReigsterForm.jsp";

  /* temp data */
  private Account account;
  private String username;
  private String password;
  private String repeatedPassword;
  private String firstName;
  private String lastName;
  private String email;
  private String phone;
  private String address1;
  private String address2;
  private String city;
  private String state;
  private String zip;
  private String country;
  private String languages;
  private String categories;
  boolean listOption;
  boolean bannerOption;

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    doGet(request, response);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    /* get temp data */
    username = request.getParameter("username");
    password = request.getParameter("password");
    repeatedPassword = request.getParameter("repeatedPassword");
    firstName = request.getParameter("firstName");
    lastName = request.getParameter("lastName");
    email = request.getParameter("email");
    phone = request.getParameter("phone");
    address1 = request.getParameter("address1");
    address2 = request.getParameter("address2");
    city = request.getParameter("city");
    state = request.getParameter("state");
    zip = request.getParameter("zip");
    country = request.getParameter("country");
    languages = request.getParameter("languagePreference");
    categories = request.getParameter("favouriteCategoryId");
    listOption = request.getParameter("listOption") != null;
    bannerOption = request.getParameter("bannerOption") != null;

    if (username.equals("")) {
      /* null id is illegal */
      HttpSession session = request.getSession();
      session.setAttribute("message", "the id can not be null");
      request.getRequestDispatcher(REGISTER).forward(request, response);
      return;
    }

    AccountService service = new AccountService();
    Account account = service.getAccount(username);
    if (account != null) {
      /* id already exist */
      HttpSession session = request.getSession();
      session.setAttribute("message", "the id has already existed");
      request.getRequestDispatcher(REGISTER).forward(request, response);
      return;
    }
    if (password.equals(repeatedPassword)) {
      /*
      password is same
      edit data
      */


      HttpSession session = request.getSession();
      if (password.equals("")) {
        /* null password is is illegal */
        session.setAttribute("username", username);
        session.setAttribute("message", "password can not be null");
        request.getRequestDispatcher(REGISTER).forward(request, response);
        return;
      }

      account = new Account();
      account.setUsername(username);
      ConfirmEditServlet.setAccount(password, firstName, lastName, email, phone, address1, address2, city, state, zip,
              country, languages, categories, listOption, bannerOption, account);

      /* service to put new info into data base*/
      AccountService accountService = new AccountService();
      accountService.insertAccount(account);

      /* remove used info */
      session.removeAttribute("message");
      session.removeAttribute("username");

      /* show sign on page*/
      request.getRequestDispatcher(SIGN_ON).forward(request, response);
    } else {
      /* re-register */
      HttpSession session = request.getSession();
      session.setAttribute("username", username);
      session.setAttribute("message", "please input same password");
      request.getRequestDispatcher(REGISTER).forward(request, response);

    }
  }
}
