package edu.bbte.idde.bnim2219.spring.dao.jpa;

import edu.bbte.idde.bnim2219.spring.dao.ChoreDao;
import edu.bbte.idde.bnim2219.spring.dao.exceptions.ChoreNotFoundException;
import edu.bbte.idde.bnim2219.spring.model.Chore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Profile("jpa")
@Repository
public class ChoreJpaDao implements ChoreDao {
    @Autowired
    private ChoreJpa choreJpa;

    @Override
    public Collection<Chore> findChoresByDone(Boolean done) {
        return choreJpa.findByDone(done);
    }

    @Override
    public Long create(Chore entity) {
        Chore chore = choreJpa.saveAndFlush(entity);
        return chore.getId();
    }

    @Override
    public Chore findById(Long id) throws ChoreNotFoundException {
        Optional<Chore> chore = choreJpa.findById(id);
        if (chore.isEmpty()) {
            throw new ChoreNotFoundException();
        }
        return chore.get();
    }

    @Override
    public Collection<Chore> findAll() {
        return choreJpa.findAll();
    }

    @Override
    public void update(Long id, Chore entity) throws ChoreNotFoundException {
        Optional<Chore> chore = choreJpa.findById(id);
        if (chore.isEmpty()) {
            throw new ChoreNotFoundException();
        }
        entity.setId(id);
        entity.setSubtasks(chore.get().getSubtasks());
        choreJpa.save(entity);
    }

    @Override
    public void delete(Long id) {
        choreJpa.deleteById(id);
    }
}
