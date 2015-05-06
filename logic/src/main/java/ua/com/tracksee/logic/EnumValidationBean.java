package ua.com.tracksee.logic;

import ua.com.tracksee.enumartion.*;

import javax.ejb.Stateless;

/**
 * @author Sharaban Sasha
 */
//TODO no sense in convertation workaround, while we have Enum.valueOf()
@Stateless
public class EnumValidationBean {

    /**
     * This method checks there is the such
     * enum  and return it if it exists.
     *
     * @param carCategory - string representation of car category
     * @return car category enum
     * @author Sharaban Sasha
     */

    public CarCategory setEnumCarCategory(String carCategory) {
        CarCategory enumCarCategory;
        switch (carCategory) {
            case "business":
                enumCarCategory = CarCategory.BUSINESS_CLASS;
                break;
            case "economyClass":
                enumCarCategory = CarCategory.ECONOMY_CLASS;
                break;
            case "van":
                enumCarCategory = CarCategory.VAN;
                break;
            default:
                enumCarCategory = null;
        }
        return enumCarCategory;
    }
    /**
     * This method checks there is the such
     * enum and return it if it exists.
     *
     * @param wayOfPayment - string representation of way of payment
     * @return way of payment enum
     * @author Sharaban Sasha
     */
    public WayOfPayment setEnumWayOfPayment(String wayOfPayment) {
        WayOfPayment enumWayOfPayment;
        switch (wayOfPayment) {
            case "cash":
                enumWayOfPayment = WayOfPayment.CASH;
                break;
            case "visaCard":
                enumWayOfPayment = WayOfPayment.VISA_CARD;
                break;
            default:
                enumWayOfPayment = null;
        }
        return enumWayOfPayment;
    }
    /**
     * This method checks there is the such
     * enum and return it if it exists.
     *
     * @param driverSex - string representation of driver sex
     * @return driver sex enum
     * @author Sharaban Sasha
     */
    public Sex setEnumDriverSex(String driverSex) {
        Sex enumDriverSex;
        switch (driverSex) {
            case "male":
                enumDriverSex = Sex.M;
                break;
            case "female":
                enumDriverSex = Sex.F;
                break;
            case "anyone":
                enumDriverSex = Sex.A;
                break;
            default:
                enumDriverSex = null;
        }
        return enumDriverSex;
    }
    /**
     * This method checks there is the such
     * enum and return it if it exists.
     *
     * @param service - string representation of service
     * @return service enum
     * @author Sharaban Sasha
     */

    public Service setEnumService(String service) {
        Service enumService;
        switch (service) {
            case "default":
                enumService = Service.SIMPLE_TAXI;
                break;
            case "soberDriver":
                enumService = Service.SOBER_DRIVER;
                break;
            case "guestDelivery":
                enumService = Service.GUEST_DELIVERY;
                break;
            case "cargoTaxi":
                enumService = Service.CARGO_TAXI;
                break;
            case "meetMyGuest":
                enumService = Service.MEET_MY_GUEST;
                break;
            case "celebrationTaxi":
                enumService = Service.CELEBRATION_TAXI;
                break;
            case "foodStuffDelivery":
                enumService = Service.FOODSTUFF_DELIVERY;
                break;
            default:
                enumService = null;

        }
        return enumService;
    }

    /**
     * This method checks there is the such
     * enum and return it if it exists.
     *
     * @param musicStyle - string representation of music style
     * @return music style enum
     * @author Sharaban Sasha
     */

    public MusicStyle setEnumMusicStyle(String musicStyle) {
        MusicStyle enumMusicStyle;
        switch (musicStyle) {
            case "default":
                enumMusicStyle = MusicStyle.DEFAULT;
                break;
            case "blues":
                enumMusicStyle = MusicStyle.BLUES;
                break;
            case "classicalMusic":
                enumMusicStyle = MusicStyle.CLASSICAL_MUSIC;
                break;
            case "rock":
                enumMusicStyle = MusicStyle.ROCK;
                break;
            case "jazz":
                enumMusicStyle = MusicStyle.JAZZ;
                break;
            case "danceMusic":
                enumMusicStyle = MusicStyle.DANCE_MUSIC;
                break;
            case "electronicMusic":
                enumMusicStyle = MusicStyle.ELECTRONIC_MUSIC;
                break;
            case "hipHop":
                enumMusicStyle = MusicStyle.HIP_HOP;
                break;
            default:
                enumMusicStyle = null;
        }
        return enumMusicStyle;
    }
}
