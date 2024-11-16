package com.BookYourShow.UserManagement.DTO;

import com.BookYourShow.UserManagement.Models.Utils.Gender;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import lombok.Data;


@Data
public class UserUpdateDTO {

    @Column(nullable = false)
    private String name;

    @Min(value = 14)
    @Max(value = 45)
    private Integer age;
    
    @Enumerated(EnumType.STRING)
    private Gender gender;
}
