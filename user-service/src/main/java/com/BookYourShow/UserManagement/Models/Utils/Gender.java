package com.BookYourShow.UserManagement.Models.Utils;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Gender {
    M, F;

    @JsonCreator
    public static Gender fromString(String value) {
        for (Gender gender : Gender.values()) {
            if (gender.name().equalsIgnoreCase(value)) {
                return gender;
            }
        }
        
        throw new IllegalArgumentException("Invalid gender. Accepted values are 'M' or 'F'.");
    }
}
