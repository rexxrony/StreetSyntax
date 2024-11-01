package com.rex.streetSyntax.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
            .csrf(csrf -> csrf.disable())  // Disable CSRF for the application
            .authorizeHttpRequests(auth -> auth
                    .anyRequest().permitAll()  // Permit all requests temporarily
            )
            .logout(logout -> logout
                    .logoutUrl("/api/users/logout")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                    .logoutSuccessHandler((request, response, authentication) -> {
                        logger.info("User logged out successfully");
                        response.setContentType("application/json");
                        response.setStatus(HttpStatus.OK.value());
                        response.getWriter().write("{\"message\": \"Logout successful!\"}");
                        response.getWriter().flush();
                    })
            );

    return http.build();
}

}
