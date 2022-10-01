package account;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserRegRequestCM {

    @NotBlank
    private String name;
    @NotBlank
    private String lastname;
    @NotNull
    private String email;
    @NotBlank
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(!email.endsWith("@acme.com"))
            throw new IllegalArgumentException("Email must be of domain 'acme.com'");
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
