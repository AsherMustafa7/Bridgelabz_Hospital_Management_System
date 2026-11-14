package com.asher.hospitalmanagement.mapper;

import com.asher.hospitalmanagement.dto.request.PatientRequest;
import com.asher.hospitalmanagement.dto.response.PatientResponse;
import com.asher.hospitalmanagement.entity.Patient;

import org.springframework.stereotype.Component;

/**
 * Converts Patient entities to DTOs and DTOs to entities.
 *
 * @Component registers this mapper as a Spring bean.
 */
@Component
public class PatientMapper {

    /**
     * Converts an incoming request DTO into a Patient entity.
     */
    public Patient toEntity(PatientRequest request) {

        Patient patient = new Patient();

        patient.setName(request.getName());
        patient.setAge(request.getAge());
        patient.setGender(request.getGender());
        patient.setDisease(request.getDisease());
        patient.setAddress(request.getAddress());
        patient.setPhoneNumber(request.getPhoneNumber());

        return patient;
    }

    /**
     * Converts a Patient entity into an API response DTO.
     */
    public PatientResponse toResponse(Patient patient) {

        return new PatientResponse(
                patient.getId(),
                patient.getName(),
                patient.getAge(),
                patient.getGender(),
                patient.getDisease(),
                patient.getAddress(),
                patient.getPhoneNumber()
        );
    }
}
