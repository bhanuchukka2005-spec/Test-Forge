package com.testforge.api;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Map;

class HealthControllerTest {
    @Test void healthEndpointPayloadIsStable() {
        Map<String, String> payload = new HealthController().health();
        assertEquals("UP", payload.get("status"));
        assertEquals("TestForge Student Portal", payload.get("application"));
    }
}
