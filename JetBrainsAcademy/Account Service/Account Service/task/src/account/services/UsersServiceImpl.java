package account.services;

import account.models.UserEntity;
import account.repositories.UsersDAO;
import account.validation.UsernameExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UsersServiceImpl implements UserEntityManager {

    @Autowired
    private UsersDAO usersDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserEntity loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = usersDAO.findByUsername(username.toLowerCase());
        if(user == null) throw new UsernameNotFoundException(username);
        return user;
    }

    public UserEntity createUser(UserEntity user) {
        if(usersDAO.count() == 0)
            user.addRole("ADMINISTRATOR");
        else user.addRole("USER");

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

    public void changePassword(String oldPassword, String newPassword) throws AuthenticationException {
        Authentication currentUser = SecurityContextHolder.getContext()
                .getAuthentication();

        UserEntity user = this.loadUserByUsername(currentUser.getName()); // By email

        if(passwordEncoder.matches(newPassword, oldPassword))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The passwords must be different!");

        user.setPassword(passwordEncoder.encode(newPassword));
        usersDAO.save(user);
    }

    public boolean userExists(String username) {
        UserEntity user = usersDAO.findByUsername(username);
        return user != null;
    }
}
