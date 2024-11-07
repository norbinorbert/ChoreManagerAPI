package edu.bbte.idde.bnim2219.backend.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class ConfigFactory {
    private static final String CONFIG_FILE_PREFIX = "config";

    @Getter
    private static MainConfig mainConfiguration = new MainConfig();

    static {
        StringBuilder sb = new StringBuilder();
        sb.append('/').append(CONFIG_FILE_PREFIX);

        String profile = System.getenv("IDDE");
        log.info("Determined profile: {}", profile);
        if (profile != null && !profile.isBlank()) {
            sb.append('-').append(profile);
        }
        sb.append(".json");

        ObjectMapper objectMapper = new ObjectMapper();

        try (InputStream inputStream = ConfigFactory.class.getResourceAsStream(sb.toString())) {
            mainConfiguration = objectMapper.readValue(inputStream, MainConfig.class);
            log.info("Read following configuration: {}", mainConfiguration);
        } catch (IOException e) {
            log.error("Error loading configuration", e);
        }
    }

    public static JdbcConfig getJdbcConfiguration() {
        return mainConfiguration.getJdbcConfig();
    }
}
