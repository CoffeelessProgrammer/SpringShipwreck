package dev.koicreek.springshipwreck;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationAPI {
    @Autowired
    UserRepository userRepo;
    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/register")
    public void register(@RequestBody CustomUser user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole("ROLE_" + user.getRole());
        userRepo.save(user);
    }

    @GetMapping("/details")
    public void details(Authentication auth) {
        // UserDetails details = (UserDetails) auth.getPrincipal();

        System.out.println("Username: " + auth.getName());
        System.out.println("User has authorities/roles: " + auth.getAuthorities());
    }

    @GetMapping("/principal")
    public void principal(@AuthenticationPrincipal UserDetails user) {
        System.out.println("Username: " + user.getUsername());
        System.out.println("User has authorities/roles: " + user.getAuthorities());
    }
}
