package dev.koicreek.springshipwreck;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomUser {
    private String username;
    private String password;
    private String role;
}
