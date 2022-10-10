package account.models;

import account.contracts.UserRegistrationCM;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USERS")
@Data
@NoArgsConstructor
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue
    @Column(name="user_id")
    private long publicId;

    @Column(name="email", unique=true)
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    @ElementCollection(targetClass = Authority.class, fetch = FetchType.EAGER)
    @JoinTable(name="AUTHORITIES",
            joinColumns = @JoinColumn(name = "user_id"))
    private Set<GrantedAuthority> authorities = new HashSet<>();

    public UserEntity(UserRegistrationCM request) {
        this.firstName = request.getName();
        this.lastName = request.getLastname();
        this.username = request.getEmail().toLowerCase();
        this.password = request.getPassword();
    }

    public UserEntity(String email) {
        this.username = email;
    }

    public UserEntity(long userId) {
        this.publicId = userId;
    }

    public UserEntity(long userId, String email) {
        this.publicId = userId;
        this.username = email;
    }

    public void setUsername(String username) {
        this.username = username.toLowerCase();
    }

    //#region Booleans

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    //#endregion
}
