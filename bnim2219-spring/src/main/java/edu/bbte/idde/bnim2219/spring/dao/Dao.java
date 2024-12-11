package edu.bbte.idde.bnim2219.spring.dao;

import edu.bbte.idde.bnim2219.spring.dao.exceptions.BackendConnectionException;
import edu.bbte.idde.bnim2219.spring.dao.exceptions.ChoreNotFoundException;
import edu.bbte.idde.bnim2219.spring.model.BaseEntity;

import java.util.Collection;

// interface for implementing CRUD functions
public interface Dao<T extends BaseEntity> {
    Long create(T entity) throws BackendConnectionException;

    T findById(Long id) throws ChoreNotFoundException, BackendConnectionException;

    Collection<T> findAll() throws BackendConnectionException;

    void update(Long id, T entity) throws ChoreNotFoundException, BackendConnectionException;

    void delete(Long id) throws ChoreNotFoundException, BackendConnectionException;
}
