package com.payledger.tenant.dto;

import java.time.Instant;
import java.util.UUID;

public class TenantResponse {

    private UUID id;
    private String tenantCode;
    private String name;
    private String status;
    private Instant createdAt;
    private Instant updatedAt;

    public TenantResponse() {
    }

    public TenantResponse(
            UUID id,
            String tenantCode,
            String name,
            String status,
            Instant createdAt,
            Instant updatedAt) {

        this.id = id;
        this.tenantCode = tenantCode;
        this.name = name;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

}
