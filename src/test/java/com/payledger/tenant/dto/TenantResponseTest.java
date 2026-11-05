package com.payledger.tenant.dto;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TenantResponseTest {

    @Test
    void shouldCreateTenantResponseSuccessfully() {

        UUID id = UUID.randomUUID();

        Instant now = Instant.now();

        TenantResponse response = new TenantResponse(
                id,
                "COMPANY001",
                "ABC Technologies",
                "ACTIVE",
                now,
                now
        );

        assertEquals(id, response.getId());
        assertEquals("COMPANY001", response.getTenantCode());
        assertEquals("ABC Technologies", response.getName());
        assertEquals("ACTIVE", response.getStatus());
        assertEquals(now, response.getCreatedAt());
        assertEquals(now, response.getUpdatedAt());
    }
}