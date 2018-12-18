package org.csu.geneve.web.filter.history;

import org.csu.geneve.domain.Account;
import org.csu.geneve.service.HistoryService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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
