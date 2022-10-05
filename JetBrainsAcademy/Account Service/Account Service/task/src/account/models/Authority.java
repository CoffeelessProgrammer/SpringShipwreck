package account.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Embeddable;

@Data
@AllArgsConstructor
@Embeddable
public class Authority implements GrantedAuthority {

    private String authority;
}
