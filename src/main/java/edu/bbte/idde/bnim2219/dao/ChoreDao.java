package edu.bbte.idde.bnim2219.dao;

import edu.bbte.idde.bnim2219.model.Chore;

import java.util.Collection;

public interface ChoreDao {
    Long create(Chore chore);
    Chore findById(Long ID);
    Collection<Chore> findAll();
    void update(Long id, Chore chore) throws NotFoundException;
    void delete(Long id) throws NotFoundException;
}
