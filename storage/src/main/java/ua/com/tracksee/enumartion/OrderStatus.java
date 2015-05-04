package ua.com.tracksee.enumartion;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * @author Igor Gula
 * @author Ruslan Gunavardana
 */
public enum OrderStatus {
    QUEUED("queued"),
    ASSIGNED("assigned"),
    UPDATED("updated"),
    REFUSED("refused"),
    IN_PROGRESS("inProgress"),
    COMPLETED("completed");

    private String name;

    private OrderStatus(String name){
        this.name = name;
    }

    private static final Map<String, OrderStatus> map =
            new HashMap<String, OrderStatus>();

    static {
        for (OrderStatus type : OrderStatus.values()) {
            map.put(type.name, type);
        }
    }

    public String getName() {
        return name;
    }

    public static OrderStatus fromString(String name) {
        if (map.containsKey(name)) {
            return map.get(name);
        }
        throw new NoSuchElementException(name + "not found");
    }
}
