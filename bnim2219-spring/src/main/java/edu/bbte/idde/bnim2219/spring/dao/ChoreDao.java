package edu.bbte.idde.bnim2219.spring.dao;

import edu.bbte.idde.bnim2219.spring.dao.exceptions.BackendConnectionException;
import edu.bbte.idde.bnim2219.spring.model.Chore;

import java.util.Collection;

// interface for implementing Chore specific CRUD functions
public interface ChoreDao extends Dao<Chore> {
    Collection<Chore> findChoresByDone(Boolean done) throws BackendConnectionException;
}
