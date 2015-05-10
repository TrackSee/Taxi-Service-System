package ua.com.tracksee.logic.encryption;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import static java.lang.Math.abs;

/**
 * @author Ruslan Gunavardana.
 */
public class PasswordUtils {
    private static final Logger logger = LogManager.getLogger(PasswordUtils.class);

    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIALS = "!@.,?;~`'\"()[]\\/_-";

    private static final String ALGORITHM = "SHA1PRNG";
    private static final SecureRandom random;

    static {
        try {
            random = SecureRandom.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            String errorString = "Mistyped algorithm in class " + PasswordUtils.class + " : " + ALGORITHM;
            logger.fatal(errorString);
            throw new ExceptionInInitializerError(errorString);
        }
    }

    public static String generatePassword(int len, boolean useUpper, boolean useLower,
                                          boolean useDigits, boolean useSpecial) {
        if (!(useUpper | useLower | useDigits | useSpecial)) {
            String errorString = "All boolean parameters, passed into the function are false. " +
                    "Impossible to generate the password with no symbols.";
            logger.error(errorString);
            return null;
        }

        StringBuilder pass = new StringBuilder(len);
        StringBuilder allowedChars = getAllowedChars(useUpper, useLower, useDigits, useSpecial);
        byte[] randomBytes = new byte[len];

        random.nextBytes(randomBytes);
        for (byte randomByte : randomBytes) {
            int randomIndex = abs(randomByte) % allowedChars.length();
            char randomChar = allowedChars.charAt(randomIndex);
            pass.append(randomChar);
        }

        return pass.toString();
    }

    private static StringBuilder getAllowedChars(boolean useUpper, boolean useLower,
                                                 boolean useDigits, boolean useSpecial) {
        StringBuilder allowedChars = new StringBuilder();
        if (useLower) allowedChars.append(LOWERCASE);
        if (useUpper) allowedChars.append(UPPERCASE);
        if (useDigits) allowedChars.append(DIGITS);
        if (useSpecial) allowedChars.append(SPECIALS);
        // allowedChars size can't be bigger than 127, because we use random signed byte values for the passwords
        assert allowedChars.length() <= 127;

        return allowedChars;
    }
}
