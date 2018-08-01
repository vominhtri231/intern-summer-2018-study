package asiantech.internship.summer;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import asiantech.internship.summer.unittest.helpers.Validator;
import asiantech.internship.summer.unittest.model.User;

import static org.junit.Assert.assertEquals;

public class ValidUsernameTest {
    @Mock
    User user;

    @Before
    public void setupMock() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void usernameNotHave2UpperCase() {
        Mockito.when(user.getUsername()).thenReturn("jsahdgkasl");
        assertEquals(Validator.validate(user), R.string.error_username_not_has_2_uppercase);
    }

    @Test
    public void usernameHave3AdjacentNumber() {
        Mockito.when(user.getUsername()).thenReturn("jsAhd123gkaSl");
        assertEquals(Validator.validate(user), R.string.error_username_has_3_adjacent_number);
    }

    @Test
    public void usernameNotStartByNormalChar() {
        Mockito.when(user.getUsername()).thenReturn("1jsAhdgkaSl");
        assertEquals(Validator.validate(user), R.string.error_username_not_start_with_normal_char);
    }

    @Test
    public void usernameHasLessThan7Char() {
        Mockito.when(user.getUsername()).thenReturn("daAsAh");
        assertEquals(Validator.validate(user), R.string.error_username_not_has_7_to_21_char);
    }

    @Test
    public void usernameHasMore21Char() {
        Mockito.when(user.getUsername()).thenReturn("daAsAhahdajjdasjhgurrenwerhuamewhuvgrnerjndaqeAA");
        assertEquals(Validator.validate(user), R.string.error_username_not_has_7_to_21_char);
    }
}
