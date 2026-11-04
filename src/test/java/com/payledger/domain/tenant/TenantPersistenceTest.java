package com.payledger.domain.tenant;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TenantPersistenceTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    void shouldSaveAndRetrieveTenant() {

        // Arrange
        Tenant tenant = new Tenant(
                "PAYLEDGER_TEST_001",
                "PayLedger Test Organization"
        );

        // Act: Save tenant
        entityManager.persist(tenant);
        entityManager.flush();

        UUID generatedId = tenant.getId();

        // Clear persistence context to force database retrieval
        entityManager.clear();

        // Retrieve tenant from database
        Tenant savedTenant = entityManager.find(
                Tenant.class,
                generatedId
        );

        // Assert
        assertNotNull(savedTenant);
        assertEquals(generatedId, savedTenant.getId());
        assertEquals("PAYLEDGER_TEST_001", savedTenant.getTenantCode());
        assertEquals("PayLedger Test Organization", savedTenant.getName());
        assertEquals("ACTIVE", savedTenant.getStatus());

        assertNotNull(savedTenant.getCreatedAt());
        assertNotNull(savedTenant.getUpdatedAt());

        System.out.println("Tenant persistence successful!");
        System.out.println("Generated Tenant ID: " + savedTenant.getId());
    }
}