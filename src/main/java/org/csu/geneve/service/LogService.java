package org.csu.geneve.service;

import org.csu.geneve.persistence.LogDao;
import org.csu.geneve.persistence.impl.LogDaoImpl;

public class LogService {
  private LogDao logDao;

  public LogService() {
    logDao = new LogDaoImpl();
  }

  public void insertLog(int count, String username, String uri, String date) {
    logDao.insertLog(count, username, uri, date);
  }
}
