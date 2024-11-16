package com.BookYourShow.UserManagement.ServiceImpl;

import java.util.Optional;
import java.lang.Boolean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BookYourShow.UserManagement.DTO.UserLoginDTO;
import com.BookYourShow.UserManagement.DTO.UserCreateDTO;
import com.BookYourShow.UserManagement.DTO.UserProfileDTO;
import com.BookYourShow.UserManagement.DTO.UserUpdateDTO;
import com.BookYourShow.UserManagement.Exceptions.UserAlreadyExistsException;
import com.BookYourShow.UserManagement.Exceptions.UserInvalidCredentialsException;
import com.BookYourShow.UserManagement.Exceptions.UserNotFoundException;
import com.BookYourShow.UserManagement.Models.Users;
import com.BookYourShow.UserManagement.Repository.UserRepository;
import com.BookYourShow.UserManagement.Service.UserService;
import com.BookYourShow.UserManagement.Utils.JwtUtil;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public JwtUtil jwtUtil;


    @Override
    public UserProfileDTO createUser(UserCreateDTO userDTO) {
        String email = userDTO.getEmail();
        
        if (this.doesUserExist(email)) {
            throw new UserAlreadyExistsException("User with this email is already present.");
        }

        Users user = Users.builder()
            .email(userDTO.getEmail())
            .name(userDTO.getName())
            .age(userDTO.getAge())
            .gender(userDTO.getGender())
            .password(userDTO.getPassword())
            .build();
        
        userRepository.save(user);

        return UserProfileDTO.builder()
            .email(user.getEmail())
            .name(user.getName())
            .age(user.getAge())
            .gender(user.getGender())
            .build();
    }


    @Override
    public String createJWT(UserLoginDTO userLoginDTO) {
        String passedPassword = userLoginDTO.getPassword();

        Optional<Users> u = userRepository.findUserByEmail(userLoginDTO.getEmail());

        System.out.println("User:: " + u.isEmpty() + " PAss: " + (passedPassword.equals(u.get().getPassword())));
        
        if ((u.isEmpty()) || (!passedPassword.equals(u.get().getPassword()))) {
            throw new UserInvalidCredentialsException("Invalid credentials.");
        }

        Users user = u.get();
        String jwt = jwtUtil.createToken(user.getEmail());

        return jwt;
    }

    @Override
    public Boolean doesUserExist(String email) {
        Optional<Users> user = userRepository.findUserByEmail(email);

        if (user.isPresent()) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    @Transactional
    public void deleteUser(String email) {
        if (!this.doesUserExist(email)) {
            throw new UserAlreadyExistsException("No such user.");
        }

        userRepository.deleteByEmail(email);
    }


    @Override
    public UserProfileDTO getUser(String email) {
        Optional<Users> user = userRepository.findUserByEmail(email);

        if (user.isPresent()) {
            Users u = user.get();
            return UserProfileDTO.builder()
            .email(u.getEmail())
            .name(u.getName())
            .age(u.getAge())
            .gender(u.getGender())
            .build();
        }
        else {
            throw new UserNotFoundException("No such user exists.");
        }
    }


    @Override
    public UserProfileDTO updateUser(String email, UserUpdateDTO userUpdateDTO) {
        Optional<Users> u = userRepository.findUserByEmail(email);

        if (!u.isPresent()) {
            throw new UserAlreadyExistsException("No such user.");
        }
        
        Users user = u.get();

        if (userUpdateDTO.getAge() != null) {
            user.setAge(userUpdateDTO.getAge());
        }
        
        if (userUpdateDTO.getName() != null) {
            user.setName(userUpdateDTO.getName());
        }

        if (userUpdateDTO.getGender() != null) {
            user.setGender(userUpdateDTO.getGender());
        }

        Users updatedUser = userRepository.save(user);

        return UserProfileDTO.builder()
            .email(updatedUser.getEmail())
            .name(updatedUser.getName())
            .age(updatedUser.getAge())
            .gender(updatedUser.getGender())
            .build();
    }

}
