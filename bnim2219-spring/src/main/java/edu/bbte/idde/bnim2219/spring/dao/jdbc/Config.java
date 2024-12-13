package edu.bbte.idde.bnim2219.spring.dao.jdbc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@Profile("jdbc")
public class Config {
    @Value("${jdbc.url}")
    private String jdbcUrl;
    @Value("${jdbc.username:root}")
    private String username;
    @Value("${jdbc.password:admin}")
    private String password;
    @Value("${jdbc.driver}")
    private String driver;
    @Value("${jdbc.poolSize:10}")
    private Integer poolSize;
}
