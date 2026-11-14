
# Feature 06 – Patient CRUD APIs

## Overview

This feature implements REST APIs for managing patients in the Hospital Management System.

It connects the controller, DTO, mapper, service, repository, and database layers.

## Objectives

- Implement PatientRequest and PatientResponse DTOs.
- Add request validation.
- Implement PatientMapper.
- Create PatientController.
- Expose patient CRUD REST endpoints.
- Return appropriate HTTP status codes.
- Test APIs using Postman.

## Package Structure

```text
com.asher.hospitalmanagement/
│
├── controller/
│   └── PatientController.java
│
├── dto/
│   ├── request/
│   │   └── PatientRequest.java
│   │
│   └── response/
│       ├── PatientResponse.java
│       ├── ApiErrorResponse.java
│       └── ValidationErrorResponse.java
│
├── mapper/
│   └── PatientMapper.java
│
└── security/
    └── SecurityConfig.java
```

## 1. PatientRequest

Represents incoming patient data.

Validation includes:

- Required name, age, gender, disease, address, and phone number.
- Minimum age of 1.
- Maximum lengths for string fields.
- Phone number format validation.

## 2. PatientResponse

Represents patient data returned to API clients.

JPA entities are not directly exposed through REST endpoints.

## 3. PatientMapper

Converts:

- PatientRequest to Patient entity.
- Patient entity to PatientResponse.

## 4. PatientController

Exposes the following REST endpoints:

| Method | Endpoint | Description | Status |
|---|---|---|---|
| POST | /api/patients | Create patient | 201 |
| GET | /api/patients | Get all patients | 200 |
| GET | /api/patients/{id} | Get patient by ID | 200 |
| PUT | /api/patients/{id} | Update patient | 200 |
| DELETE | /api/patients/{id} | Delete patient | 204 |

## 5. Request Flow

Postman → PatientController → PatientMapper → PatientService → PatientRepository → MySQL

The response follows the reverse direction and uses PatientResponse.

## 6. Validation and Exception Handling

The controller uses @Valid to validate incoming requests.

Validation failures are handled by GlobalExceptionHandler.

Missing patients result in ResourceNotFoundException and HTTP 404.

## 7. Security Configuration

A temporary SecurityFilterChain permits all requests so the patient APIs can be tested without authentication.

This configuration is for development only and will be replaced with JWT-based authentication in Feature 07.

## 8. Testing

Patient endpoints are tested using Postman.

Validation failures and missing resources are also verified.

## 9. Dependencies

No additional Maven dependencies are required.

Spring Web, Spring Data JPA, Spring Validation, and Spring Security are already included.

## Status

Patient CRUD REST APIs implemented and ready for testing.
