package com.switchfully.eurder_backend.service;

import com.switchfully.eurder_backend.entity.Role;
import com.switchfully.eurder_backend.entity.User;
import com.switchfully.eurder_backend.exception.NotFoundException;
import com.switchfully.eurder_backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class AuthorizationService {


    private final UserRepository userRepository;

    public AuthorizationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(String authorization) {
        String authentication = authorization.split(" ")[1];
        byte[] authenticationDecoded = Base64.getDecoder().decode(authentication);
        String authenticationAsString = new String(authenticationDecoded);
        String username = authenticationAsString.split(":")[0];

        User user = userRepository.findById(Long.valueOf(username))
                .orElseThrow(() -> new NotFoundException("User id not found"));

        return user;
    }

    public boolean isAdmin(String authorization) {
        return getUser(authorization).getRole() == Role.ADMIN;
    }

    public boolean isCustomer(String authorization) {
        return getUser(authorization).getRole() == Role.CUSTOMER;
    }

}
