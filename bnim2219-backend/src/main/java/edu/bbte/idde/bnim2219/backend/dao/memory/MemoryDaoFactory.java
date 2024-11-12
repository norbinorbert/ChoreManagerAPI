package edu.bbte.idde.bnim2219.backend.dao.memory;

import edu.bbte.idde.bnim2219.backend.dao.DaoFactory;
import edu.bbte.idde.bnim2219.backend.dao.ChoreDao;

public class MemoryDaoFactory extends DaoFactory {

    @Override
    public ChoreDao getChoreDao() {
        return new ChoreInMemoryDao();
    }
}
