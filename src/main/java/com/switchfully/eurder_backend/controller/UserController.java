package com.switchfully.eurder_backend.controller;

import com.switchfully.eurder_backend.dto.userdto.CreateUserDto;
import com.switchfully.eurder_backend.dto.userdto.UserDto;
import com.switchfully.eurder_backend.exception.AuthorizationException;
import com.switchfully.eurder_backend.service.AuthorizationService;
import com.switchfully.eurder_backend.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/customer")
public class UserController {

    private final AuthorizationService authorizationService;
    private final UserService userService;

    public UserController(AuthorizationService authorizationService, UserService userService) {
        this.authorizationService = authorizationService;
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json", produces = "application/json")
    public UserDto createCustomer(@RequestBody CreateUserDto createUserDto) {
        return userService.createUser(createUserDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/{id}", produces = "application/json")
    public UserDto getCustomer(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization, @PathVariable Long id) {
        if (!authorizationService.isAdmin(authorization)) {
            throw new AuthorizationException("You are not authorized for this action");
        }
        return userService.getUserById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = "application/json")
    public List<UserDto> getAllCustomers(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
        if (!authorizationService.isAdmin(authorization)) {
            throw new AuthorizationException("You are not authorized for this action");
        }
        return userService.getAllUsers();
    }

}
