package com.switchfully.eurder_backend.mapper;

import com.switchfully.eurder_backend.dto.userdto.CreateUserDto;
import com.switchfully.eurder_backend.dto.userdto.UserDto;
import com.switchfully.eurder_backend.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User mapCreateUserDtoToUser(CreateUserDto createUserDto) {
        return new User(
                createUserDto.getFirstName(),
                createUserDto.getLastName(),
                createUserDto.getEmail(),
                createUserDto.getAddress(),
                createUserDto.getPhoneNumber(),
                createUserDto.getRole()
        );
    }

    public UserDto mapUserToUserDto(User user) {
        return new UserDto(
                user.getUserId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getAddress(),
                user.getPhoneNumber()
        );
    }

}
