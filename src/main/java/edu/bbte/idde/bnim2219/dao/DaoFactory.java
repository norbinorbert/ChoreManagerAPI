package edu.bbte.idde.bnim2219.dao;

import edu.bbte.idde.bnim2219.dao.memory.MemoryDaoFactory;

public abstract class DaoFactory {
    private static DaoFactory instance;

    public static synchronized DaoFactory getInstance() {
        if (instance == null) {
            instance = new MemoryDaoFactory();
        }
        return instance;
    }

    public abstract ChoreDao getChoreDao();
}
