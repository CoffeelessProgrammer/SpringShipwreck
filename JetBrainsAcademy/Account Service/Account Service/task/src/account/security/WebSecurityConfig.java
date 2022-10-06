package account.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    RestAuthenticationEntryPoint restAuthEntryPoint;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(13);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests() // manage access
                .mvcMatchers("/api/auth/signup").anonymous()
                .mvcMatchers("/api/auth/changepass").authenticated()
                .mvcMatchers("/api/empl/**").authenticated()
                .and().csrf().disable().headers().frameOptions().disable() // For Postman, the H2 console
                .and().httpBasic().authenticationEntryPoint(restAuthEntryPoint) // Handle auth error
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS); // No session
    }

}
