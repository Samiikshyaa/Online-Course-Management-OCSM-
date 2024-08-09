//package com.infodevelopers.ocsm.config;
//
//import com.infodevelopers.ocsm.service.CustomUserDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.SecurityBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.JdbcUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//import static org.springframework.security.config.Customizer.withDefaults;
//
//@Configuration
//public class SecurityConfig{
//
////    @Autowired
////    private DataSource dataSource;
//
//
//    private final CustomUserDetailsService customUserDetailsService;
//
//    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
//        this.customUserDetailsService = customUserDetailsService;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//                .csrf().disable()
//                .authorizeRequests(authorizeRequests ->
//                        authorizeRequests
//                                .requestMatchers("/enrollment/enroll").hasRole("instructor".toUpperCase())
//                                .requestMatchers("/enrollment/enrollmentList").hasAnyRole("instructor".toUpperCase(), "student".toUpperCase())
//                                .requestMatchers("/enrollment/myEnrollments").hasRole("student".toUpperCase())
//                                .anyRequest().authenticated()
//                )
//                .formLogin(withDefaults())
//                .httpBasic(withDefaults());
//        return http.build();
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
////        UserDetails student = User.builder()
////                .username("student")
////                .password(passwordEncoder().encode("password"))
////                .roles("student")
////                .build();
////        UserDetails instructor = User.builder()
////                .username("instructor")
////                .password(passwordEncoder().encode("password"))
////                .roles("instructor")
////                .build();
////        return new InMemoryUserDetailsManager(student, instructor);
//
////        JdbcUserDetailsManager manager = new JdbcUserDetailsManager();
////        manager.setDataSource(dataSource);
////        manager.setUsersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username=?");
////
////        manager.setAuthoritiesByUsernameQuery("SELECT username, role FROM user_roles WHERE username=?");
////        return manager;
//
//
//
//        return  customUserDetailsService;
//    }
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//}
//
