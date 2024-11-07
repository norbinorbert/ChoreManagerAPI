package edu.bbte.idde.bnim2219.backend.dao;

import edu.bbte.idde.bnim2219.backend.config.ConfigFactory;
import edu.bbte.idde.bnim2219.backend.dao.jdbc.JdbcDaoFactory;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;

@Slf4j
public abstract class DaoFactory {
    private static DaoFactory instance;

    public static synchronized DaoFactory getInstance() {
        try {
            if (instance == null) {
                instance = (DaoFactory) Class.forName(ConfigFactory.getMainConfiguration().getDaoClassName())
                        .getDeclaredConstructor()
                        .newInstance();
            }
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException
                 | IllegalAccessException | InvocationTargetException e) {
            log.error("Error loading class from config file. Please check if path is correctly configured", e);
            instance = new JdbcDaoFactory();
        }
        return instance;
    }

    public abstract ChoreDao getChoreDao();
}
