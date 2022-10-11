package account.services;

import account.contracts.ModifyRoleCM;
import account.contracts.UserInfoCM;
import account.models.UserEntity;
import account.repositories.UsersDAO;
import account.security.UserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    UsersDAO usersDAO;

    public UserInfoCM modifyUserRole(ModifyRoleCM modifyRoleCM) {
        if (!usersDAO.existsByUsername(modifyRoleCM.getEmail()))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!");
        try {
            UserRoles.valueOf(modifyRoleCM.getRole());
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found!");
        }

        UserEntity userEntity = usersDAO.findByUsername(modifyRoleCM.getEmail());

        switch (modifyRoleCM.getOperation()) {
            case GRANT: {
                if (userEntity.addRole(modifyRoleCM.getRole().toUpperCase())) usersDAO.save(userEntity);
                break;
            }
            case REMOVE: {
                if (!userEntity.hasRole(modifyRoleCM.getRole()))
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The user does not have a role!");

                userEntity.removeRole(modifyRoleCM.getRole());
                usersDAO.save(userEntity);
                break;
            }
        }

        return new UserInfoCM(userEntity);
    }

    public List<UserInfoCM> getAllUsers() {
        List<UserInfoCM> users = new ArrayList<>();
        List<UserEntity> userEntities = usersDAO.findAllByOrderByPublicIdAsc();

        for(UserEntity user : userEntities)
            users.add(new UserInfoCM(user));

        return users;
    }

    @Transactional
    public void deleteUser(String email) {
        if(!usersDAO.existsByUsername(email))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!");

        usersDAO.deleteByUsername(email);
    }
}
