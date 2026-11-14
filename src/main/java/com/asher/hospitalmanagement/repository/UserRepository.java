
package com.asher.hospitalmanagement.repository;

import com.asher.hospitalmanagement.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository responsible for database operations related to User.
 *
 * Spring Data JPA automatically creates an implementation
 * of this interface at runtime.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a user using their email address.
     *
     * Spring Data JPA derives the query from the method name.
     */
    Optional<User> findByEmail(String email);

    /**
     * Checks whether a user with the given email already exists.
     */
    boolean existsByEmail(String email);
}
