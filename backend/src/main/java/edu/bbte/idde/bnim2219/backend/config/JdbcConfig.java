package edu.bbte.idde.bnim2219.backend.config;

import lombok.Data;

@Data
public class JdbcConfig {
    private String jdbcUrl;
    private String driverClassName;
    private String username;
    private String password;
    private Integer poolSize;
}
