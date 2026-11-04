package com.payledger.domain.tenant;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(
        replace = AutoConfigureTestDatabase.Replace.NONE
)
class TenantRepositoryTest {

    @Autowired
    private TenantRepository tenantRepository;


    @Test
    void shouldSaveTenant() {

        Tenant tenant = new Tenant(
                "TEST_TENANT_001",
                "Test Tenant"
        );

        Tenant savedTenant = tenantRepository.saveAndFlush(tenant);

        assertNotNull(savedTenant.getId());

        assertEquals(
                "TEST_TENANT_001",
                savedTenant.getTenantCode()
        );

        assertEquals(
                "Test Tenant",
                savedTenant.getName()
        );

        assertEquals("ACTIVE", savedTenant.getStatus());

        assertNotNull(savedTenant.getCreatedAt());
        assertNotNull(savedTenant.getUpdatedAt());
    }


    @Test
    void shouldFindTenantByTenantCode() {

        Tenant tenant = new Tenant(
                "TEST_TENANT_002",
                "Find Test Tenant"
        );

        tenantRepository.saveAndFlush(tenant);

        Optional<Tenant> result =
                tenantRepository.findByTenantCode("TEST_TENANT_002");

        assertTrue(result.isPresent());

        assertEquals(
                "Find Test Tenant",
                result.get().getName()
        );
    }


    @Test
    void shouldReturnTrueWhenTenantCodeExists() {

        Tenant tenant = new Tenant(
                "TEST_TENANT_003",
                "Existence Test Tenant"
        );

        tenantRepository.saveAndFlush(tenant);

        boolean exists =
                tenantRepository.existsByTenantCode("TEST_TENANT_003");

        assertTrue(exists);
    }


    @Test
    void shouldReturnFalseWhenTenantCodeDoesNotExist() {

        boolean exists =
                tenantRepository.existsByTenantCode("NON_EXISTENT_TENANT");

        assertFalse(exists);
    }


    @Test
    void shouldRejectDuplicateTenantCode() {

        Tenant firstTenant = new Tenant(
                "DUPLICATE_TENANT",
                "First Tenant"
        );

        tenantRepository.saveAndFlush(firstTenant);

        Tenant secondTenant = new Tenant(
                "DUPLICATE_TENANT",
                "Second Tenant"
        );

        assertThrows(
                DataIntegrityViolationException.class,
                () -> tenantRepository.saveAndFlush(secondTenant)
        );
    }

}