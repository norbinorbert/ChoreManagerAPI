package edu.bbte.idde.bnim2219.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;

@Slf4j
@SpringBootApplication(exclude =
        {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class})
public class SpringWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringWebApplication.class, args);
    }
}
