package com.BookYourShow.UserManagement.Service;

import java.lang.Boolean;
import com.BookYourShow.UserManagement.DTO.UserLoginDTO;
import com.BookYourShow.UserManagement.DTO.UserCreateDTO;
import com.BookYourShow.UserManagement.DTO.UserProfileDTO;
import com.BookYourShow.UserManagement.DTO.UserUpdateDTO;

public interface UserService {
    UserProfileDTO createUser(UserCreateDTO userCreateDTO);
    UserProfileDTO updateUser(String email, UserUpdateDTO userUpdateDTO);
    Boolean doesUserExist(String email);
    void deleteUser(String email);
    UserProfileDTO getUser(String email);
    String createJWT(UserLoginDTO userLoginDTO);
}
