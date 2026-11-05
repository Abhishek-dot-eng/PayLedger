package com.payledger.tenant.exception;

public class TenantAlreadyExistsException extends RuntimeException {

    public TenantAlreadyExistsException(String tenantCode) {
        super("Tenant code already exists: " + tenantCode);
    }
}