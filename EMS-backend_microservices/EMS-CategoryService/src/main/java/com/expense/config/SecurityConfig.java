package com.expense.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import com.expense.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    private final UserService userDetailsService;
//    private final PasswordEncoder passwordEncoder;

    private final UserService userService;

    @Autowired
    public SecurityConfig(UserService userService) {
        
		this.userService = userService;
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable() // Consider enabling this if your application uses session-based authentication
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers(HttpMethod.POST, "/user/auth/signin", "/user/auth/signup").permitAll()
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()
//                .requestMatchers("/admin/**").hasRole("ADMIN")
//                .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/**").permitAll()
                .anyRequest().authenticated())
            .httpBasic(); // Basic authentication for simplicity, consider JWT or OAuth for more secure apps

        return http.build();
    }
}
