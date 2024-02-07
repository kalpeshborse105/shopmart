// package com.shope.shopmart.Services;

// import com.shope.shopmart.Entities.RegisteredUser;
// import com.shope.shopmart.Repository.RegisteredUserRepository;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.stereotype.Service;
// import org.springframework.web.server.ResponseStatusException;

// @Service
// public class AuthService {

//     @Autowired
//     private RegisteredUserRepository repository;


//     @Autowired
//     private BCryptPasswordEncoder passwordEncoder;

//     public ResponseEntity<String> register(RegisteredUser registeredUser) {
//     if (repository.findByEmail(registeredUser.getEmail()).isPresent()) {
//         throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with this email already registered");
//     }

//     registeredUser.setPassword(passwordEncoder.encode(registeredUser.getPassword()));
//     repository.save(registeredUser);

//     return ResponseEntity.ok("Congratulations!!! User registered successfully");
// }

//     // public String login(LoginDto loginDto) {
//     //     Authentication authentication = authenticationManager.authenticate(
//     //             new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
//     //     SecurityContextHolder.getContext().setAuthentication(authentication);
//     //     return "User Logged In Successfully!!!";
//     // }
// }
