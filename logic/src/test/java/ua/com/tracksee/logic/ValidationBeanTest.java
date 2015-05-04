package ua.com.tracksee.logic;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Ruslan Gunavardana
 */
public class ValidationBeanTest {

    private ValidationBean validationBean = new ValidationBean();

    @Test
    public void testIsValidPassword() throws Exception {
        // good
        assertTrue(validationBean.isValidPassword("+380635helloWORLD"));
        assertTrue(validationBean.isValidPassword("+38022222222222222AAAAAAAAAq"));
        assertTrue(validationBean.isValidPassword("passwordIs2"));
        assertTrue(validationBean.isValidPassword("IloveTHisSECURITYSYSTEM@"));

        // bad values
        assertFalse(validationBean.isValidPassword("+380635293333"));

        // too long
        assertFalse(validationBean.isValidPassword("+38063@1A312qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq"));

        // too short
        assertFalse(validationBean.isValidPassword("1"));

        // only numbers
        assertFalse(validationBean.isValidPassword("121111111111111111111111"));

        // no small letters
        assertFalse(validationBean.isValidPassword("+38022222222222222AAAAAAAAA"));

        // whitespaces
        assertFalse(validationBean.isValidPassword("+38 (063) 529 - 33 - 33"));
        assertFalse(validationBean.isValidPassword("password@A A"));
    }

    @Test
    public void testIsValidEmail() throws Exception {
        // good
        assertTrue(validationBean.isValidEmail("vadimka9992@gmail.com"));
        assertTrue(validationBean.isValidEmail("rusan.rus@ukr.net"));
        assertTrue(validationBean.isValidEmail("alexander.the@bigmir.net"));
        assertTrue(validationBean.isValidEmail("5123123@i.ua"));

        // bad values
        assertFalse(validationBean.isValidEmail("good@-1"));
        assertFalse(validationBean.isValidEmail("+38063@12312"));
        assertFalse(validationBean.isValidEmail("a@a"));
        assertFalse(validationBean.isValidEmail("@gmail.com"));
        assertFalse(validationBean.isValidEmail("1@@"));
        assertFalse(validationBean.isValidEmail("1@ s"));
    }

    @Test
    public void testValidatePhoneNumber() throws Exception {
        // good
        assertTrue(validationBean.isValidPhoneNumber("+380635293333"));
        assertTrue(validationBean.isValidPhoneNumber("+38 (063) 529 - 33 - 33"));
        assertTrue(validationBean.isValidPhoneNumber("421 - 08 - 09"));
        assertTrue(validationBean.isValidPhoneNumber("044-421- 08- 09"));

        // bad values
        assertFalse(validationBean.isValidPhoneNumber("+380635helloWORLD"));
        assertFalse(validationBean.isValidPhoneNumber("+38063@12312"));
        assertFalse(validationBean.isValidPhoneNumber("1"));
        assertFalse(validationBean.isValidPhoneNumber("12"));
        assertFalse(validationBean.isValidPhoneNumber("+380"));
    }
}
