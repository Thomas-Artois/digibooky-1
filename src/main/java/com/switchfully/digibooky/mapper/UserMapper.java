package com.switchfully.digibooky.mapper;

import com.switchfully.digibooky.domain.Role;
import com.switchfully.digibooky.domain.User;
import com.switchfully.digibooky.dto.CreateUserDto;
import com.switchfully.digibooky.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User mapCreateUserDtoToUser(CreateUserDto createUserDto, Role role) {
        return new User(createUserDto.getFirstName(), createUserDto.getLastName(), createUserDto.getEmail(), createUserDto.getAddress(), role, createUserDto.getPassword());
    }

    public UserDto mapUserToUserDto(User user) {
        return new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getAddress(), user.getRole());
    }
}
