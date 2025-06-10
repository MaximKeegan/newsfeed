package com.example.newsfeed;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;


@SpringBootTest
@Testcontainers
//@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public abstract class IntegrationTestBase {

    @Container
    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            DockerImageName.parse("postgres:17")
    )
            .withDatabaseName("testdb")
            .withUsername("testuser")
            .withPassword("testpass")
            .withStartupTimeoutSeconds(60)
            .withReuse(false);

    @BeforeAll
    static void beforeAll() {
        // Устанавливаем сокет Colima
//        System.setProperty("testcontainers.kubernetes.client.config.ignore", "true");
//        System.setProperty("docker.host", "unix:///Users/maximkeegan/.colima/default/docker.sock");
//        System.setProperty("testcontainers.docker.socket.path", "/Users/maximkeegan/.colima/default/docker.sock");
        System.out.println("Starting PostgreSQL container...");
        postgres.start();
        System.out.println("PostgreSQL container started with JDBC URL: " + postgres.getJdbcUrl());
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", () -> postgres.getJdbcUrl() + "?loggerLevel=ON");
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }
}