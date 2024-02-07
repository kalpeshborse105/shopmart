package com.shope.shopmart.dtos;


import java.util.List;

import com.shope.shopmart.validators.PhoneNumber;
import com.shope.shopmart.validators.VerifyPassword;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@VerifyPassword(
    field = "password",
    matchingField = "confirmPassword"
)
public class RegisterUserDto {
    @NotNull
    @Size(min = 1, message = "First Name should be greater than 1 character")
    private String firstName;

    @NotNull
    @Size(min = 1, message = "Last Name should be greater than 1 character")
    private String lastName;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 6, max = 20, message = "Password should have a length between 6 - 20 characters")
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "Password should contain only alphanumeric characters")
    private String password;

    private String confirmPassword;

    @PhoneNumber
    private String mobile;

    private List<String> roles;
}
