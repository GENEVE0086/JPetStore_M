package org.csu.geneve.service;

import java.util.List;

import org.csu.geneve.domain.Record;
import org.csu.geneve.persistence.HistoryDao;
import org.csu.geneve.persistence.impl.HistoryDaoImpl;

public class HistoryService {
  private HistoryDao historyDao;

  public HistoryService() {
    historyDao = new HistoryDaoImpl();
  }

  public List<Record> getListByUsername(String username) {
    return historyDao.getListsByUsername(username);
  }

  public void  insertHistory(String username, String item) {
    historyDao.insertHistory(username, item);
  }
}
