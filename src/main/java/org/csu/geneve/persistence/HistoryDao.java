package org.csu.geneve.persistence;

import java.util.List;

import org.csu.geneve.domain.Record;

public interface HistoryDao {

  public List<Record> getListsByUsername(String username);

  public void  insertHistory(String username, String item);
}
