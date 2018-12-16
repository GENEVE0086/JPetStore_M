package org.csu.geneve.web.servlets.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.csu.geneve.domain.Account;
import org.csu.geneve.service.AccountService;

public class ConfirmEditServlet extends HttpServlet {
  /* jsp path */
  private static final String MAIN = "/WEB-INF/jsp/catalog/Main.jsp";
  private static final String EDIT_ACCOUNT = "/WEB-INF/jsp/account/EditAccountForm.jsp";

  /* temp data */
  private Account account;
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


    if (password.equals(repeatedPassword)) {
      /*
      password is same
      get session
      */

      HttpSession session = request.getSession();
      /* edit data */
      account = (Account) session.getAttribute("account");
      if (password.equals("")) {
        /* not change password */
        password = account.getPassword();
      }
      setAccount(password, firstName, lastName, email, phone, address1, address2,
              city, state, zip, country, languages, categories, listOption, bannerOption, account);
      AccountService accountService = new AccountService();
      accountService.updateAccount(account);
      session.setAttribute("account", account);
      session.removeAttribute("message");

      /* transport and show */
      request.getRequestDispatcher(MAIN).forward(request,response);
    } else {
      /*
      password is not same
      transport and show
      */
      HttpSession session = request.getSession();
      session.setAttribute("message", "please input same password");
      request.getRequestDispatcher(EDIT_ACCOUNT).forward(request, response);
    }
  }

  static void setAccount(String password, String firstName, String lastName, String email,
                         String phone, String address1, String address2, String city, String state,
                         String zip, String country, String languages, String categories,
                         boolean listOption, boolean bannerOption, Account account) {
    account.setPassword(password);
    account.setFirstName(firstName);
    account.setLastName(lastName);
    account.setEmail(email);
    account.setPhone(phone);
    account.setAddress1(address1);
    account.setAddress2(address2);
    account.setCity(city);
    account.setState(state);
    account.setZip(zip);
    account.setCountry(country);
    account.setLanguagePreference(languages);
    account.setFavouriteCategoryId(categories);
    account.setListOption(listOption);
    account.setBannerOption(bannerOption);
  }
}
