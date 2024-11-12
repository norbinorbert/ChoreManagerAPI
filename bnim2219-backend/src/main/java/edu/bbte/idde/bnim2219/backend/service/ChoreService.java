package edu.bbte.idde.bnim2219.backend.service;

import edu.bbte.idde.bnim2219.backend.dao.ChoreDao;
import edu.bbte.idde.bnim2219.backend.dao.DaoFactory;
import edu.bbte.idde.bnim2219.backend.dao.exceptions.BackendConnectionException;
import edu.bbte.idde.bnim2219.backend.dao.exceptions.ChoreNotFoundException;
import edu.bbte.idde.bnim2219.backend.model.Chore;
import edu.bbte.idde.bnim2219.backend.service.exceptions.ChoreProcessingException;
import edu.bbte.idde.bnim2219.backend.service.exceptions.UnexpectedBackendException;

import java.io.Serializable;
import java.util.Collection;

// proxy service for now
public class ChoreService implements Serializable {
    private final transient ChoreDao data = DaoFactory.getInstance().getChoreDao();

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
