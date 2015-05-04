package ua.com.tracksee.logic.group;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Created by Igor on 23.04.2015.
 */

public enum GroupSelectAction {

    SELECT_GROUPS("selectGroups"),
    SELECT_USERS("selectUsers");

    private final String name;

    private static final Map<String, GroupSelectAction> map =
            new HashMap<String, GroupSelectAction>();

    static {
        for (GroupSelectAction type : GroupSelectAction.values()) {
            map.put(type.name, type);
        }
    }

    private GroupSelectAction(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static GroupSelectAction fromString(String name) {
        if (map.containsKey(name)) {
            return map.get(name);
        }
        throw new NoSuchElementException(name + "not found");
    }
}
