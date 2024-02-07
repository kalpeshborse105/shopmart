// package com.shope.shopmart.Entities;

// import java.util.Collection;
// import java.util.List;

// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;

// import jakarta.persistence.ElementCollection;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.Transient;
// import lombok.Data;

// @Data
// @Entity
// public class User{

//     @Id
//     @GeneratedValue(strategy = GenerationType.AUTO)
//     private Integer id;
//     private String email;
//     private String password;
//     @ElementCollection
//     private List<String>roles;
    
//     @Transient
//     private Collection<? extends GrantedAuthority> authorities;

//     public User(String email, String password, Collection<? extends GrantedAuthority> authorities) {
//         this.email = email;
//         this.password = password;
//         this.authorities = authorities;
//     }

//     public User() {}



//     @Override
//     public Collection<? extends GrantedAuthority> getAuthorities() {
//         return this.authorities;
//     }

//     @Override
//     public String getUsername() {
//         return this.email;
//     }

//     @Override
//     public String getPassword() {
//         return this.password;
//     }
//     @Override
//     public boolean isAccountNonExpired() {
//         return true;
//     }

//     @Override
//     public boolean isAccountNonLocked() {
//         return true;
//     }

//     @Override
//     public boolean isCredentialsNonExpired() {
//         return true;
//     }

//     @Override
//     public boolean isEnabled() {
//         return true;
//     }
// }

