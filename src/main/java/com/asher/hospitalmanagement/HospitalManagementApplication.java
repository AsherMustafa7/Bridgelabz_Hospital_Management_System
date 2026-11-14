
package com.asher.hospitalmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point of the Hospital Management System.
 *
 * @SpringBootApplication enables:
 * 1. Component scanning
 * 2. Auto-configuration
 * 3. Spring configuration
 */
@SpringBootApplication
public class HospitalManagementApplication {

    public static void main(String[] args) {

        // Starts the Spring application context
        // and launches the embedded server.
        SpringApplication.run(HospitalManagementApplication.class, args);
    }
}
