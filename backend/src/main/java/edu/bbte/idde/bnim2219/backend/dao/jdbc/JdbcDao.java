package edu.bbte.idde.bnim2219.backend.dao.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.bbte.idde.bnim2219.backend.config.ConfigFactory;
import edu.bbte.idde.bnim2219.backend.dao.Dao;
import edu.bbte.idde.bnim2219.backend.model.BaseEntity;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
public abstract class JdbcDao<T extends BaseEntity> implements Dao<T> {
    private final transient HikariDataSource dataSource;

    protected JdbcDao() {
        var config = new HikariConfig();
        config.setJdbcUrl(ConfigFactory.getJdbcConfiguration().getJdbcUrl());
        config.setDriverClassName(ConfigFactory.getJdbcConfiguration().getDriverClassName());
        config.setUsername(ConfigFactory.getJdbcConfiguration().getUsername());
        config.setPassword(ConfigFactory.getJdbcConfiguration().getPassword());
        config.setMaximumPoolSize(ConfigFactory.getJdbcConfiguration().getPoolSize());

        dataSource = new HikariDataSource(config);
    }

    protected Connection getConnection() throws SQLException {
        log.info("Requesting a connection");
        return dataSource.getConnection();
    }
}
