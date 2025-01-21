package edu.bbte.idde.bnim2219.spring.dao.jdbc;

import edu.bbte.idde.bnim2219.spring.dao.Dao;
import edu.bbte.idde.bnim2219.spring.model.BaseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
@Profile("jdbc")
public abstract class JdbcDao<T extends BaseEntity> implements Dao<T> {

    @Autowired
    private DataSource dataSource;

    protected Connection getConnection() throws SQLException {
        log.info("Requesting a connection");
        return dataSource.getConnection();
    }
}
