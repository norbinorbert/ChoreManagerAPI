package edu.bbte.idde.bnim2219.dao.memory;

import edu.bbte.idde.bnim2219.dao.Dao;
import edu.bbte.idde.bnim2219.dao.exceptions.NotFoundException;
import edu.bbte.idde.bnim2219.model.BaseEntity;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

// class that implements the CRUD functions, stores data in memory in a map
public abstract class MemoryDao<T extends BaseEntity> implements Dao<T> {
    protected final Map<Long, T> entities = new ConcurrentHashMap<>();
    protected final AtomicLong id = new AtomicLong(0L);

    // creates a new entity and returns its id
    @Override
    public Long create(T entity) {
        Long id = this.id.incrementAndGet();
        entity.setId(id);
        entities.put(id, entity);
        return id;
    }

    // returns the entity with the provided ID
    @Override
    public T findById(Long id) throws NotFoundException {
        T entity = entities.get(id);
        if (entity == null) {
            throw new NotFoundException();
        }
        return entity;
    }

    // returns all entities in a collection
    @Override
    public Collection<T> findAll() {
        return entities.values();
    }

    // updates the entity that has the provided ID, uses data from the provided entity
    @Override
    public void update(Long id, T entity) throws NotFoundException {
        T existingEntity = entities.get(id);
        if (existingEntity == null) {
            throw new NotFoundException();
        }
        entity.setId(id);
        entities.put(id, entity);
    }

    // deletes the entity that has the provided ID
    @Override
    public void delete(Long id) throws NotFoundException {
        T existingEntity = entities.get(id);
        if (existingEntity == null) {
            throw new NotFoundException();
        }
        entities.remove(id);
    }
}
