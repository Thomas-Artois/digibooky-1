package com.switchfully.digibooky.service;

import com.switchfully.digibooky.domain.User;
import com.switchfully.digibooky.dto.CreateUserDto;
import com.switchfully.digibooky.dto.UserDto;
import com.switchfully.digibooky.mapper.UserMapper;
import com.switchfully.digibooky.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserMapper userMapper;
    private UserRepository userRepository;

    public UserService(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    public UserDto createUser(CreateUserDto createUserDto) {
        User user = userRepository.create(userMapper.mapCreateUserDtoToUser(createUserDto));

        return userMapper.mapUserToUserDto(user);
    }
}
