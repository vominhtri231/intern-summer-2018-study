package asiantech.internship.summer.unittest.helpers;

import java.util.regex.Pattern;

public class Validater {

    private Validater() {

    }

    private static final Pattern VALID_PASSWORD_REGEX =
            Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{7,22}$");

    private static final Pattern VALID_USERNAME_REGEX =
            Pattern.compile("^(){7,22}$", Pattern.CASE_INSENSITIVE);

    public static boolean isValidUsername(String username) {
        return VALID_USERNAME_REGEX.matcher(username).find();
    }

    public static boolean isValidPassword(String password) {
        return VALID_PASSWORD_REGEX.matcher(password).find();
    }

}
