package com.shope.shopmart.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shope.shopmart.Entities.User;
import com.shope.shopmart.Repository.UserRepository;

import jakarta.transaction.Transactional;

import java.util.stream.Collectors;

@Service
public class MyUserDetailsService implements UserDetailsService {

        @Autowired
        private UserRepository userRepository;

        @Override
        @Transactional
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return this.userRepository
                .findByEmail(username)
                                .map(user -> {
                                        return new User(
                                                        user.getEmail(),
                                                        user.getPassword(),
                                                        user.getRoles().stream()
                                                                        .map(role -> new SimpleGrantedAuthority(role))
                                                                        .collect(Collectors.toList()));
                                })
                                .orElseThrow(() -> {
                                        throw new UsernameNotFoundException("User wit email does not exists");
                                }
                                );
        }
}
