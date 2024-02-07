package com.shope.shopmart.Repository;

import com.shope.shopmart.Entities.User;
import com.shope.shopmart.Entities.UserName;
import com.shope.shopmart.dtos.RegisterUserDto;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(excerptProjection = UserName.class)
public interface UserRepository extends JpaRepository<User, Integer> {

    List<UserName> findByFirstNameAndLastName(String firstName, String lastName);

    List<User> findByEmailContaining(String email);

    @RestResource(exported = false)
    Optional<User> findByEmail(String email);
    
    @RestResource(exported = false)
    <S extends User> S save(@Valid RegisterUserDto data);
}
