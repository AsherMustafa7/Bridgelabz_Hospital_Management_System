package com.asher.hospitalmanagement.dto.response;

/**
 * Represents patient data returned by the REST API.
 *
 * JPA entities are not exposed directly to API clients.
 */
public class PatientResponse {

    private Long id;
    private String name;
    private Integer age;
    private String gender;
    private String disease;
    private String address;
    private String phoneNumber;

    public PatientResponse() {
    }

    public PatientResponse(
            Long id,
            String name,
            Integer age,
            String gender,
            String disease,
            String address,
            String phoneNumber
    ) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.disease = disease;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
