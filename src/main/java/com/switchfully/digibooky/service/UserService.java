package com.switchfully.digibooky.service;

import com.switchfully.digibooky.domain.Role;
import com.switchfully.digibooky.domain.User;
import com.switchfully.digibooky.dto.CreateUserDto;
import com.switchfully.digibooky.dto.UserDto;
import com.switchfully.digibooky.exception.*;
import com.switchfully.digibooky.mapper.UserMapper;
import com.switchfully.digibooky.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public void checkIfUserIsAdmin(String email, String password) throws NotAnAdminException {
        User user = userRepository.getUserByEmail(email);
        checkIfPasswordIsCorrect(user, password);

        if (user.getRole() != Role.ADMIN) {
            throw new NotAnAdminException();
        }
    }

    public void checkIfUserIsLibrarian(String email, String password) throws NotALibrarianException {
        User user = userRepository.getUserByEmail(email);
        checkIfPasswordIsCorrect(user, password);

        if (user.getRole() != Role.LIBRARIAN) {
            throw new NotALibrarianException();
        }
    }

    // TODO refactor params
    public void checkIfUserIsMember(String email, String password) throws NotAMemberException {
        User user = userRepository.getUserByEmail(email);
        checkIfPasswordIsCorrect(user, password);

        if (user.getRole() != Role.MEMBER) {
            throw new NotAMemberException();
        }
    }

    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    public void checkIfUserExists(String userId) throws UserNotFoundException {
        User user = userRepository.getUserById(userId);
        checkIfPasswordIsCorrect(user, user.getPassword());
    }

    private UserDto createUser(CreateUserDto createUserDto, Role role) throws EmailExistsException, SocialSecurityNumberExistsException {
        userRepository.checkIfEmailExists(createUserDto.getEmail());
        userRepository.checkIfSocialSecurityNumberExists(createUserDto.getSocialSecurityNumber());

        createUserDto.setPassword(bCryptPasswordEncoder.encode(createUserDto.getPassword()));

        User user = userRepository.create(userMapper.mapCreateUserDtoToUser(createUserDto, role));

        return userMapper.mapUserToUserDto(user);
    }

    private void checkIfPasswordIsCorrect(User user, String password) throws PasswordIsIncorrectException {
        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw new PasswordIsIncorrectException();
        }
    }

    public List<UserDto> getAllMembers() {
        return userRepository.getAllMembers().stream().map(user -> userMapper.mapUserToUserDto(user)).collect(Collectors.toList());
    }
}
