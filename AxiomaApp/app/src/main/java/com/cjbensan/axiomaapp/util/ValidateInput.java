package com.cjbensan.axiomaapp.util;

import java.util.regex.Pattern;

public class ValidateInput {

    public static boolean validateForename(String forename) {
        return Pattern.matches("[A-Z][a-zA-Z]*", forename);
    }

    public static boolean validateSurname(String surname) {
        return Pattern.matches("[a-zA-z]+([ '-][a-zA-Z]+)*", surname);
    }

    public static boolean validateEmail(String email) {
        return Pattern.matches("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", email);
    }

    public static boolean validatePassword(String password) {
        return password.length() >= 8;
    }
}
