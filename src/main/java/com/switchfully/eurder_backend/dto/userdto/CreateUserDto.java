package com.switchfully.eurder_backend.dto.userdto;

import com.switchfully.eurder_backend.entity.Role;

public class CreateUserDto {

    private final String firstName;
    private final String lastName;
    private final String email;
    private final String address;
    private final String phoneNumber;
    private final Role role;

    public CreateUserDto(String firstName, String lastName, String email, String address, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.role = Role.CUSTOMER;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Role getRole() {
        return role;
    }

}
