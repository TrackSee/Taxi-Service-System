package ua.com.tracksee.entity;

import java.util.ArrayList;

/**
 * @author Sharaban Sasha
 * @see ua.com.tracksee.entity.DataObjectArray
 */
public class DataObjectArrayImpl implements DataObjectArray {
private ArrayList<Object> data=new ArrayList<>();
    private ArrayList<String> typesArray=new ArrayList<>();

    @Override
    public Object get(int index) {
        return data.get(index);
    }
    @Override
    public void add(Integer value) {
        data.add((Object)value);
        typesArray.add("Integer");
    }
    @Override
    public void add(Double value) {
        data.add((Object)value);
        typesArray.add("Double");
    }
    @Override
    public void add(String value) {
        data.add((Object)value);
        typesArray.add("String");
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public String getType(int index) {
        return typesArray.get(index);
    }
}
