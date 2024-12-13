package edu.bbte.idde.bnim2219.spring.service;

import edu.bbte.idde.bnim2219.spring.dao.ChoreDao;
import edu.bbte.idde.bnim2219.spring.dao.exceptions.BackendConnectionException;
import edu.bbte.idde.bnim2219.spring.dao.exceptions.ChoreNotFoundException;
import edu.bbte.idde.bnim2219.spring.model.Chore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;

@Service
public class ChoreService implements Serializable {
    @Autowired
    private ChoreDao data;

    public Long create(Chore chore) throws BackendConnectionException {
        return data.create(chore);
    }

    public Chore findById(Long id) throws BackendConnectionException, ChoreNotFoundException {
        return data.findById(id);
    }

    public Collection<Chore> findAll() throws BackendConnectionException {
        return data.findAll();
    }

    public void update(Long id, Chore chore) throws BackendConnectionException, ChoreNotFoundException {
        data.update(id, chore);
    }

    public void delete(Long id) throws BackendConnectionException, ChoreNotFoundException {
        data.delete(id);
    }

    public Collection<Chore> findChoresByDone(Boolean done) throws BackendConnectionException {
        return data.findChoresByDone(done);
    }
}
