package ua.com.tracksee.dao;

import ua.com.tracksee.entities.CarEntity;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by kstes_000 on 24-Apr-15.
 */
@Local
public interface CarDAO {

    int CARS_PAGE_SIZE =5;

    void createCar(CarEntity carEntity);
    void updateCar(CarEntity carEntity);
    void deleteCar(String carNumber);
    List<CarEntity> getCars();
    public int getCarPagesCount();
    List<CarEntity> getCarsPart(int partNumber);
    CarEntity getCarByNumber(String carNumber);

    /**
     *@author Vadym Akymov, Katia Stetsiuk
     * @return list of all free cars
     */
    List<CarEntity> getAllFreeCars();
}