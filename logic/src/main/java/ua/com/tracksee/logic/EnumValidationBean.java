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
            case "businessClass":
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
            case "conveyCorporationEmployees":
                enumService = Service.CONVEY_CORPORATION_EMPLOYEES;
                break;
            case "taxiForLongTerm":
                enumService = Service.TAXI_FOR_LONG_TERM;
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
    public String getFromEnumMusicStyle(MusicStyle enumMusicStyle){
        String musicStyle=null;
        if(enumMusicStyle== MusicStyle.BLUES){
            musicStyle="blues";
        }
        if(enumMusicStyle== MusicStyle.CLASSICAL_MUSIC){
            musicStyle="classicalMusic";
        }
        if(enumMusicStyle== MusicStyle.DANCE_MUSIC){
            musicStyle="danceMusic";
        }
        if(enumMusicStyle== MusicStyle.DEFAULT){
            musicStyle="default";
        }
        if(enumMusicStyle== MusicStyle.ELECTRONIC_MUSIC){
            musicStyle="electronicMusic";
        }
        if(enumMusicStyle== MusicStyle.HIP_HOP){
            musicStyle="hiHop";
        }
        if(enumMusicStyle== MusicStyle.JAZZ){
            musicStyle="jazz";
        }
        if(enumMusicStyle== MusicStyle.ROCK){
            musicStyle="rock";
        }
        return musicStyle;
    }
    public String getFromEnumService(Service enumService){
        String service=null;
        if (enumService == Service.CARGO_TAXI) {
            service = "cargoTaxi";
        }
        if (enumService == Service.CELEBRATION_TAXI) {
            service = "celebrationTaxi";
        }
        if (enumService == Service.CONVEY_CORPORATION_EMPLOYEES) {
            service = "conveyCorporationEmployees";
        }
        if (enumService == Service.FOODSTUFF_DELIVERY) {
            service = "foodStuffDelivery";
        }
        if (enumService == Service.GUEST_DELIVERY) {
            service = "guestDelivery";
        }
        if (enumService == Service.MEET_MY_GUEST) {
            service = "meetMyGuest";
        }
        if (enumService == Service.SIMPLE_TAXI) {
            service = "simpleTaxi";
        }
        if (enumService == Service.SOBER_DRIVER) {
            service = "soberDriver";
        }
        if (enumService == Service.TAXI_FOR_LONG_TERM) {
            service = "taxiForLongTerm";
        }
        return service;
    }
    public String getFromEnumCarCategory(CarCategory enumCarCategory){
        String carCategory=null;
        if (enumCarCategory == CarCategory.BUSINESS_CLASS) {
            carCategory = "businessClass";
        }
        if (enumCarCategory == CarCategory.ECONOMY_CLASS) {
            carCategory = "economyClass";
        }
        if (enumCarCategory == CarCategory.VAN) {
            carCategory = "van";}
    return carCategory;
    }
    public String getFromEnumDriverSex(Sex enumDriverSex){
        String driverSex=null;
        if (enumDriverSex == Sex.A) {
            driverSex = "anyone";
        }
        if (enumDriverSex== Sex.M) {
            driverSex = "male";
        }
        if (enumDriverSex == Sex.F) {
            driverSex = "female";
        }
        return driverSex;
    }
    public String getFromEnumWayOfPayment(WayOfPayment enumWayOfPayment){
        String wayOfPayment=null;
        if (enumWayOfPayment == WayOfPayment.CASH) {
            wayOfPayment = "cash";
        }
        if (enumWayOfPayment == WayOfPayment.VISA_CARD) {
            wayOfPayment = "visaCard";
        }
        return wayOfPayment;
    }
}
