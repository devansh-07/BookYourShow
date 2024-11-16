package com.BookYourShow.UserManagement.DTO;


import com.BookYourShow.UserManagement.Models.Utils.Gender;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// import javax.validation.constraints.Email;
// import javax.validation.constraints.NotBlank;
// import javax.validation.constraints.NotNull;

import lombok.Data;


@Data
public class UserCreateDTO {
    @NotBlank(message = "Email is a required field.")
    @Email
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "Name is required.")
    private String name;

    @Min(value = 14)
    @Max(value = 45)
    @NotNull(message = "Age is required.")
    private Integer age;
    
    @NotBlank(message = "Password is obviously required.")
    private String password;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Gender is required.")
    private Gender gender;
}
