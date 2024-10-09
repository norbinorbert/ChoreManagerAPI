package edu.bbte.idde.bnim2219.dao;

import edu.bbte.idde.bnim2219.model.Chore;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

// class that implements the CRUD functions, stores data in memory in a map
public class ChoreInMemoryDao implements ChoreDao{
    private final ConcurrentHashMap<Long, Chore> toDoList = new ConcurrentHashMap<>();
    private final AtomicLong id = new AtomicLong(0L);

    // creates a new chore and returns its it
    @Override
    public Long create(Chore chore) {
        Long ID = id.incrementAndGet();
        chore.setId(ID);
        toDoList.put(ID, chore);
        return ID;
    }

    // returns the chore with the provided ID
    @Override
    public Chore findById(Long ID) {
        return toDoList.get(ID);
    }

    // returns all chores in a collection
    @Override
    public Collection<Chore> findAll() {
        return toDoList.values();
    }

    // updates the chore that has the provided ID, used data from the provided chore
    @Override
    public void update(Long id, Chore chore) throws NotFoundException {
        Chore existingChore = toDoList.get(id);
        if (existingChore == null){
            throw new NotFoundException();
        }
        chore.setId(id);
        toDoList.put(id, chore);
    }

    // deleted the chore that has the provided ID
    @Override
    public void delete(Long id) throws NotFoundException {
        Chore existingChore = toDoList.get(id);
        if(existingChore == null){
            throw new NotFoundException();
        }
        toDoList.remove(id);
    }
}
