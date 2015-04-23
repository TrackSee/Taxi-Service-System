package ua.com.tracksee.logic.group;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Created by Igor Gula on 19.04.2015.
 */
public enum GroupAction {
    ADD_GROUP("addGroup"),
    ADD_USERS_TO_GROUP("addUsersToGroup"),
    REMOVE_GROUP("removeGroup"),
    REMOVE_USERS_FROM_GROUP("removeUsersFromGroup"),
    UPDATE_GROUP("updateGroup");

    private final String name;

    private static final Map<String, GroupAction> map =
            new HashMap<String, GroupAction>();

    static {
        for (GroupAction type : GroupAction.values()) {
            map.put(type.name, type);
        }
    }

    private GroupAction(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static GroupAction fromString(String name) {
        if (map.containsKey(name)) {
            return map.get(name);
        }
        throw new NoSuchElementException(name + "not found");
    }
}
