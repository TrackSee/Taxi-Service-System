package ua.com.tracksee.entity;

/**
 * @author Sharaban Sasha
 */
public interface DataObjectArray {
    Object get(int index);
    String getType(int index);
    void add(Integer value);
    void add(String value);
    void add(Long value);
    int size();

}
