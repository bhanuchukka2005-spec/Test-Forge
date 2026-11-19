package com.testforge;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class TestForgeApplicationTest {
    @Test void applicationContextLoads() {
        // Starting the context validates Spring wiring, JPA mappings and demo-data seeding.
    }
}
