package edu.bbte.idde.bnim2219.dao.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.bbte.idde.bnim2219.dao.Dao;
import edu.bbte.idde.bnim2219.model.BaseEntity;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
public abstract class JdbcDao<T extends BaseEntity> implements Dao<T> {
    private transient HikariDataSource dataSource;

    protected JdbcDao() {
        var config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost/idde");
        config.setUsername("root");
        config.setPassword("admin");
        config.setMaximumPoolSize(10);

        dataSource = new HikariDataSource(config);
    }

    protected Connection getConnection() throws SQLException {
        log.info("Requesting a connection");
        return dataSource.getConnection();
    }
}
