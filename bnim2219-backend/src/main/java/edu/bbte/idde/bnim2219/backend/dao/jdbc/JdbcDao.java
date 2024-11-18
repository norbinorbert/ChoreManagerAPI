package edu.bbte.idde.bnim2219.backend.dao.jdbc;

import edu.bbte.idde.bnim2219.backend.dao.Dao;
import edu.bbte.idde.bnim2219.backend.model.BaseEntity;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
public abstract class JdbcDao<T extends BaseEntity> implements Dao<T> {
    protected Connection getConnection() throws SQLException {
        log.info("Requesting a connection");
        return HikariDataSourceFactory.getDataSource().getConnection();
    }
}
