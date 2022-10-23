package dev.koicreek.springshipwreck.hellospringsecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
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
        // return NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/public").permitAll()
                .mvcMatchers("/").anonymous()
                .mvcMatchers("/heal").hasRole("NURSE")
                .mvcMatchers("/service").hasAnyRole("NURSE", "ADMIN")
                .mvcMatchers("/actuator/**").hasAuthority("MONITORING")
                .mvcMatchers("/**").authenticated()     // or .anyRequest().authenticated()
                .and().httpBasic()
                .and().formLogin()
                .and().logout()
                    .deleteCookies("JSESSIONID")
                    .invalidateHttpSession(true)
                    .clearAuthentication(true); // This line fixed the broken logout

        return http.build();
    }

    @Bean
    UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("brock")
                .password(passwordEncoder.encode("onix"))
                .authorities("MONITORING")
                .build()
        );
        manager.createUser(User.withUsername("nursejoy")
                .password(passwordEncoder.encode("chansey"))
                .roles("NURSE")
                .build()
        );
        manager.createUser(User.withUsername("admin")
                .password(passwordEncoder.encode("password"))
                .roles("ADMIN")
                .build()
        );
        return manager;
    }

    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return (web) ->
                web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**", "/favicon.ico");
    }
}
