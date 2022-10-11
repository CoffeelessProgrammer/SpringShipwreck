package account.contracts;

import account.models.Authority;
import account.models.UserEntity;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Data
public class UserInfoCM {
    private long id;
    private String name;
    private String lastname;
    private String email;
    private List<String> roles;

    public UserInfoCM(UserEntity userEntity) {
        this.id = userEntity.getPublicId();
        this.name = userEntity.getFirstName();
        this.lastname = userEntity.getLastName();
        this.email = userEntity.getUsername();
        this.roles = new ArrayList<>();

        for(GrantedAuthority authority : userEntity.getAuthorities())
            this.roles.add(authority.getAuthority());

        Collections.sort(roles);
    }
}
