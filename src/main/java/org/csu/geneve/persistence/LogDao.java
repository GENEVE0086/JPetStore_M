package org.csu.geneve.persistence;

import java.math.BigInteger;

public interface LogDao {
  void insertLog(int count, String username, String uri, String date);
}
