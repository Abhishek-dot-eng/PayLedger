package com.payledger;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DatabaseConnectivityTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void shouldConnectToPostgreSQL() {

        String databaseName = jdbcTemplate.queryForObject(
                "SELECT current_database()",
                String.class
        );

        String username = jdbcTemplate.queryForObject(
                "SELECT current_user",
                String.class
        );

        assertEquals("payledger_db", databaseName);
        assertEquals("payledger_user", username);

        System.out.println("Database: " + databaseName);
        System.out.println("Connected User: " + username);
        System.out.println("PostgreSQL connection successful!");
    }
}