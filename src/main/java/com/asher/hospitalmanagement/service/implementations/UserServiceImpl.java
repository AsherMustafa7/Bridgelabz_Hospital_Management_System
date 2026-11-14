
package com.asher.hospitalmanagement.service.implementations;

import com.asher.hospitalmanagement.entity.User;
import com.asher.hospitalmanagement.repository.UserRepository;
import com.asher.hospitalmanagement.service.interfaces.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implements the business operations defined by UserService.
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger =
            LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    /**
     * Spring injects the UserRepository bean through this constructor.
     */
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {

        User savedUser = userRepository.save(user);

        logger.info("User account saved successfully with ID: {}",
                savedUser.getId());

        return savedUser;
    }

    @Override
    public Optional<User> findByEmail(String email) {

        return userRepository.findByEmail(email);
    }

    @Override
    public boolean existsByEmail(String email) {

        return userRepository.existsByEmail(email);
    }
}
