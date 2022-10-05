package dev.koicreek.springshipwreck;

import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepository {
    final private Map<String, CustomUser> users = new ConcurrentHashMap<>();

    public CustomUser findUserByUsername(String username) {
        return users.get(username);
    }

    public void save(CustomUser user) {
        users.put(user.getUsername(), user);
    }
}
