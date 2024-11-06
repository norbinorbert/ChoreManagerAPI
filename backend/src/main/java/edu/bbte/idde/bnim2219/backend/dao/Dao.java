package edu.bbte.idde.bnim2219.backend.dao;

import edu.bbte.idde.bnim2219.backend.dao.exceptions.NotFoundException;
import edu.bbte.idde.bnim2219.backend.dao.exceptions.BackendNotAvailableException;
import edu.bbte.idde.bnim2219.backend.model.BaseEntity;

import java.util.Collection;

// interface for implementing CRUD functions
public interface Dao<T extends BaseEntity> {
    Long create(T entity) throws BackendNotAvailableException;

    T findById(Long id) throws NotFoundException, BackendNotAvailableException;

    Collection<T> findAll() throws BackendNotAvailableException;

    void update(Long id, T entity) throws NotFoundException, BackendNotAvailableException;

    void delete(Long id) throws NotFoundException, BackendNotAvailableException;

}
