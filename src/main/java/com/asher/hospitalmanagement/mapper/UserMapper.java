package com.asher.hospitalmanagement.mapper;

import com.asher.hospitalmanagement.dto.request.RegisterRequest;
import com.asher.hospitalmanagement.entity.User;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    /**
     * Converts registration data into a User entity.
     *
     * Password encoding and role assignment are handled
     * by the authentication service, not by this mapper.
     */
    public User toEntity(RegisterRequest request) {

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        return user;
    }
}
