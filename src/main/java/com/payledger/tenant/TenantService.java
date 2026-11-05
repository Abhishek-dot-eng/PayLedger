package com.payledger.tenant;

import com.payledger.domain.tenant.Tenant;
import com.payledger.domain.tenant.TenantRepository;
import com.payledger.tenant.dto.TenantRequest;
import com.payledger.tenant.dto.TenantResponse;
import com.payledger.tenant.exception.TenantAlreadyExistsException;
import com.payledger.tenant.exception.TenantNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class TenantService {

    private final TenantRepository tenantRepository;

    public TenantService(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    @Transactional
    public TenantResponse createTenant(TenantRequest request) {

        if (tenantRepository.existsByTenantCode(request.getTenantCode())) {
            throw new TenantAlreadyExistsException(request.getTenantCode());
        }

        Tenant tenant = new Tenant(
                request.getTenantCode(),
                request.getName()
        );

        Tenant savedTenant = tenantRepository.save(tenant);

        return mapToResponse(savedTenant);
    }

    @Transactional(readOnly = true)
    public TenantResponse getTenantByCode(String tenantCode) {

        Tenant tenant = tenantRepository.findByTenantCode(tenantCode)
                .orElseThrow(() -> new TenantNotFoundException(tenantCode));

        return mapToResponse(tenant);
    }

    private TenantResponse mapToResponse(Tenant tenant) {

        return new TenantResponse(
                tenant.getId(),
                tenant.getTenantCode(),
                tenant.getName(),
                tenant.getStatus(),
                tenant.getCreatedAt(),
                tenant.getUpdatedAt()
        );
    }
}