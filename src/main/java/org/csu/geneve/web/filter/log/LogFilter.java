package org.csu.geneve.web.filter.log;

import org.csu.geneve.domain.Account;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


import org.csu.geneve.service.LogService;
//import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.log4j.Logger;

public class LogFilter implements Filter {

  protected Logger logger = Logger.getLogger(getClass());
  private static int count = 0;
  private String username;
  private String date;
  private String uri;

  public void destroy() {
  }

  public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
          throws ServletException, IOException {
    try {
      count++;

      System.out.print(count + ".");

      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      Date currentDate = new Date();
      date = formatter.format(currentDate);
      System.out.print(date + ":");
      HttpServletRequest request = (HttpServletRequest) req;
      HttpSession session = request.getSession();
      uri = request.getRequestURI();
      Account account = (Account) session.getAttribute("account");
      username = (account == null) ? "Temporary user" : account.getUsername();

      LogService service = new LogService();
      service.insertLog(count, username, uri, date);
      logger.info(count + "." + date + ":" + "user:" + username + " visit " + uri + "\n");
      System.out.println(count + "." + date + ":" + "user:" + username + " visit " + uri + "\n");
    } catch (Exception e) {
      e.printStackTrace();
    }
    chain.doFilter(req, resp);
  }

  public void init(FilterConfig config) throws ServletException {
  }

}
