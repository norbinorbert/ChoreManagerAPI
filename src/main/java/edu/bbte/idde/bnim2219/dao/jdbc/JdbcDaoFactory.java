package edu.bbte.idde.bnim2219.dao.jdbc;

import edu.bbte.idde.bnim2219.dao.ChoreDao;
import edu.bbte.idde.bnim2219.dao.DaoFactory;

public class JdbcDaoFactory extends DaoFactory {

    @Override
    public ChoreDao getChoreDao() {
        return new ChoreInJdbcDao();
    }
}
