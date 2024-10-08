package edu.bbte.idde.bnim2219.dao;

import edu.bbte.idde.bnim2219.model.Chore;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class ChoreInMemoryDao implements ChoreDao{
    private final ConcurrentHashMap<Long, Chore> toDoList = new ConcurrentHashMap<>();
    private final AtomicLong id = new AtomicLong(0L);

    @Override
    public Long create(Chore chore) {
        Long ID = id.incrementAndGet();
        chore.setId(ID);
        toDoList.put(ID, chore);
        return ID;
    }

    @Override
    public Chore findById(Long ID) {
        return toDoList.get(ID);
    }

    @Override
    public Collection<Chore> findAll() {
        return toDoList.values();
    }

    @Override
    public void update(Long id, Chore chore) throws NotFoundException {
        Chore existingChore = toDoList.get(id);
        if (existingChore == null){
            throw new NotFoundException();
        }
        chore.setId(id);
        toDoList.put(id, chore);
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        Chore existingChore = toDoList.get(id);
        if(existingChore == null){
            throw new NotFoundException();
        }
        toDoList.remove(id);
    }
}
