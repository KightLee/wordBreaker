package org.example.worldbreaker.validation;

import java.util.regex.Pattern;

public class WordValidator {

    // input regex
    public static final Pattern INPUT_REGEX_STR = Pattern.compile("^(?i)[A-Za-z]+$");

    public static boolean isValid(String str) {
        return INPUT_REGEX_STR.matcher(str).matches();
    }

}
