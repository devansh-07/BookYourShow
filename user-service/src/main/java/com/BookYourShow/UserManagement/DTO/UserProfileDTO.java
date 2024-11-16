package com.BookYourShow.UserManagement.DTO;

import com.BookYourShow.UserManagement.Models.Utils.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@AllArgsConstructor
@Builder
public class UserProfileDTO {
    private String email;
    private String name;
    private Integer age;
    private Gender gender;
}
