package com.payledger.tenant.exception;

public class TenantNotFoundException extends RuntimeException {

    public TenantNotFoundException(String tenantCode) {
        super("Tenant not found with code: " + tenantCode);
    }
}