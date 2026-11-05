package com.payledger.tenant;

import com.payledger.tenant.dto.TenantRequest;
import com.payledger.tenant.dto.TenantResponse;
import com.payledger.domain.tenant.TenantRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TenantServiceIntegrationTest {

    @Autowired
    private TenantService tenantService;

    @Autowired
    private TenantRepository tenantRepository;

    @Test
    void shouldCreateAndRetrieveTenantSuccessfully() {

        // Arrange: Prepare request
        TenantRequest request =
                new TenantRequest("INTEGRATION001", "Integration Technologies");

        // Act: Create tenant
        TenantResponse createdTenant =
                tenantService.createTenant(request);

        // Assert: Verify creation
        assertNotNull(createdTenant.getId());
        assertEquals("INTEGRATION001", createdTenant.getTenantCode());
        assertEquals("Integration Technologies", createdTenant.getName());
        assertEquals("ACTIVE", createdTenant.getStatus());

        // Act: Retrieve tenant
        TenantResponse retrievedTenant =
                tenantService.getTenantByCode("INTEGRATION001");

        // Assert: Verify retrieval
        assertEquals(createdTenant.getId(), retrievedTenant.getId());
        assertEquals("INTEGRATION001", retrievedTenant.getTenantCode());

        // Verify database record
        assertTrue(tenantRepository.existsByTenantCode("INTEGRATION001"));
    }

    @Test
    void shouldThrowExceptionForDuplicateTenant() {

        TenantRequest request =
                new TenantRequest("DUPLICATE001", "Duplicate Technologies");

        // First registration should succeed
        tenantService.createTenant(request);

        // Second registration should fail
        assertThrows(
                com.payledger.tenant.exception.TenantAlreadyExistsException.class,
                () -> tenantService.createTenant(request)
        );
    }
}