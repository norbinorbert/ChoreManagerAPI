package edu.bbte.idde.bnim2219.dao.jdbc;

import edu.bbte.idde.bnim2219.dao.Dao;
import edu.bbte.idde.bnim2219.dao.exceptions.NotFoundException;
import edu.bbte.idde.bnim2219.model.BaseEntity;

import java.util.Collection;
import java.util.List;

public class JdbcDao<T extends BaseEntity> implements Dao<T> {
    @Override
    public Long create(T entity) {
        return 0L;
    }

    @Override
    public T findById(Long id) throws NotFoundException {
        return null;
    }

    @Override
    public Collection<T> findAll() {
        return List.of();
    }

    @Override
    public void update(Long id, T entity) throws NotFoundException {

    }

    @Override
    public void delete(Long id) throws NotFoundException {

    }
}
