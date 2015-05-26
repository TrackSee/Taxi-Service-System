package ua.com.tracksee.entity;

/**
 * Interface used for storage data
 * of different types, setting three
 * types of data and getting them
 * from object array to their base types.
 *
 * @author Sharaban Sasha
 */
public interface DataObjectArray {
    Object get(int index);
    String getType(int index);
    void add(Integer value);
    void add(String value);
    void add(Double value);
    int size();

}
