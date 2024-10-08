package edu.bbte.idde.bnim2219.service;

import edu.bbte.idde.bnim2219.dao.ChoreInMemoryDao;
import edu.bbte.idde.bnim2219.dao.NotFoundException;
import edu.bbte.idde.bnim2219.model.Chore;

import java.util.Collection;

public class ChoreService {
    private final ChoreInMemoryDao data = new ChoreInMemoryDao();

    public Long create(Chore chore) {
        return data.create(chore);
    }

    public Chore findById(Long ID) {
        return data.findById(ID);
    }

    public Collection<Chore> findAll() {
        return data.findAll();
    }

    public void update(Long id, Chore chore) throws NotFoundServiceException {
        try {
            data.update(id, chore);
        }
        catch (NotFoundException e){
            throw new NotFoundServiceException();
        }
    }

    public void delete(Long id) throws NotFoundServiceException {
        try {
            data.delete(id);
        }
        catch (NotFoundException e){
            throw new NotFoundServiceException();
        }
    }
}
