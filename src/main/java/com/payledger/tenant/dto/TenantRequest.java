package com.payledger.tenant.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TenantRequest {


    @NotBlank(message = "Tenant code is required")
    @Size(max = 50, message = "Tenant code cannot exceed 50 characters")
    private String tenantCode;

    @NotBlank(message = "Tenant name is required")
    @Size(max = 150, message = "Tenant name cannot exceed 150 characters")
    private String name;

    public TenantRequest() {
    }

    public TenantRequest(String tenantCode, String name) {
        this.tenantCode = tenantCode;
        this.name = name;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
