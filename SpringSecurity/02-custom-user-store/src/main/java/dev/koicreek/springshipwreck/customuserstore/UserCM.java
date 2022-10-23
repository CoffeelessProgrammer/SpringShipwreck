package dev.koicreek.springshipwreck.customuserstore;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserCM {
    private String username;
    private String password;
    private String role;
}
