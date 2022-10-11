package account.contracts.response;

import account.models.UserEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRegResponseCM {

    private long id;
    private String name;
    private String lastname;
    private String email;

    public UserRegResponseCM(UserEntity user) {
        this.id = user.getPublicId();
        this.email = user.getUsername();
        this.name = user.getFirstName();
        this.lastname = user.getLastName();
    }
}
