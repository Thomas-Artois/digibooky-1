package com.switchfully.digibooky.repository;

import com.switchfully.digibooky.domain.Address;
import com.switchfully.digibooky.domain.Role;
import com.switchfully.digibooky.domain.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class UserRepository {
    public Map<String, User> users = new HashMap<>();

    public UserRepository() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        Address address = new Address("streetName", "streetNumber", "postalCode", "city");
        User adminUser = new User("admin", "admin", "admin@digibooky.com", address, Role.ADMIN, bCryptPasswordEncoder.encode("admin"));

        create(adminUser);
    }

    public User create(User user) {
        users.put(user.getId().toString(), user);

        return user;
    }

    public boolean checkIfEmailExists(String email) {
        return users.entrySet().stream().anyMatch(stringUserEntry -> stringUserEntry.getValue().getEmail().equals(email));
    }

    public User getUserByEmail(String email) throws IllegalArgumentException {
        if (!checkIfEmailExists(email)) {
            throw new IllegalArgumentException();
        }

        return users.entrySet().stream().filter(stringUserEntry -> stringUserEntry.getValue().getEmail().equals(email)).findAny().get().getValue();
    }
}