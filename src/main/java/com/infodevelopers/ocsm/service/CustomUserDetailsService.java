//package com.infodevelopers.ocsm.service;
//
//import com.infodevelopers.ocsm.entity.User;
//import com.infodevelopers.ocsm.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    private final UserRepository userRepository;
//
//    @Autowired
//    public CustomUserDetailsService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> optionalUser = userRepository.findByUserName(username);
//
//        User user = optionalUser.get();
//        if (user == null) {
//            throw new UsernameNotFoundException("User not found");
//        }
//
//        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
//                user.getRole().stream()
//                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRoleName().toUpperCase()))
//                        .collect(Collectors.toList()));
//    }
//}
//
