
# Feature 04 – Service Layer

## Overview

This feature implements the service layer of the Hospital Management System.

The service layer contains business logic and acts as an intermediary between controllers and repositories.

## Objectives

- Understand the Service Layer.
- Create service interfaces.
- Create separate service implementation classes.
- Implement constructor-based dependency injection.
- Implement Patient CRUD business operations.
- Implement User persistence and lookup operations.
- Introduce application logging.

## Package Structure

```text
service/
├── interfaces/
│   ├── PatientService.java
│   └── UserService.java
│
└── implementations/
    ├── PatientServiceImpl.java
    └── UserServiceImpl.java
```

## 1. PatientService

Defines the following operations:

| Method | Responsibility |
|---|---|
| createPatient() | Creates a patient |
| getAllPatients() | Retrieves all patients |
| getPatientById() | Retrieves a patient by ID |
| updatePatient() | Updates an existing patient |
| deletePatient() | Deletes a patient |

## 2. PatientServiceImpl

Implements PatientService.

Responsibilities:

- Uses PatientRepository for database operations.
- Checks whether a patient exists before updating or deleting.
- Updates existing patient fields.
- Logs important operations.

## 3. UserService

Defines the following operations:

| Method | Responsibility |
|---|---|
| saveUser() | Saves a user |
| findByEmail() | Finds a user by email |
| existsByEmail() | Checks whether an email exists |

## 4. UserServiceImpl

Implements UserService using UserRepository.

These operations will be used by the authentication module during registration and login.

Password encoding will be handled by the authentication service in a later feature.

## 5. Dependency Injection

Constructor injection is used to provide repository dependencies to service implementations.

Spring automatically provides the repository beans created by Spring Data JPA.

The service implementation classes are registered as Spring beans using @Service.

## 6. Application Architecture

Controller → Service Interface → Service Implementation → Repository → MySQL

Each layer has a separate responsibility.

## 7. Logging

SLF4J is used to log important service operations.

Sensitive information such as passwords is never logged.

## 8. Exception Handling

NoSuchElementException is currently used when a requested patient does not exist.

Centralized exception handling and custom exceptions will be implemented in the next feature.

## 9. Testing and Verification

Run:

`mvn clean package`

Start the application:

`mvn spring-boot:run`

Verify that Spring successfully creates the service beans and injects the repository dependencies.

No HTTP endpoints are introduced in this feature.

## 10. Dependencies

No additional Maven dependencies are required.

The project already includes Spring Data JPA and Spring Boot Starter.

## Status

Patient and User service interfaces and implementations created.
