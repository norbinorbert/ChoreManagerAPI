package edu.bbte.idde.bnim2219.service;

import edu.bbte.idde.bnim2219.dao.ChoreDao;
import edu.bbte.idde.bnim2219.dao.DaoFactory;
import edu.bbte.idde.bnim2219.dao.exceptions.NotFoundException;
import edu.bbte.idde.bnim2219.model.Chore;
import edu.bbte.idde.bnim2219.service.exceptions.NotFoundServiceException;

import java.util.Collection;

// proxy service for now
public class ChoreService {
    private final ChoreDao data = DaoFactory.getInstance().getChoreDao();

    public Long create(Chore chore) {
        return data.create(chore);
    }

    public Chore findById(Long id) throws NotFoundServiceException {
        try {
            return data.findById(id);
        } catch (NotFoundException e) {
            throw new NotFoundServiceException(e);
        }
    }

    public Collection<Chore> findAll() {
        return data.findAll();
    }

    public void update(Long id, Chore chore) throws NotFoundServiceException {
        try {
            data.update(id, chore);
        } catch (NotFoundException e) {
            throw new NotFoundServiceException(e);
        }
    }

    public void delete(Long id) throws NotFoundServiceException {
        try {
            data.delete(id);
        } catch (NotFoundException e) {
            throw new NotFoundServiceException(e);
        }
    }
}
