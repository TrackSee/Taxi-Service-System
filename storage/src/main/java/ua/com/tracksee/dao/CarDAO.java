package ua.com.tracksee.dao;

import ua.com.tracksee.entities.CarEntity;

import javax.ejb.Local;
import java.util.List;

/**
 * @author Katia Stetsiuk
 */
@Local
public interface CarDAO {
    //5 cars per query by default
    int CARS_PAGE_SIZE = 10;

    void createCar(CarEntity carEntity);

    void updateCar(CarEntity carEntity);

    /**
     * @param carNumber car number for deleting
     * @author Katia Stetsiuk
     */
    void deleteCar(String carNumber);

    /**
     * @return list, containing the all cars(without partition)
     * @author Katia Stetsiuk
     */
    List<CarEntity> getCars();

    /**
     * @return count of car pages in system
     * @author Katia Stetsiuk
     */
    public int getCarPagesCount();

    /**
     * @param partNumber number of data part
     * @return list, containing the part of cars(default size of list is 10)
     * @author Katia Stetsiuk
     */
    List<CarEntity> getCarsPart(int partNumber);

    /**
     * @throws ua.com.tracksee.dao.postrgresql.exceptions.CarNotFoundException when there
     *                                                                         is no car with such carNumber
     * @author Katia Stetsiuk
     */
    CarEntity getCarByNumber(String carNumber);

    /**
     * @return list of all free cars
     * @author Vadym Akymov, Katia Stetsiuk
     */
    List<CarEntity> getAllFreeCars();
}