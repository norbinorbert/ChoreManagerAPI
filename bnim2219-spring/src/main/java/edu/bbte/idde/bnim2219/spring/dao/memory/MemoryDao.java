package edu.bbte.idde.bnim2219.spring.dao.memory;

import edu.bbte.idde.bnim2219.spring.dao.Dao;
import edu.bbte.idde.bnim2219.spring.dao.exceptions.ChoreNotFoundException;
import edu.bbte.idde.bnim2219.spring.model.BaseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

// class that implements the CRUD functions, stores data in memory in a map
@Slf4j
@Profile("!jdbc")
public abstract class MemoryDao<T extends BaseEntity> implements Dao<T> {
    protected Map<Long, T> entities = new ConcurrentHashMap<>();
    protected AtomicLong id = new AtomicLong();

    // creates a new entity and returns its id
    @Override
    public Long create(T entity) {
        log.info("Created new entity");
        Long id = this.id.incrementAndGet();
        entity.setId(id);
        entities.put(id, entity);
        return id;
    }

    // returns the entity with the provided ID
    @Override
    public T findById(Long id) throws ChoreNotFoundException {
        T entity = entities.get(id);
        log.info("Trying to find entity by id");
        if (entity == null) {
            log.info("Couldn't find because entity doesn't exist");
            throw new ChoreNotFoundException();
        }
        log.info("Found entity");
        return entity;
    }

    // returns all entities in a collection
    @Override
    public Collection<T> findAll() {
        log.info("Found all entities");
        return entities.values();
    }

    // updates the entity that has the provided ID, uses data from the provided entity
    @Override
    public void update(Long id, T entity) throws ChoreNotFoundException {
        T existingEntity = entities.get(id);
        log.info("Trying to update entity by id");
        if (existingEntity == null) {
            log.info("Couldn't update because entity doesn't exist");
            throw new ChoreNotFoundException();
        }
        log.info("Successfully updated entity");
        entity.setId(id);
        entities.put(id, entity);
    }

    // deletes the entity that has the provided ID
    @Override
    public void delete(Long id) throws ChoreNotFoundException {
        T existingEntity = entities.get(id);
        log.info("Trying to delete entity by id");
        if (existingEntity == null) {
            log.info("Couldn't delete because entity doesn't exist");
            throw new ChoreNotFoundException();
        }
        log.info("Successfully deleted entity");
        entities.remove(id);
    }
}
