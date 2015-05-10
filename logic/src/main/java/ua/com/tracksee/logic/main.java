package ua.com.tracksee.logic;

import ua.com.tracksee.enumartion.CarCategory;

import javax.validation.constraints.Null;

/**
 * Created by Katakuna on 10.05.2015.
 */
public class main {
    public static void main(String[] args) {
        String s=get(CarCategory.USER_CAR);
        System.out.println(s);
    }
    public static String get(CarCategory enumCarCategory){
        String carCategory=null;
     //   carCategory=enumCarCategory.toString();
        return carCategory;
    }
}
