package ua.com.tracksee.entity;

import java.util.ArrayList;

/**
 * @author Sharaban Sasha
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
    public void add(Long value) {
        data.add((Object)value);
        typesArray.add("Long");
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
