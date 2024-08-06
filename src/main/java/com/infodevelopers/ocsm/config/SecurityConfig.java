package com.infodevelopers.ocsm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/enrollment/enroll").hasRole("INSTRUCTOR")
                                .requestMatchers("/enrollment/enrollmentList").hasAnyRole("INSTRUCTOR", "STUDENT")
                                .requestMatchers("/enrollment/myEnrollments").hasRole("STUDENT")
                                .anyRequest().authenticated()
                )
                .formLogin(withDefaults())
                .httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails student = User.builder()
                .username("student")
                .password(passwordEncoder().encode("password"))
                .roles("STUDENT")
                .build();
        UserDetails instructor = User.builder()
                .username("instructor")
                .password(passwordEncoder().encode("password"))
                .roles("INSTRUCTOR")
                .build();
        return new InMemoryUserDetailsManager(student, instructor);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

