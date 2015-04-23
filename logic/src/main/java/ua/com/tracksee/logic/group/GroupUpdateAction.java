package ua.com.tracksee.logic.group;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Created by Igor Gula on 19.04.2015.
 */
public enum GroupUpdateAction {
    ADD_GROUP("addGroup"),
    ADD_USERS_TO_GROUP("addUsersToGroup"),
    REMOVE_GROUP("removeGroup"),
    REMOVE_USERS_FROM_GROUP("removeUsersFromGroup"),
    UPDATE_GROUP("updateGroup");

    private final String name;

    private static final Map<String, GroupUpdateAction> map =
            new HashMap<String, GroupUpdateAction>();

    static {
        for (GroupUpdateAction type : GroupUpdateAction.values()) {
            map.put(type.name, type);
        }
    }

    private GroupUpdateAction(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static GroupUpdateAction fromString(String name) {
        if (map.containsKey(name)) {
            return map.get(name);
        }
        throw new NoSuchElementException(name + "not found");
    }
}
