package com.asher.hospitalmanagement.exception;

/**
 * Thrown when a requested resource does not exist.
 *
 * Examples:
 * - Patient not found.
 * - User not found.
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
