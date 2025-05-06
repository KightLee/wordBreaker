package org.example.worldbreaker.validation;

import java.util.regex.Pattern;

public class WordValidator {

    public static final Pattern FILE_REGEX_STR = Pattern.compile("\\{\\^(?i)[A-Za-z]+}");;

    // input regex
    public static final Pattern INPUT_REGEX_STR = Pattern.compile("^(?i)[A-Za-z]+$");

    public static boolean isValid(String str) {
        return INPUT_REGEX_STR.matcher(str).matches();
    }

    public static boolean isFile(String str) {
        return FILE_REGEX_STR.matcher(str).matches();
    }
}
