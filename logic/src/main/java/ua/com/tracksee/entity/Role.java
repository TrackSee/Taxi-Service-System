package ua.com.tracksee.entity;


import java.util.HashMap;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * @author Ruslan Gunavardana
 */
public enum Role {

    ADMINISTRATOR("administrator"),

    DRIVER("driver"),

    CUSTOMER_USER("registered_customer"),

    NOT_REGISTER_USER("unregistered_customer");

    private String name;

    private Role(String name){
        this.name = name;
    }

    private static final Map<String, Role> map =
            new HashMap<String, Role>();

    static {
        for (Role type : Role.values()) {
            map.put(type.name, type);
        }
    }

    public String getName() {
        return name;
    }

    public static Role fromString(String name) {
        if (map.containsKey(name)) {
            return map.get(name);
        }
        throw new NoSuchElementException(name + "not found");
    }
}