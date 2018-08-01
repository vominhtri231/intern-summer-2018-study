package asiantech.internship.summer;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.Spy;

import asiantech.internship.summer.unittest.helpers.Validator;
import asiantech.internship.summer.unittest.model.User;

import static org.junit.Assert.assertEquals;

public class ValidPasswordTest {
    @Spy
    private User user;

    @Before
    public void setUp() {
        user = Mockito.spy(new User("ijsAhdgkaSl", ""));
    }

    @Test
    public void passwordEqualsUsername() {
        Mockito.doReturn("ijsAhdgkaSl").when(user).getPassword();
        assertEquals(Validator.validate(user), R.string.error_username_equals_password);
    }

    @Test
    public void passwordRepeatChar() {
        Mockito.doReturn("ndaewgeerieR").when(user).getPassword();
        assertEquals(Validator.validate(user), R.string.error_password_repeat_char);
    }

    @Test
    public void passwordNotHas2SpecialChar() {
        Mockito.doReturn("nbewgerier").when(user).getPassword();
        assertEquals(Validator.validate(user), R.string.error_password_not_has_2_special_char);
    }

    @Test
    public void passwordNotHas3Uppercase() {
        Mockito.doReturn("n1ewge7rieR").when(user).getPassword();
        assertEquals(Validator.validate(user), R.string.error_password_not_has_3_uppercase);
    }

    @Test
    public void passwordHasLessThan8Char() {
        Mockito.doReturn("n1RGE7R").when(user).getPassword();
        assertEquals(Validator.validate(user), R.string.error_password_has_less_than_8_char);
    }

    @Test
    public void passwordEndWithSpecialChar() {
        Mockito.doReturn("n1RGE7Rgajsd1").when(user).getPassword();
        assertEquals(Validator.validate(user), R.string.error_password_end_with_special_char);
    }

    @Test
    public void passwordAndUsernameValid() {
        Mockito.doReturn("n1RGE7Rgajsde").when(user).getPassword();
        assertEquals(Validator.validate(user), R.string.valid_username_password);
    }
}
