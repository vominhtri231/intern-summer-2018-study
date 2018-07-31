package asiantech.internship.summer.unit_test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountValidationTest {
    @Mock
    private Account mAccountUsername;
    @Spy
    private Account mAccountPassword = new Account();

    @Test
    public void testCaseLengthUser() {
        when(mAccountUsername.getUsername()).thenReturn("");
        assertFalse(UtilValidate.checkLengthUsername(mAccountUsername.getUsername()));
        when(mAccountUsername.getUsername()).thenReturn("asdsdf");
        assertTrue(UtilValidate.checkLengthUsername(mAccountUsername.getUsername()));
        when(mAccountUsername.getUsername()).thenReturn("asdsdfdhgjghgjghjtjghj");
        assertTrue(UtilValidate.checkLengthUsername(mAccountUsername.getUsername()));
        when(mAccountUsername.getUsername()).thenReturn("asdsdfdhgjghgjghjtjghjd");
        assertFalse(UtilValidate.checkLengthUsername(mAccountUsername.getUsername()));
    }

    @Test
    public void testCaseContainUpcase() {
        when(mAccountUsername.getUsername()).thenReturn("asdsdfdhgjghgjghjd");
        assertEquals(UtilValidate.containUpcase(mAccountUsername.getUsername()), 0);
    }

    @Test
    public void testCaseUpcaseIsNotContinous() {
        when(mAccountUsername.getUsername()).thenReturn("asdsdfdhgjghgjggh");
        assertFalse(UtilValidate.upcaseIsNotContinous(mAccountUsername.getUsername(), 2));
        when(mAccountUsername.getUsername()).thenReturn("asdsdfdhgjgjgTTth");
        assertFalse(UtilValidate.upcaseIsNotContinous(mAccountUsername.getUsername(), 2));
        when(mAccountUsername.getUsername()).thenReturn("asdsdfdhgjgTfTth");
        assertTrue(UtilValidate.upcaseIsNotContinous(mAccountUsername.getUsername(), 2));
    }

    @Test
    public void testCaseUsernameisNotSpecialCharacter() {
        when(mAccountUsername.getUsername()).thenReturn("shddfhf_jd");
        assertFalse(UtilValidate.usernameisNotSpecialCharacter(mAccountUsername.getUsername()));
        when(mAccountUsername.getUsername()).thenReturn("shddfhfjd");
        assertTrue(UtilValidate.usernameisNotSpecialCharacter(mAccountUsername.getUsername()));
    }

    @Test
    public void testCaseUsernameIsContainDigit() {
        when(mAccountUsername.getUsername()).thenReturn("asdsdfdjghjd");
        assertEquals(UtilValidate.usernameIsContainDigit(mAccountUsername.getUsername()), 0);
    }

    @Test
    public void testCaseUsernameIsDigitContinous() {
        when(mAccountUsername.getUsername()).thenReturn("dfifids");
        assertTrue(UtilValidate.usernameIsDigitContinous(mAccountUsername.getUsername()));
        when(mAccountUsername.getUsername()).thenReturn("dfifid5s6j7");
        assertFalse(UtilValidate.usernameIsDigitContinous(mAccountUsername.getUsername()));
        when(mAccountUsername.getUsername()).thenReturn("dfifid5f6j");
        assertFalse(UtilValidate.usernameIsDigitContinous(mAccountUsername.getUsername()));
        when(mAccountUsername.getUsername()).thenReturn("dfifid56j");
        assertTrue(UtilValidate.usernameIsDigitContinous(mAccountUsername.getUsername()));
    }

    @Test
    public void testCaseUsernameStartLowerCase() {
        when(mAccountUsername.getUsername()).thenReturn("dfifid56j");
        assertTrue(UtilValidate.usernameStartLowerCase(mAccountUsername.getUsername()));
        when(mAccountUsername.getUsername()).thenReturn("Aajhshssk");
        assertFalse(UtilValidate.usernameStartLowerCase(mAccountUsername.getUsername()));
    }

    @Test
    public void testCasePasswordIsNotUser() {
        when(mAccountUsername.getUsername()).thenReturn("ajhd");
        when(mAccountPassword.getPassword()).thenReturn("dfgh");
        assertTrue(UtilValidate.passwordIsNotUser(mAccountUsername.getUsername(), mAccountPassword.getPassword()));
        when(mAccountUsername.getUsername()).thenReturn("fghj");
        when(mAccountPassword.getPassword()).thenReturn("fghj");
        assertFalse(UtilValidate.passwordIsNotUser(mAccountUsername.getUsername(), mAccountPassword.getPassword()));
    }

    @Test
    public void testCasePasswordContainsDigitAndSpecial() {
        when(mAccountPassword.getPassword()).thenReturn("fghj");
        assertFalse(UtilValidate.passwordContainsDigitAndSpecial(mAccountPassword.getPassword()));
        when(mAccountPassword.getPassword()).thenReturn("fgh)1h");
        assertTrue(UtilValidate.passwordContainsDigitAndSpecial(mAccountPassword.getPassword()));
    }

    @Test
    public void testCaseCheckLengthPassword() {
        when(mAccountPassword.getPassword()).thenReturn("asdsd");
        assertFalse(UtilValidate.checkLengthPassword(mAccountPassword.getPassword()));
        when(mAccountPassword.getPassword()).thenReturn("asdsdhtf");
        assertTrue(UtilValidate.checkLengthPassword(mAccountPassword.getPassword()));
    }

    @Test
    public void testCasePasswordNotRepeatCharacter() {
        when(mAccountPassword.getPassword()).thenReturn("asddsd");
        assertFalse(UtilValidate.passwordNotRepeatCharacter(mAccountPassword.getPassword()));
        when(mAccountPassword.getPassword()).thenReturn("asdsdhsa");
        assertTrue(UtilValidate.passwordNotRepeatCharacter(mAccountPassword.getPassword()));
    }

    @Test
    public void testCasePasswordEndWithLetter() {
        when(mAccountPassword.getPassword()).thenReturn("asajhjd1");
        assertFalse(UtilValidate.passwordEndWithLetter(mAccountPassword.getPassword()));
        when(mAccountPassword.getPassword()).thenReturn("dhdhdf");
        assertTrue(UtilValidate.passwordEndWithLetter(mAccountPassword.getPassword()));
    }

    @Test
    public void testCaseResultLogin() {
        when(mAccountUsername.getUsername()).thenReturn("fg");
        when(mAccountPassword.getPassword()).thenReturn("jddd");
        assertEquals(UtilValidate.resultLogin(mAccountUsername.getUsername(), mAccountPassword.getPassword()), UtilValidate.CHECK_LENGTH_USERNAME);
        when(mAccountUsername.getUsername()).thenReturn("djfaah");
        when(mAccountPassword.getPassword()).thenReturn("jddd");
        assertEquals(UtilValidate.resultLogin(mAccountUsername.getUsername(), mAccountPassword.getPassword()), UtilValidate.UPCASE_USERNAME);
        when(mAccountUsername.getUsername()).thenReturn("djfHiH_h");
        when(mAccountPassword.getPassword()).thenReturn("jddd");
        assertEquals(UtilValidate.resultLogin(mAccountUsername.getUsername(), mAccountPassword.getPassword()), UtilValidate.USERNAME_IS_NOT_SPECIAL);
        when(mAccountUsername.getUsername()).thenReturn("djfHiH345h");
        when(mAccountPassword.getPassword()).thenReturn("jddd");
        assertEquals(UtilValidate.resultLogin(mAccountUsername.getUsername(), mAccountPassword.getPassword()), UtilValidate.USERNAME_CONTAIN_DIGIT);
        when(mAccountUsername.getUsername()).thenReturn("AjfHiH35h");
        when(mAccountPassword.getPassword()).thenReturn("jddd");
        assertEquals(UtilValidate.resultLogin(mAccountUsername.getUsername(), mAccountPassword.getPassword()), UtilValidate.START_USERNAME);
        when(mAccountUsername.getUsername()).thenReturn("jfHiH35h");
        when(mAccountPassword.getPassword()).thenReturn("jddd");
        assertEquals(UtilValidate.resultLogin(mAccountUsername.getUsername(), mAccountPassword.getPassword()), UtilValidate.CHECK_LENGTH_PASSWORD);
        when(mAccountUsername.getUsername()).thenReturn("jfHiH35h");
        when(mAccountPassword.getPassword()).thenReturn("jfHiH35h");
        assertEquals(UtilValidate.resultLogin(mAccountUsername.getUsername(), mAccountPassword.getPassword()), UtilValidate.PASSWORD_IS_NOT_USERNAME);
        when(mAccountUsername.getUsername()).thenReturn("jfHiH35h");
        when(mAccountPassword.getPassword()).thenReturn("jfHisfg5h");
        assertEquals(UtilValidate.resultLogin(mAccountUsername.getUsername(), mAccountPassword.getPassword()), UtilValidate.PASSWORD_CONTAIN_SPECIAL);
        when(mAccountUsername.getUsername()).thenReturn("jfHiH35h");
        when(mAccountPassword.getPassword()).thenReturn("jfHiKss9sHfg5h");
        assertEquals(UtilValidate.resultLogin(mAccountUsername.getUsername(), mAccountPassword.getPassword()), UtilValidate.CHARACRER_PASSWORD_REPEAT);
        when(mAccountUsername.getUsername()).thenReturn("jfHiH35h");
        when(mAccountPassword.getPassword()).thenReturn("jfHis6g5h5");
        assertEquals(UtilValidate.resultLogin(mAccountUsername.getUsername(), mAccountPassword.getPassword()), UtilValidate.END_PASSWORD);
        when(mAccountUsername.getUsername()).thenReturn("jfHiH35h");
        when(mAccountPassword.getPassword()).thenReturn("jfHis6g5hH");
        assertEquals(UtilValidate.resultLogin(mAccountUsername.getUsername(), mAccountPassword.getPassword()), UtilValidate.UPCASE_PASSWORD);
        when(mAccountUsername.getUsername()).thenReturn("jfHiH35h");
        when(mAccountPassword.getPassword()).thenReturn("jfHisK6g5hH");
        assertEquals(UtilValidate.resultLogin(mAccountUsername.getUsername(), mAccountPassword.getPassword()), UtilValidate.SUCCESS);
    }
}
