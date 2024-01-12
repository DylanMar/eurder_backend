package com.switchfully.eurder_backend.service;

import com.switchfully.eurder_backend.dto.userdto.CreateUserDto;
import com.switchfully.eurder_backend.entity.User;
import com.switchfully.eurder_backend.dto.userdto.UserDto;
import com.switchfully.eurder_backend.exception.NotFoundException;
import com.switchfully.eurder_backend.mapper.UserMapper;
import com.switchfully.eurder_backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDto createUser(CreateUserDto createUserDto) {
        User user = userMapper.mapCreateUserDtoToUser(createUserDto);
        return userMapper.mapUserToUserDto(userRepository.save(user));
    }

    public List<UserDto> getAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(userMapper::mapUserToUserDto)
                .toList();
    }

    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User id not found"));
        return userMapper.mapUserToUserDto(user);
    }

}
