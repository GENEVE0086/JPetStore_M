package org.csu.geneve.web.filter.history;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.csu.geneve.domain.Account;
import org.csu.geneve.service.HistoryService;

public class HistoryFilter implements Filter {
  public void destroy() {
  }

  public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
          throws ServletException, IOException {

    HttpServletRequest request = (HttpServletRequest) req;
    HttpSession session = request.getSession();
    Account account = (Account) session.getAttribute("account");

    if (account != null) {
      String username = account.getUsername();
      String item = request.getParameter("itemId");
      HistoryService service = new HistoryService();
      service.insertHistory(username, item);
    }

    chain.doFilter(req, resp);
  }

  public void init(FilterConfig config) throws ServletException {

  }

}
