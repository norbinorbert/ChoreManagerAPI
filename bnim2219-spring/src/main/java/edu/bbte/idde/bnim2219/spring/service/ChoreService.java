package edu.bbte.idde.bnim2219.spring.service;

import edu.bbte.idde.bnim2219.spring.dao.ChoreDao;
import edu.bbte.idde.bnim2219.spring.dao.exceptions.BackendConnectionException;
import edu.bbte.idde.bnim2219.spring.dao.exceptions.ChoreNotFoundException;
import edu.bbte.idde.bnim2219.spring.model.Chore;
import edu.bbte.idde.bnim2219.spring.service.exceptions.ChoreProcessingException;
import edu.bbte.idde.bnim2219.spring.service.exceptions.UnexpectedBackendException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;

@Service
public class ChoreService implements Serializable {
    @Autowired
    private ChoreDao data;

    public Long create(Chore chore) throws UnexpectedBackendException {
        try {
            return data.create(chore);
        } catch (BackendConnectionException e) {
            throw new UnexpectedBackendException(e);
        }
    }

    public Chore findById(Long id) throws ChoreProcessingException, UnexpectedBackendException {
        try {
            return data.findById(id);
        } catch (BackendConnectionException e) {
            throw new UnexpectedBackendException(e);
        } catch (ChoreNotFoundException e) {
            throw new ChoreProcessingException(e);
        }
    }

    public Collection<Chore> findAll() throws UnexpectedBackendException {
        try {
            return data.findAll();
        } catch (BackendConnectionException e) {
            throw new UnexpectedBackendException(e);
        }
    }

    public void update(Long id, Chore chore) throws ChoreProcessingException, UnexpectedBackendException {
        try {
            data.update(id, chore);
        } catch (BackendConnectionException e) {
            throw new UnexpectedBackendException(e);
        } catch (ChoreNotFoundException e) {
            throw new ChoreProcessingException(e);
        }
    }

    public void delete(Long id) throws ChoreProcessingException, UnexpectedBackendException {
        try {
            data.delete(id);
        } catch (BackendConnectionException e) {
            throw new UnexpectedBackendException(e);
        } catch (ChoreNotFoundException e) {
            throw new ChoreProcessingException(e);
        }
    }
}
