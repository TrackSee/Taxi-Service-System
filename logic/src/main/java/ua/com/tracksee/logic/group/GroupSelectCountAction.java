package ua.com.tracksee.logic.group;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Created by Igor on 23.04.2015.
 */
public enum GroupSelectCountAction {

    SELECT_GROUPS_COUNT("selectGroups"),
    SELECT_USERS_COUNT("selectUsers");

    private final String name;

    private static final Map<String, GroupSelectCountAction> map =
            new HashMap<String, GroupSelectCountAction>();

    static {
        for (GroupSelectCountAction type : GroupSelectCountAction.values()) {
            map.put(type.name, type);
        }
    }

    private GroupSelectCountAction(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static GroupSelectCountAction fromString(String name) {
        if (map.containsKey(name)) {
            return map.get(name);
        }
        throw new NoSuchElementException(name + "not found");
    }
}

