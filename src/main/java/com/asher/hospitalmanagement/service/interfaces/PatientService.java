
package com.asher.hospitalmanagement.service.interfaces;

import com.asher.hospitalmanagement.entity.Patient;

import java.util.List;

/**
 * Defines the business operations available for Patient.
 *
 * This interface separates the service contract
 * from its implementation.
 */
public interface PatientService {

    Patient createPatient(Patient patient);

    List<Patient> getAllPatients();

    Patient getPatientById(Long id);

    Patient updatePatient(Long id, Patient updatedPatient);

    void deletePatient(Long id);
}
