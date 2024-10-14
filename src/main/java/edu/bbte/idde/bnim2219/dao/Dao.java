package edu.bbte.idde.bnim2219.dao;

import edu.bbte.idde.bnim2219.dao.exceptions.NotFoundException;
import edu.bbte.idde.bnim2219.model.BaseEntity;

import java.util.Collection;

// interface for implementing CRUD functions
public interface Dao<T extends BaseEntity> {
    Long create(T entity);
    T findById(Long ID) throws NotFoundException;
    Collection<T> findAll();
    void update(Long id, T entity) throws NotFoundException;
    void delete(Long id) throws NotFoundException;
}
