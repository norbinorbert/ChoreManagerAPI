package edu.bbte.idde.bnim2219.service;

import edu.bbte.idde.bnim2219.dao.ChoreDao;
import edu.bbte.idde.bnim2219.dao.DaoFactory;
import edu.bbte.idde.bnim2219.dao.exceptions.BackendNotAvailableException;
import edu.bbte.idde.bnim2219.dao.exceptions.NotFoundException;
import edu.bbte.idde.bnim2219.model.Chore;
import edu.bbte.idde.bnim2219.service.exceptions.ServiceNotAvailableException;

import java.util.Collection;

// proxy service for now
public class ChoreService {
    private final ChoreDao data = DaoFactory.getInstance().getChoreDao();

    public Long create(Chore chore) throws ServiceNotAvailableException {
        try {
            return data.create(chore);
        } catch (BackendNotAvailableException e) {
            throw new ServiceNotAvailableException(e);
        }
    }

    public Chore findById(Long id) throws ServiceNotAvailableException {
        try {
            return data.findById(id);
        } catch (BackendNotAvailableException | NotFoundException e) {
            throw new ServiceNotAvailableException(e);
        }
    }

    public Collection<Chore> findAll() throws ServiceNotAvailableException {
        try {
            return data.findAll();
        } catch (BackendNotAvailableException e) {
            throw new ServiceNotAvailableException(e);
        }
    }

    public void update(Long id, Chore chore) throws ServiceNotAvailableException {
        try {
            data.update(id, chore);
        } catch (BackendNotAvailableException | NotFoundException e) {
            throw new ServiceNotAvailableException(e);
        }
    }

    public void delete(Long id) throws ServiceNotAvailableException {
        try {
            data.delete(id);
        } catch (BackendNotAvailableException | NotFoundException e) {
            throw new ServiceNotAvailableException(e);
        }
    }
}
