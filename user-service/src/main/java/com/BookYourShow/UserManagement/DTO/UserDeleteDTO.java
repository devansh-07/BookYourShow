package com.BookYourShow.UserManagement.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UserDeleteDTO {
    @NotBlank(message = "Email is a required field.")
    @Email
    private String email;
}
