package ua.com.tracksee.logic.encryption;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static java.lang.String.format;

/**
 * @author Ruslan Gunavardana.
 */
public class HashGenerator {
    private static final Logger logger = LogManager.getLogger(HashGenerator.class);

    /**
     * Because of founded vulnerabilities since 2010, the SHA-1 algorithm
     * is not recommended for data encryption.
     * So it's better to use one of the SHA-2 family algorithms.
     * SHA-256 generates 256-bit hash.
     */
    private static final String ALGORITHM = "SHA-256";

    /**
     * <p>This field depends on algorithm and hash-string's radix.</p>
     * <p>For SHA-256 algorithm the size of the generated hash is 256 bits.
     * The hash-string is hexadecimal, so every symbol represents 4 bits.</p>
     * <p>256 / 4 = 64</p>
     * <p>This is where the field value comes from.</p>
     */
    private static final int HASH_STRING_SIZE = 64;
    private static final MessageDigest messageDigest;

    static {
        try {
            messageDigest = MessageDigest.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            String errorString = "Mistyped algorithm in class " + PasswordUtils.class + " : " + ALGORITHM;
            logger.fatal(errorString);
            throw new RuntimeException(errorString);
        }
    }

    public static String getHash(String data) {
        StringBuilder stringHash = new StringBuilder(HASH_STRING_SIZE);
        messageDigest.update(data.getBytes());
        byte[] byteHash = messageDigest.digest();

        for (byte b : byteHash) {
            String byteStr = format("%02x", ((int) b) & 0xff);
            stringHash.append(byteStr);
        }

        logger.debug("Calculated: {}", stringHash);
        return stringHash.toString();
    }
}
