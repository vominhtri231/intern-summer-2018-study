package asiantech.internship.summer.unit_test;

public final class UtilValidate {
    public final static String CHECK_LENGTH_USERNAME = "Username must be 6 from 22 characters";
    public final static String UPCASE_USERNAME = "Username must have at least 2 non consecutive capital character";
    public final static String USERNAME_IS_NOT_SPECIAL = "Username isn't special character and space";
    public final static String USERNAME_CONTAIN_DIGIT = "Username must be many 2 next digit";
    public final static String START_USERNAME = "Username can't begin with capital character or digit ";
    public final static String PASSWORD_IS_NOT_USERNAME = "Password must be different username";
    public final static String PASSWORD_CONTAIN_SPECIAL = "Password is at least 2 special character or digit";
    public final static String CHECK_LENGTH_PASSWORD = "Password must be 8 or more characters";
    public final static String CHARACRER_PASSWORD_REPEAT = "Password isn't loop 1 character too 2 times next";
    public final static String END_PASSWORD = "Password can't finish with special character or digit ";
    public final static String UPCASE_PASSWORD = "Password must have at least 3 non consecutive capital character";
    public final static String SUCCESS = "Success!!!";

    public static boolean checkLengthUsername(String username) {
        return username.length() >= 6 && username.length() <= 22;
    }

    public static int containUpcase(String string) {
        int n = 0;
        for (int i = 0; i < string.length(); i++) {
            if (Character.isUpperCase(string.charAt(i))) {
                n++;
            }
        }
        return n;
    }

    public static boolean upcaseIsNotContinous(String string, int number) {
        if (containUpcase(string) < number) {
            return false;
        }
        int m = -2;
        int n;
        for (int i = 0; i < string.length(); i++) {
            if (Character.isUpperCase(string.charAt(i))) {
                n = i;
                if (m + 1 == n) {
                    return false;
                }
                m = n;
            }
        }
        return true;
    }

    public static boolean usernameisNotSpecialCharacter(String username) {
        for (int i = 0; i < username.length(); i++) {
            if (!Character.isLetterOrDigit(username.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static int usernameIsContainDigit(String username) {
        int n = 0;
        for (int i = 0; i < username.length(); i++) {
            if (Character.isDigit(username.charAt(i))) {
                n++;
            }
        }
        return n;
    }

    public static boolean usernameIsDigitContinous(String username) {
        if (usernameIsContainDigit(username) <= 1) {
            return true;
        }
        if (usernameIsContainDigit(username) > 2) {
            return false;
        }
        int m = -2;
        int n;
        for (int i = 0; i < username.length(); i++) {
            if (Character.isDigit(username.charAt(i))) {
                n = i;
                if (m + 1 == n) {
                    return true;
                }
                m = n;
            }
        }
        return false;
    }

    public static boolean usernameStartLowerCase(String username) {
        return Character.isLowerCase(username.charAt(0));
    }

    public static boolean passwordIsNotUser(String username, String password) {
        return username.length() != password.length() || !password.contains(username);
    }

    public static boolean passwordContainsDigitAndSpecial(String password) {
        int n = 0;
        for (int i = 0; i < password.length(); i++) {
            if ((!Character.isLetterOrDigit(password.charAt(i))
                    && !Character.isSpaceChar(password.charAt(i))
                    || Character.isDigit(password.charAt(i)))) {
                n++;
            }
        }
        return n >= 2;
    }

    public static boolean checkLengthPassword(String password) {
        return password.length() >= 8;
    }

    public static boolean passwordNotRepeatCharacter(String password) {
        for (int i = 0; i < password.length() - 1; i++) {
            if (password.charAt(i) == password.charAt(i + 1)) {
                return false;
            }
        }
        return true;
    }

    public static boolean passwordEndWithLetter(String password) {
        return Character.isLetter(password.charAt(password.length() - 1));
    }

    public static String resultLogin(String username, String password) {
        if (!checkLengthUsername(username)) {
            return CHECK_LENGTH_USERNAME;
        }
        if (!upcaseIsNotContinous(username, 2)) {
            return UPCASE_USERNAME;
        }
        if (!usernameisNotSpecialCharacter(username)) {
            return USERNAME_IS_NOT_SPECIAL;
        }
        if (!usernameIsDigitContinous(username)) {
            return USERNAME_CONTAIN_DIGIT;
        }
        if (!usernameStartLowerCase(username)) {
            return START_USERNAME;
        }
        if (!checkLengthPassword(password)) {
            return CHECK_LENGTH_PASSWORD;
        }
        if (!passwordIsNotUser(username, password)) {
            return PASSWORD_IS_NOT_USERNAME;
        }
        if (!passwordContainsDigitAndSpecial(password)) {
            return PASSWORD_CONTAIN_SPECIAL;
        }
        if (!passwordNotRepeatCharacter(password)) {
            return CHARACRER_PASSWORD_REPEAT;
        }
        if (!passwordEndWithLetter(password)) {
            return END_PASSWORD;
        }
        if (!upcaseIsNotContinous(password, 3)) {
            return UPCASE_PASSWORD;
        }
        return SUCCESS;
    }
}
