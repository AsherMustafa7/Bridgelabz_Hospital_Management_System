package com.asher.hospitalmanagement.controller;

import com.asher.hospitalmanagement.dto.request.PatientRequest;
import com.asher.hospitalmanagement.dto.response.PatientResponse;
import com.asher.hospitalmanagement.entity.Patient;
import com.asher.hospitalmanagement.mapper.PatientMapper;
import com.asher.hospitalmanagement.service.interfaces.PatientService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller responsible for Patient API endpoints.
 *
 * Handles HTTP requests and delegates business operations
 * to PatientService.
 */
@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;
    private final PatientMapper patientMapper;

    /**
     * Constructor injection.
     *
     * Spring provides the PatientService implementation
     * and PatientMapper bean automatically.
     */
    public PatientController(
            PatientService patientService,
            PatientMapper patientMapper
    ) {
        this.patientService = patientService;
        this.patientMapper = patientMapper;
    }

    /**
     * Creates a new patient.
     *
     * POST /api/patients
     */
    @PostMapping
    public ResponseEntity<PatientResponse> createPatient(
            @Valid @RequestBody PatientRequest request
    ) {

        Patient patient = patientMapper.toEntity(request);

        Patient savedPatient = patientService.createPatient(patient);

        PatientResponse response =
                patientMapper.toResponse(savedPatient);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    /**
     * Retrieves all patients.
     *
     * GET /api/patients
     */
    @GetMapping
    public ResponseEntity<List<PatientResponse>> getAllPatients() {

        List<PatientResponse> patients =
                patientService.getAllPatients()
                        .stream()
                        .map(patientMapper::toResponse)
                        .toList();

        return ResponseEntity.ok(patients);
    }

    /**
     * Retrieves a patient using their ID.
     *
     * GET /api/patients/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<PatientResponse> getPatientById(
            @PathVariable Long id
    ) {

        Patient patient = patientService.getPatientById(id);

        PatientResponse response =
                patientMapper.toResponse(patient);

        return ResponseEntity.ok(response);
    }

    /**
     * Updates an existing patient.
     *
     * PUT /api/patients/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<PatientResponse> updatePatient(
            @PathVariable Long id,
            @Valid @RequestBody PatientRequest request
    ) {

        Patient updatedPatient = patientMapper.toEntity(request);

        Patient savedPatient =
                patientService.updatePatient(id, updatedPatient);

        PatientResponse response =
                patientMapper.toResponse(savedPatient);

        return ResponseEntity.ok(response);
    }

    /**
     * Deletes a patient using their ID.
     *
     * DELETE /api/patients/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(
            @PathVariable Long id
    ) {

        patientService.deletePatient(id);

        return ResponseEntity.noContent().build();
    }
}
