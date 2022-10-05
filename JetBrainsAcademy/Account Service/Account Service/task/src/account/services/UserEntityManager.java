package account.services;

import account.models.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserEntityManager extends UserDetailsService {

    UserEntity createUser(UserEntity user);
    void updateUser(UserEntity user);
    void deleteUser(String username);
    void changePassword(String oldPassword, String newPassword);
    boolean userExists(String username);
}
