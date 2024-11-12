package edu.bbte.idde.bnim2219.backend.dao.jdbc;

import edu.bbte.idde.bnim2219.backend.dao.ChoreDao;
import edu.bbte.idde.bnim2219.backend.dao.DaoFactory;

public class JdbcDaoFactory extends DaoFactory {

    @Override
    public ChoreDao getChoreDao() {
        return new ChoreInJdbcDao();
    }
}
