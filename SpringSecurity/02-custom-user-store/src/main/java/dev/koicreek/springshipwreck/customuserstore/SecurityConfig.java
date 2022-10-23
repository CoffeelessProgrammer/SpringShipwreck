package dev.koicreek.springshipwreck.customuserstore;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http,
                                            AuthenticationProvider dbAuthProvider,
                                            AuthenticationProvider inMemoryAuthProvider) throws Exception {
        http.csrf().disable() // Required for Postman?!
                .authorizeRequests()
                .mvcMatchers("/trainer/**").hasRole("TRAINER")
                .mvcMatchers("/pokedex/**").hasAnyRole("PROFESSOR", "TRAINER")
                .anyRequest().permitAll()
                .and().formLogin()
                .and().httpBasic()
                .and().logout().clearAuthentication(true)
                .and().authenticationManager(new ProviderManager(dbAuthProvider, inMemoryAuthProvider));

        return http.build();
    }

    @Bean
    UserDetailsService inMemoryUserDetailsService(PasswordEncoder passwordEncoder) {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("oak")
                .password(passwordEncoder.encode("password"))
                .authorities("ROLE_PROFESSOR")
                .build()
        );
        manager.createUser(User.withUsername("brock")
                .password(passwordEncoder.encode("password"))
                .roles("TRAINER")
                .build()
        );
        return manager;
    }

    //#region AuthProviders

    @Bean
    public AuthenticationProvider dbAuthProvider(UserDetailsService userDetailsSvcImpl,
                                                 PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsSvcImpl);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean
    public AuthenticationProvider inMemoryAuthProvider(UserDetailsService inMemoryUserDetailsService,
                                                       PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(inMemoryUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    //#endregion
}
