package account.services;

import account.models.UserEntity;
import account.validation.UsernameExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UserEntityManager {
    @Autowired
    UsersDAO usersDAO;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserEntity loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = usersDAO.findByUsername(username.toLowerCase());
        if(user == null) throw new UsernameNotFoundException(username);
        return user;
    }

    public UserEntity createUser(UserEntity user) {
        if(userExists(user.getUsername()))
            throw new UsernameExistsException();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        usersDAO.save(user);
        return user;
    }

    public void updateUser(UserEntity user) {
    }

    public void deleteUser(String username) {
    }

    public void changePassword(String oldPassword, String newPassword) {
    }

    public boolean userExists(String username) {
        UserEntity user = usersDAO.findByUsername(username);
        return user != null;
    }
}
