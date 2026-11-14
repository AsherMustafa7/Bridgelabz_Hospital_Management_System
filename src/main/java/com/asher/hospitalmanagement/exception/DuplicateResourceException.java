package com.asher.hospitalmanagement.exception;

/**
 * Thrown when an attempt is made to create a resource
 * that already exists.
 *
 * Example:
 * Registering an account using an already registered email.
 */
public class DuplicateResourceException extends RuntimeException {

    public DuplicateResourceException(String message) {
        super(message);
    }
}
