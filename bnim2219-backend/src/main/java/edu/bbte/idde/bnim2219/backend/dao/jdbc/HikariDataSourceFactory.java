package edu.bbte.idde.bnim2219.backend.dao.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.bbte.idde.bnim2219.backend.config.ConfigFactory;

public class HikariDataSourceFactory {
    private static HikariDataSource dataSource;

    public static synchronized HikariDataSource getDataSource() {
        if (dataSource == null) {
            var config = new HikariConfig();
            config.setJdbcUrl(ConfigFactory.getJdbcConfiguration().getJdbcUrl());
            config.setDriverClassName(ConfigFactory.getJdbcConfiguration().getDriverClassName());
            config.setUsername(ConfigFactory.getJdbcConfiguration().getUsername());
            config.setPassword(ConfigFactory.getJdbcConfiguration().getPassword());
            config.setMaximumPoolSize(ConfigFactory.getJdbcConfiguration().getPoolSize());

            dataSource = new HikariDataSource(config);
        }
        return dataSource;
    }
}
