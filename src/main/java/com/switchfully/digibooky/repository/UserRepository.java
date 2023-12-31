package com.switchfully.digibooky.repository;

import com.switchfully.digibooky.domain.Address;
import com.switchfully.digibooky.domain.Role;
import com.switchfully.digibooky.domain.User;
import com.switchfully.digibooky.exception.EmailExistsException;
import com.switchfully.digibooky.exception.SocialSecurityNumberExistsException;
import com.switchfully.digibooky.exception.UserNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Member;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class UserRepository {
    private Map<String, User> users = new HashMap<>();

    public UserRepository() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        Address address = new Address("streetName", "streetNumber", "postalCode", "city");
        User adminUser = new User("AAAAAAAA", "admin", "admin", "admin@digibooky.com", address, Role.ADMIN, bCryptPasswordEncoder.encode("admin"));
        create(adminUser);
        User librarianUser = new User("BBBBAAAA","Librarian","Librarian","librarian@digibooky.com",address, Role.LIBRARIAN, bCryptPasswordEncoder.encode("librarian"));
        create(librarianUser);

        List<User> listOfUsers = List.of(
                new User("8ec158fc-9b1b-46af-9e3f-5c99ee9752a9","11111111", "One", "OneLast", "one@digibooky.com", new Address("Stockholm"), Role.MEMBER, bCryptPasswordEncoder.encode("passwordOne")),
                new User("9dc7d4f8-612b-4cdb-8bcb-838ce16a4c12","22222222", "Two", "TwoLast", "two@digibooky.com", new Address("Stockholm"), Role.MEMBER, bCryptPasswordEncoder.encode("passwordTwo")),
                new User("7dcd50bb-98bb-49a0-96ac-e59c3fc7f9a0","33333333", "Three", "ThreeLast", "three@digibooky.com", new Address("Stockholm"), Role.MEMBER, bCryptPasswordEncoder.encode("passwordThree"))
        );

        listOfUsers.forEach(this::create);
    }

    public User create(User user) {
        users.put(user.getId(), user);

        return user;
    }

    public void checkIfEmailExists(String email) throws EmailExistsException {
        if (users.values().stream().anyMatch(user -> user.getEmail().equals(email))) {
            throw new EmailExistsException();
        }
    }

    public void checkIfSocialSecurityNumberExists(String socialSecurityNumber) throws SocialSecurityNumberExistsException {
        if (users.values().stream().anyMatch(user -> user.getSocialSecurityNumber().equals(socialSecurityNumber))) {
            throw new SocialSecurityNumberExistsException();
        }
    }

    public User getUserByEmail(String email) throws UserNotFoundException {
        return users.values().stream().filter(user -> user.getEmail().equals(email)).findFirst().orElseThrow(UserNotFoundException::new);
    }

    public User getUserById(String userId) throws UserNotFoundException{
        User user = users.get(userId);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return users.get(userId);
    }

    public List<User> getAllMembers() {
        return users.values().stream().filter(user -> user.getRole().equals(Role.MEMBER)).collect(Collectors.toList());
    }
}