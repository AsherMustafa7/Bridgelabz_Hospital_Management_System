package com.asher.hospitalmanagement.repository;

import com.asher.hospitalmanagement.entity.Patient;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository responsible for database operations related to Patient.
 *
 * Spring Data JPA provides the implementation at runtime.
 */
public interface PatientRepository extends JpaRepository<Patient, Long> {

}
