package dev.koicreek.springshipwreck.customuserstore;

import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserDAO {
    final private Map<String, UserCM> users = new ConcurrentHashMap<>();

    public UserCM findUserByUsername(String username) {
        return users.get(username);
    }

    public void save(UserCM user) {
        users.put(user.getUsername(), user);
    }
}
