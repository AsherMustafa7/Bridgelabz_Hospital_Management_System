
package com.asher.hospitalmanagement.service.interfaces;

import com.asher.hospitalmanagement.entity.User;

import java.util.Optional;

/**
 * Defines the business operations available for User.
 */
public interface UserService {

    User saveUser(User user);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
