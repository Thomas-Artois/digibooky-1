package com.switchfully.digibooky.repository;

import com.switchfully.digibooky.domain.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class UserRepository {
    private Map<String, User> users = new HashMap<>();

    public User create(User user) {
        users.put(user.getId().toString(), user);

        return user;
    }

    public boolean checkIfEmailExists(String email) {
        return users.entrySet().stream().anyMatch(stringUserEntry -> stringUserEntry.getValue().getEmail().equals(email));
    }
}