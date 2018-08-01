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
    User user;

    @Before
    public void setUp() {
        user = Mockito.spy(new User("ijsAhdgkaSl", ""));
    }

    @Test
    public void passwordEqualsUsername() {
        Mockito.when(user.getPassword()).thenReturn("ijsAhdgkaSl");
        assertEquals(Validator.validate(user), R.string.error_username_equals_password);
    }

    @Test
    public void passwordRepeatChar() {
        Mockito.when(user.getPassword()).thenReturn("ndaewgeerieR");
        assertEquals(Validator.validate(user), R.string.error_password_repeat_char);
    }

    @Test
    public void passwordNotHas2SpecialChar() {
        Mockito.when(user.getPassword()).thenReturn("nbewgerier");
        assertEquals(Validator.validate(user), R.string.error_password_not_has_2_special_char);
    }

    @Test
    public void passwordNotHas3Uppercase() {
        Mockito.when(user.getPassword()).thenReturn("n1ewge7rieR");
        assertEquals(Validator.validate(user), R.string.error_password_not_has_3_uppercase);
    }

    @Test
    public void passwordHasLessThan8Char() {
        Mockito.when(user.getPassword()).thenReturn("n1RGE7R");
        assertEquals(Validator.validate(user), R.string.error_password_has_less_than_8_char);
    }

    @Test
    public void passwordEndWithSpecialChar() {
        Mockito.when(user.getPassword()).thenReturn("n1RGE7Rgajsd1");
        assertEquals(Validator.validate(user), R.string.error_password_end_with_special_char);
    }

    @Test
    public void passwordAndUsernameValid() {
        Mockito.when(user.getPassword()).thenReturn("n1RGE7Rgajsde");
        assertEquals(Validator.validate(user), R.string.valid_username_password);
    }
}
