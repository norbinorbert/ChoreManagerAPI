package edu.bbte.idde.bnim2219.dao.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.bbte.idde.bnim2219.dao.Dao;
import edu.bbte.idde.bnim2219.model.BaseEntity;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class JdbcDao<T extends BaseEntity> implements Dao<T> {
    private final HikariDataSource dataSource;

    protected JdbcDao() {
        var config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/idde");
        config.setUsername("root");
        config.setPassword("admin");
        config.setDriverClassName("com.mysql.jdbc.Driver");

        dataSource = new HikariDataSource(config);
    }

    // TODO: error handling (either here or in child)
    protected Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
