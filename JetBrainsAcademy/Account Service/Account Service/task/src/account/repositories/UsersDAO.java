package account.repositories;

import account.models.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface UsersDAO extends CrudRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
    List<UserEntity> findByUsernameIn(Collection<String> usernames);
}
