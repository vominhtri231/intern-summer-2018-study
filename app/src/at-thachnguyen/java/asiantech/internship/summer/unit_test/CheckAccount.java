package asiantech.internship.summer.unit_test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class CheckAccount {

    public static boolean validateUser(String username) {
        Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^[a-zA-Z]+[a-zA-Z0-9._%+-]*+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(username);
        return !matcher.find();
    }

//    public static boolean checkPassword(String password) {
//        Pattern VALID_PASSWORD_REGEX =
//                Pattern.compile("[a-zA-Z0-9]{6,}", Pattern.CASE_INSENSITIVE);
//        Matcher matcher = VALID_PASSWORD_REGEX.matcher(password);
//        return !matcher.find();
//    }
}
