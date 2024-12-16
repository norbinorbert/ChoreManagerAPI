package edu.bbte.idde.bnim2219.spring.dao.jdbc;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile({"jdbc", "jpa"})
public class DataSourceFactory {

    @Autowired
    private Config config;

    @Bean
    public DataSource getDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(config.getJdbcUrl());
        dataSource.setDriverClassName(config.getDriver());
        dataSource.setUsername(config.getUsername());
        dataSource.setPassword(config.getPassword());
        dataSource.setMaximumPoolSize(config.getPoolSize());
        return dataSource;
    }
}
