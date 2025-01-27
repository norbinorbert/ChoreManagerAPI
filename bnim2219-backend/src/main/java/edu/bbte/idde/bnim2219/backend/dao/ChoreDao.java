package edu.bbte.idde.bnim2219.backend.dao;

import edu.bbte.idde.bnim2219.backend.dao.exceptions.BackendConnectionException;
import edu.bbte.idde.bnim2219.backend.model.Chore;

import java.util.Collection;

// interface for implementing Chore specific CRUD functions
public interface ChoreDao extends Dao<Chore> {
    Collection<Chore> findByMinMax(Integer min, Integer max) throws BackendConnectionException;
}
