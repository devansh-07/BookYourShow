package com.BookYourShow.UserManagement.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.BookYourShow.UserManagement.ErrorResponse;
import com.BookYourShow.UserManagement.DTO.UserCreateDTO;
import com.BookYourShow.UserManagement.DTO.UserLoginDTO;
import com.BookYourShow.UserManagement.DTO.UserProfileDTO;
import com.BookYourShow.UserManagement.DTO.UserUpdateDTO;
import com.BookYourShow.UserManagement.Exceptions.UserAlreadyExistsException;
import com.BookYourShow.UserManagement.Exceptions.UserInvalidCredentialsException;
import com.BookYourShow.UserManagement.Exceptions.UserNotFoundException;
import com.BookYourShow.UserManagement.ServiceImpl.UserServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;

import java.util.Map;
import java.util.HashMap;


@RestController
public class UserController {

    @Autowired
    public UserServiceImpl userService;
    

    // Endpoints
    
    @PostMapping(value = "/api/users/create")
    public ResponseEntity<UserProfileDTO> createNewUser(@Valid @RequestBody UserCreateDTO userProfileDTO) {
        return ResponseEntity.ok(userService.createUser(userProfileDTO));
    }


    @PostMapping(value = "/api/users/verify")
    public ResponseEntity<?> verifyAuthHeader() {
        return ResponseEntity.ok().build();
    }


    @PostMapping(value = "/api/users/update")
    public ResponseEntity<UserProfileDTO> updateUser(HttpServletRequest request, @Valid @RequestBody UserUpdateDTO userUpdateDTO) {
        String email = (String) request.getAttribute("email");
        System.out.println("email:: " + email);
        return ResponseEntity.ok(userService.updateUser(email, userUpdateDTO));
    }


    @PostMapping(value = "/api/users/login")
    public ResponseEntity<Map<String, String>> loginUser(@Valid @RequestBody UserLoginDTO userLoginDTO) {
        String jwt = userService.createJWT(userLoginDTO);
        
        Map<String, String> response = new HashMap<>();
        response.put("jwt_token", jwt);

        return ResponseEntity.ok(response);
    }


    @GetMapping(value = "/api/users/{email}")
    public ResponseEntity<UserProfileDTO> getUser(@PathVariable("email") @Email String email) {
        return ResponseEntity.ok(userService.getUser(email));
    }

    
    @DeleteMapping(value = "/api/users/delete")
    public ResponseEntity<UserProfileDTO> deleteUser(HttpServletRequest request) {
        String email = (String) request.getAttribute("email");
        userService.deleteUser(email);
        return ResponseEntity.noContent().build();
    }

    // // // // // 
    // Custom Exception handlers
    // // // // // 

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserExistsException(UserAlreadyExistsException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(UserInvalidCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCredentialsException(UserInvalidCredentialsException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }
}
