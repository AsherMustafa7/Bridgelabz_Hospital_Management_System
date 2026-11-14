package com.asher.hospitalmanagement.service.implementations;

import com.asher.hospitalmanagement.entity.Patient;
import com.asher.hospitalmanagement.repository.PatientRepository;
import com.asher.hospitalmanagement.service.interfaces.PatientService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.util.List;

import com.asher.hospitalmanagement.exception.ResourceNotFoundException;

/**
 * Implements the business operations defined by PatientService.
 *
 * @Service registers this class as a Spring-managed bean.
 */
@Service
public class PatientServiceImpl implements PatientService {

    private static final Logger logger =
            LoggerFactory.getLogger(PatientServiceImpl.class);

    private final PatientRepository patientRepository;

    /**
     * Constructor injection.
     *
     * Spring automatically provides the PatientRepository bean
     * created by Spring Data JPA.
     */
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Patient createPatient(Patient patient) {

        Patient savedPatient = patientRepository.save(patient);

        logger.info(
                "Patient created successfully with ID: {}",
                savedPatient.getId()
        );

        return savedPatient;
    }

    @Override
    public List<Patient> getAllPatients() {

        List<Patient> patients = patientRepository.findAll();

        logger.debug(
                "Retrieved {} patients",
                patients.size()
        );

        return patients;
    }

    @Override
    public Patient getPatientById(Long id) {

        return patientRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Patient not found with ID: {}", id);

                    return new ResourceNotFoundException(
                            "Patient not found with ID: " + id
                    );
                });
    }

    @Override
    public Patient updatePatient(Long id, Patient updatedPatient) {

        Patient existingPatient = getPatientById(id);

        existingPatient.setName(updatedPatient.getName());
        existingPatient.setAge(updatedPatient.getAge());
        existingPatient.setGender(updatedPatient.getGender());
        existingPatient.setDisease(updatedPatient.getDisease());
        existingPatient.setAddress(updatedPatient.getAddress());
        existingPatient.setPhoneNumber(updatedPatient.getPhoneNumber());

        Patient savedPatient = patientRepository.save(existingPatient);

        logger.info("Patient updated successfully with ID: {}", id);

        return savedPatient;
    }

    @Override
    public void deletePatient(Long id) {

        Patient existingPatient = getPatientById(id);

        patientRepository.delete(existingPatient);

        logger.info("Patient deleted successfully with ID: {}", id);
    }
}
