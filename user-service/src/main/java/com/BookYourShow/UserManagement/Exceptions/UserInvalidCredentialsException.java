package com.BookYourShow.UserManagement.Exceptions;

public class UserInvalidCredentialsException extends RuntimeException {
    public UserInvalidCredentialsException(String message) {
        super(message);
    }
}