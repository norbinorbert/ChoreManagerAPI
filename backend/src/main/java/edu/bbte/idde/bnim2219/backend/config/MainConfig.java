package edu.bbte.idde.bnim2219.backend.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MainConfig {
    @JsonProperty("dao")
    private String daoClassName;
    @JsonProperty("jdbc")
    private JdbcConfig jdbcConfig = new JdbcConfig();
}
