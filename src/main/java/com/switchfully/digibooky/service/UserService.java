package com.switchfully.digibooky.service;

import com.switchfully.digibooky.domain.Role;
import com.switchfully.digibooky.domain.User;
import com.switchfully.digibooky.dto.CreateUserDto;
import com.switchfully.digibooky.dto.UserDto;
import com.switchfully.digibooky.mapper.UserMapper;
import com.switchfully.digibooky.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    private UserMapper userMapper;
    private UserRepository userRepository;

    public UserService(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    public UserDto createAdmin(CreateUserDto createUserDto) {
        return createUser(createUserDto, Role.ADMIN);
    }

    public UserDto createLibrarian(CreateUserDto createUserDto) {
        return createUser(createUserDto, Role.LIBRARIAN);
    }

    public UserDto createMember(CreateUserDto createUserDto) {
        return createUser(createUserDto, Role.MEMBER);
    }

    public void checkIfUserIsAdmin(String email, String password) {
        User user = userRepository.getUserByEmail(email);
        checkIfPasswordIsCorrect(user, password);

        if (user.getRole() != Role.ADMIN) {
            throw new IllegalArgumentException();
        }
    }

    public void checkIfUserIsLibrarian(String email, String password) {
        User user = userRepository.getUserByEmail(email);
        checkIfPasswordIsCorrect(user, password);

        if (user.getRole() != Role.LIBRARIAN) {
            throw new IllegalArgumentException();
        }
    }

    private UserDto createUser(CreateUserDto createUserDto, Role role) throws IllegalArgumentException {
        if (userRepository.checkIfEmailExists(createUserDto.getEmail())) {
            throw new IllegalArgumentException();
        }

        createUserDto.setPassword(bCryptPasswordEncoder.encode(createUserDto.getPassword()));

        User user = userRepository.create(userMapper.mapCreateUserDtoToUser(createUserDto, role));
        UserDto userDto = userMapper.mapUserToUserDto(user);

        return userDto;
    }

    private void checkIfPasswordIsCorrect(User user, String password) throws IllegalArgumentException {
        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException();
        }
    }
}
