package ua.com.tracksee.enumartion;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * @author Igor Gula
 * @author Ruslan Gunavardana
 */
public enum Sex {
    MALE("male"),
    FEMALE("famale");

    private String name;

    private Sex(String name){
        this.name = name;
    }

    private static final Map<String, Sex> map =
            new HashMap<String, Sex>();

    static {
        for (Sex type : Sex.values()) {
            map.put(type.name, type);
        }
    }

    public String getName() {
        return name;
    }

    public static Sex fromString(String name) {
        if (map.containsKey(name)) {
            return map.get(name);
        }
        throw new NoSuchElementException(name + "not found");
    }
}
