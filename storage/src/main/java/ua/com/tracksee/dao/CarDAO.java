package ua.com.tracksee.dao;

import ua.com.tracksee.entities.CarEntity;

/**
 * Created by kstes_000 on 24-Apr-15.
 */
public interface CarDAO {
    void createCar(CarEntity carEntity);
    void updateCar(CarEntity carEntity);
    void deleteCar(CarEntity carEntity);
}
