package ua.com.tracksee.util;

import static java.util.UUID.randomUUID;

/**
 * @author Ruslan Gunavardana
 */
public class IdGenerator {
    public static String generateId() {
        return randomUUID().toString();
    }
}
