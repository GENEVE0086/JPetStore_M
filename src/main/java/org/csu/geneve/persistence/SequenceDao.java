package org.csu.geneve.persistence;

import org.csu.geneve.domain.Sequence;

public interface SequenceDao {

  Sequence getSequence(Sequence sequence);

  boolean updateSequence(Sequence sequence);
}
