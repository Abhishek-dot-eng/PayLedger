package com.payledger.tenant.dto;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TenantRequestValidationTest {

    private static ValidatorFactory factory;
    private static Validator validator;

    @BeforeAll
    static void setUp() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @AfterAll
    static void tearDown() {
        factory.close();
    }

    @Test
    void shouldPassValidationForValidTenantRequest() {

        TenantRequest request =
                new TenantRequest("COMPANY001", "ABC Technologies");

        var violations = validator.validate(request);

        assertTrue(violations.isEmpty());
    }

    @Test
    void shouldFailWhenTenantCodeIsBlank() {

        TenantRequest request =
                new TenantRequest("", "ABC Technologies");

        var violations = validator.validate(request);

        assertFalse(violations.isEmpty());
    }

    @Test
    void shouldFailWhenTenantNameIsBlank() {

        TenantRequest request =
                new TenantRequest("COMPANY001", "   ");

        var violations = validator.validate(request);

        assertFalse(violations.isEmpty());
    }

    @Test
    void shouldFailWhenTenantCodeExceedsMaximumLength() {

        TenantRequest request =
                new TenantRequest("A".repeat(51), "ABC Technologies");

        var violations = validator.validate(request);

        assertFalse(violations.isEmpty());
    }

    @Test
    void shouldFailWhenTenantNameExceedsMaximumLength() {

        TenantRequest request =
                new TenantRequest("COMPANY001", "A".repeat(151));

        var violations = validator.validate(request);

        assertFalse(violations.isEmpty());
    }
}