package com.payledger.tenant;

import com.payledger.domain.tenant.Tenant;
import com.payledger.domain.tenant.TenantRepository;
import com.payledger.tenant.dto.TenantRequest;
import com.payledger.tenant.dto.TenantResponse;
import com.payledger.tenant.exception.TenantAlreadyExistsException;
import com.payledger.tenant.exception.TenantNotFoundException;
import org.springframework.test.util.ReflectionTestUtils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TenantServiceTest {

    @Mock
    private TenantRepository tenantRepository;

    @InjectMocks
    private TenantService tenantService;

    @Test
    void shouldCreateTenantSuccessfully() {

        TenantRequest request =
                new TenantRequest("COMPANY001", "ABC Technologies");

        Tenant savedTenant =
                new Tenant("COMPANY001", "ABC Technologies");

        UUID tenantId = UUID.randomUUID();
        ReflectionTestUtils.setField(savedTenant, "id", tenantId);

        when(tenantRepository.existsByTenantCode("COMPANY001"))
                .thenReturn(false);

        when(tenantRepository.save(any(Tenant.class)))
                .thenReturn(savedTenant);

        TenantResponse response = tenantService.createTenant(request);

        assertNotNull(response);
        assertEquals(tenantId, response.getId());
        assertEquals("COMPANY001", response.getTenantCode());
        assertEquals("ABC Technologies", response.getName());
        assertEquals("ACTIVE", response.getStatus());

        verify(tenantRepository).existsByTenantCode("COMPANY001");
        verify(tenantRepository).save(any(Tenant.class));
    }

    @Test
    void shouldThrowExceptionWhenTenantAlreadyExists() {

        TenantRequest request =
                new TenantRequest("COMPANY001", "ABC Technologies");

        when(tenantRepository.existsByTenantCode("COMPANY001"))
                .thenReturn(true);

        assertThrows(
                TenantAlreadyExistsException.class,
                () -> tenantService.createTenant(request)
        );

        verify(tenantRepository, never()).save(any(Tenant.class));
    }

    @Test
    void shouldGetTenantSuccessfully() {

        Tenant tenant =
                new Tenant("COMPANY001", "ABC Technologies");

        UUID tenantId = UUID.randomUUID();
        ReflectionTestUtils.setField(tenant, "id", tenantId);

        when(tenantRepository.findByTenantCode("COMPANY001"))
                .thenReturn(Optional.of(tenant));

        TenantResponse response =
                tenantService.getTenantByCode("COMPANY001");

        assertNotNull(response);
        assertEquals(tenantId, response.getId());
        assertEquals("COMPANY001", response.getTenantCode());
        assertEquals("ABC Technologies", response.getName());
    }

    @Test
    void shouldThrowExceptionWhenTenantDoesNotExist() {

        when(tenantRepository.findByTenantCode("UNKNOWN"))
                .thenReturn(Optional.empty());

        assertThrows(
                TenantNotFoundException.class,
                () -> tenantService.getTenantByCode("UNKNOWN")
        );
    }
}