package account.models;

import account.contracts.UserRegistrationCM;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.server.ResponseStatusException;

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
    @JsonIgnore
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

    public boolean addRole(String role) {
        if ((role.equals("ACCOUNTANT") || role.equals("USER")) && this.hasRole("ADMINISTRATOR"))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The user cannot combine administrative and business roles!");
        if (role.equals("ADMINISTRATOR") && (this.hasRole("USER") || this.hasRole("ACCOUNTANT")))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The user cannot combine administrative and business roles!");

        return this.authorities.add(new Authority("ROLE_" + role));
    }

    public boolean hasRole(String role) {
        return this.authorities.contains(new Authority("ROLE_" + role));
    }

    public boolean removeRole(String role) {
        if (this.authorities.size() < 2)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The user must have at least one role!");

        return this.authorities.remove(new Authority("ROLE_" + role));
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
