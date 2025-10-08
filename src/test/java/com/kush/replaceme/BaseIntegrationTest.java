package com.kush.replaceme;

import com.redis.testcontainers.RedisContainer;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.BeforeEach;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@ActiveProfiles("integration-test")
@Sql(scripts = "/test-cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public abstract class BaseIntegrationTest {

    private static final PostgreSQLContainer<?> DB = new PostgreSQLContainer<>("postgres:18.0-alpine")
            .withDatabaseName("replaceme-db")
            .withUsername("postgres")
            .withPassword("password2")
            .withExposedPorts(5432)
            .waitingFor(Wait.forListeningPort());

    private static final RedisContainer REDIS = new RedisContainer("redis:8.2.1")
            .waitingFor(Wait.forListeningPort());

    static {
        DB.start();
        REDIS.start();
    }

    @Autowired
    protected TestRestTemplate restTemplate;

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @DynamicPropertySource
    public static void setDynamicProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", DB::getJdbcUrl);
        registry.add("spring.datasource.username", DB::getUsername);
        registry.add("spring.datasource.password", DB::getPassword);
        registry.add("spring.data.redis.host", REDIS::getHost);
        registry.add("spring.data.redis.port", REDIS::getRedisPort);
    }


    @PreDestroy
    public static void tearDown() {
        DB.stop();
        REDIS.stop();
    }

    @BeforeEach
    public void setUp() throws IOException {
        jdbcTemplate.execute(Files.readString(Paths.get("src/test/resources/test-init.sql")));
    }

    @SuppressWarnings("PMD.LooseCoupling")
    public static <T> HttpEntity<T> buildRequest(T body) {
        HttpHeaders headers = new HttpHeaders();
        if (body != null) {
            headers.setContentType(MediaType.APPLICATION_JSON);
        }

        return new HttpEntity<>(body, headers);
    }
}
