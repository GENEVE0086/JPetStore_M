package org.csu.geneve.web.servlets.catalog;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.csu.geneve.domain.Account;
import org.csu.geneve.domain.Record;
import org.csu.geneve.service.HistoryService;

public class ViewHistoryServlet extends HttpServlet {
  private static final String VIEW_HISTORY =
          "/WEB-INF/jsp/catalog/History.jsp";

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    doGet(request, response);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    HttpSession session = request.getSession();
    Account account = (Account) session.getAttribute("account");
    String username = account.getUsername();
    HistoryService service = new HistoryService();
    List<Record> recordList = service.getListByUsername(username);
    for (int i = 10; i < recordList.size(); ++i) {
      recordList.remove(i);
    }
    session.setAttribute("recordList", recordList);
    request.getRequestDispatcher(VIEW_HISTORY).forward(request, response);
  }
}
