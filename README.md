# PayLedger

A Multi-Tenant FinTech Split-Payment and Double-Entry Ledger System built using Java and Spring Boot.

## Overview

PayLedger is a backend engineering project designed to demonstrate how financial transactions can be managed reliably using double-entry bookkeeping principles.

The project aims to explore important backend engineering concepts such as:

* Double-entry accounting and ledger management
* Multi-tenant architecture
* Secure transaction processing
* Database consistency and reliability
* Caching and infrastructure management

Currently, the project is under active development.

## Technology Stack

| Technology        | Purpose                         |
| ----------------- | ------------------------------- |
| Java 21           | Backend programming language    |
| Spring Boot 4.1.2 | Backend framework               |
| Maven             | Build and dependency management |
| Spring Data JPA   | Database persistence            |
| Spring Data Redis | Redis integration               |
| PostgreSQL 17     | Relational database             |
| Flyway            | Database schema migrations      |
| Redis 8           | Caching and fast data access    |
| Docker Compose    | Infrastructure management       |

## Current Architecture

```text
             PayLedger
                 |
          Spring Boot App
             Port 8080
                 |
       +---------+---------+
       |                   |
   PostgreSQL             Redis
   Port 5433             Port 6379
       |                   |
   Persistent            Persistent
    Volume                Volume
```

The Spring Boot application connects to PostgreSQL for relational data persistence and Redis for caching-related operations.

Docker Compose manages the PostgreSQL and Redis containers.

## Prerequisites

Install the following before running the project:

* Java 21
* Docker Desktop
* Git

Maven is included through the Maven Wrapper.

## Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/Abhishek-dot-eng/PayLedger.git
```

```bash
cd PayLedger
```

### 2. Configure environment variables

Create your local environment file from the example:

**Windows PowerShell:**

```powershell
Copy-Item .env.example .env
```

Open `.env` and replace the placeholder password with your local PostgreSQL password.

Never commit your actual `.env` file to Git.

### 3. Start infrastructure services

Run:

```bash
docker compose up -d
```

This starts:

* PostgreSQL on port `5433`
* Redis on port `6379`

Check container status:

```bash
docker compose ps
```

### 4. Verify PostgreSQL

```bash
docker compose exec postgres psql -U payledger_user -d payledger_db -c "SELECT current_database();"
```

### 5. Verify Redis

```bash
docker compose exec redis redis-cli PING
```

Expected response:

```text
PONG
```

### 6. Run application tests

**Windows PowerShell:**

```powershell
.\mvnw.cmd clean test
```

### 7. Start PayLedger

```powershell
.\mvnw.cmd spring-boot:run
```

The application starts on:

```text
http://localhost:8080
```

## Environment Configuration

The application uses environment-based configuration to avoid hardcoding sensitive credentials.

| Variable    | Description         | Default                     |
| ----------- | ------------------- | --------------------------- |
| DB_PASSWORD | PostgreSQL password | Required                    |
| DB_URL      | PostgreSQL JDBC URL | localhost:5433/payledger_db |
| DB_USERNAME | PostgreSQL username | payledger_user              |
| REDIS_HOST  | Redis hostname      | localhost                   |
| REDIS_PORT  | Redis port          | 6379                        |

The `.env` file is excluded from Git. The `.env.example` file provides a safe configuration template.

## Database Management

Flyway is configured to manage database schema migrations.

Migration files will be maintained in:

```text
src/main/resources/db/migration/
```

Hibernate schema generation is configured with:

```properties
spring.jpa.hibernate.ddl-auto=validate
```

This ensures Hibernate validates the database schema rather than automatically creating or modifying it.

## Project Structure

```text
payledger/
├── src/
│   ├── main/
│   │   ├── java/com/payledger/
│   │   │   ├── domain/
│   │   │   │   └── tenant/
│   │   │   │       ├── Tenant.java
│   │   │   │       └── TenantRepository.java
│   │   │   ├── infrastructure/
│   │   │   │   └── redis/
│   │   │   ├── tenant/
│   │   │   │   ├── dto/
│   │   │   │   │   ├── TenantRequest.java
│   │   │   │   │   └── TenantResponse.java
│   │   │   │   ├── exception/
│   │   │   │   │   ├── TenantAlreadyExistsException.java
│   │   │   │   │   └── TenantNotFoundException.java
│   │   │   │   └── TenantService.java
│   │   │   └── PayledgerApplication.java
│   │   └── resources/
│   │       ├── db/migration/
│   │       │   └── V1__create_tenants_table.sql
│   │       └── application.properties
│   └── test/
│       └── java/com/payledger/
│           ├── DatabaseConnectivityTest.java
│           ├── domain/tenant/
│           │   ├── TenantPersistenceTest.java
│           │   └── TenantRepositoryTest.java
│           ├── infrastructure/redis/
│           │   └── RedisServiceTest.java
│           └── tenant/
│               ├── dto/
│               │   ├── TenantRequestValidationTest.java
│               │   └── TenantResponseTest.java
│               ├── TenantServiceTest.java
│               └── TenantServiceIntegrationTest.java
├── .env.example
├── .gitignore
├── compose.yml
├── pom.xml
└── README.md
```

## Development Progress

| Day | Task | Status |
|---|---|---|
| Day 1 | Maven Project Initialization | Completed |
| Day 2 | Docker, PostgreSQL & Redis Setup | Completed |
| Day 3 | Tenant Entity, Flyway Migration & Repository | Completed |
| Day 4 | Tenant Service Layer & Business Logic | Completed |

## Current Project Status

**Infrastructure, tenant persistence, and tenant business logic layers completed.**

### Implemented Features

- PostgreSQL and Redis configured using Docker Compose.
- Environment-based application configuration.
- Database connectivity verified.
- Redis integration implemented and tested.
- Flyway database migration configured.
- Tenant entity created with UUID-based identification.
- Tenant status and audit timestamps configured.
- Tenant repository implemented using Spring Data JPA.
- Repository methods for tenant lookup and existence checks.
- Database-level unique constraint for tenant codes.
- Tenant request and response DTOs implemented.
- Jakarta Bean Validation for tenant request fields.
- Custom exceptions for duplicate and missing tenants.
- Tenant service layer implemented with business logic.
- Constructor-based dependency injection.
- Transaction management using Spring's `@Transactional`.
- Read-only transaction configuration for tenant retrieval.
- Mockito-based unit tests for tenant service logic.
- PostgreSQL-backed integration tests for tenant service operations.

### Testing Results

Full regression test suite:

| Metric | Result |
|---|---:|
| Total Tests | 22 |
| Passed | 22 |
| Failed | 0 |
| Errors | 0 |
| Skipped | 0 |

**Build Status: SUCCESS**

Run the complete test suite using:

```powershell
.\mvnw.cmd "-Duser.timezone=UTC" clean test
```

The project is under active development. Upcoming work will expand the domain model and implement the core financial ledger functionality.


## Author

**Abhishek Pal**

GitHub: [Abhishek-dot-eng](https://github.com/Abhishek-dot-eng)
