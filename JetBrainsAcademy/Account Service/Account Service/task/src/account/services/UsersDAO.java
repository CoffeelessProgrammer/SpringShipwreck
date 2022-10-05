package account.services;

import account.models.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersDAO extends CrudRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
}
