package com.switchfully.digibooky.repository;

import com.switchfully.digibooky.domain.Address;
import com.switchfully.digibooky.domain.Role;
import com.switchfully.digibooky.domain.User;
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
        User adminUser = new User("ABCDEFGH", "admin", "admin", "admin@digibooky.com", address, Role.ADMIN, bCryptPasswordEncoder.encode("admin"));

        create(adminUser);
    }

    public User create(User user) {
        users.put(user.getId(), user);

        return user;
    }

    public void checkIfEmailExists(String email) throws IllegalArgumentException {
        if (users.values().stream().noneMatch(user -> user.getEmail().equals(email))) {
            throw new IllegalArgumentException();
        }
    }

    public void checkIfSocialSecurityNumberExists(String socialSecurityNumber) throws IllegalArgumentException{
        if (users.values().stream().noneMatch(user -> user.getSocialSecurityNumber().equals(socialSecurityNumber))) {
            throw new IllegalArgumentException();
        }
    }

    public User getUserByEmail(String email) throws IllegalArgumentException {
        return users.values().stream().filter(user -> user.getEmail().equals(email)).findFirst().orElseThrow(IllegalArgumentException::new);
    }

    public List<User> getAllMembers() {
        return users.values().stream().filter(user -> user.getRole().equals(Role.MEMBER)).collect(Collectors.toList());
    }
}