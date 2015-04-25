package ua.com.tracksee;

import com.netcracker.tracksee.enumartion.CarCategory;
import ua.com.tracksee.logic.exception.EnumException;

/**
 * Created by Katakuna on 25.04.2015.
 */
public class main {
    public static void main(String[] args) {
        CarCategory carCategory=null;
        try {
            carCategory= setEnumCarCategory("van");

                if(carCategory!=null){
                    System.out.println("carCategory!=null");
                }
            System.out.println("carCategory:  "+carCategory);
        } catch (EnumException e) {
            e.printStackTrace();
        }
        if(carCategory==null){
            System.out.println("carCategory==null");
        }

//        boolean state=setBoolean();
//        System.out.println("boolean state:"+ state);


    }

    private static boolean setBoolean(String stringValue){
        boolean state;
        state=Boolean.parseBoolean(stringValue);
        return state;
    }
    private static CarCategory setEnumCarCategory(String carCategory) throws EnumException {
        CarCategory enumCarCategory;
        switch (carCategory){
            case "business":
                enumCarCategory=CarCategory.BUSINESS_CLASS;
                break;
            case "economyClass":
                enumCarCategory=CarCategory.ECONOMY_CLASS;
                break;
            case "van":
                enumCarCategory=CarCategory.VAN;
                break;
            default:
                throw new EnumException("Invalid carCategory enum value","invalid-carCategory");
        }
        return enumCarCategory;
    }
}
