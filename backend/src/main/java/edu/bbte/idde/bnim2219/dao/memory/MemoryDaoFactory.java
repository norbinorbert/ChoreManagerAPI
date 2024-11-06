package edu.bbte.idde.bnim2219.dao.memory;

import edu.bbte.idde.bnim2219.dao.ChoreDao;
import edu.bbte.idde.bnim2219.dao.DaoFactory;

public class MemoryDaoFactory extends DaoFactory {

    @Override
    public ChoreDao getChoreDao() {
        return new ChoreInMemoryDao();
    }
}
