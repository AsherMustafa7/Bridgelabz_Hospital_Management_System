
package com.asher.hospitalmanagement.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Represents incoming patient data from API requests.
 *
 * Validation annotations ensure that invalid data
 * is rejected before it reaches the service layer.
 */
public class PatientRequest {

    @NotBlank(message = "Patient name is required")
    @Size(max = 100, message = "Name cannot exceed 100 characters")
    private String name;

    @NotNull(message = "Patient age is required")
    @Min(value = 1, message = "Age must be at least 1")
    private Integer age;

    @NotBlank(message = "Gender is required")
    @Size(max = 20, message = "Gender cannot exceed 20 characters")
    private String gender;

    @NotBlank(message = "Disease is required")
    @Size(max = 150, message = "Disease cannot exceed 150 characters")
    private String disease;

    @NotBlank(message = "Address is required")
    @Size(max = 255, message = "Address cannot exceed 255 characters")
    private String address;

    @NotBlank(message = "Phone number is required")
    @Pattern(
            regexp = "^\\+?[0-9]{10,15}$",
            message = "Phone number must contain 10 to 15 digits, optionally starting with +"
    )
    private String phoneNumber;

    public PatientRequest() {
    }

    public PatientRequest(
            String name,
            Integer age,
            String gender,
            String disease,
            String address,
            String phoneNumber
    ) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.disease = disease;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
