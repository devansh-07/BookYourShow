package com.BookYourShow.UserManagement.Models;

import com.BookYourShow.UserManagement.Models.Utils.Gender;

import jakarta.persistence.Column;

// import javax.validation.constraints.Email;
// import javax.validation.constraints.NotBlank;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Email is required.")
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
