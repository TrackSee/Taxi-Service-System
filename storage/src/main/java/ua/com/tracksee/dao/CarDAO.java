package ua.com.tracksee.dao;

import ua.com.tracksee.entities.CarEntity;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by kstes_000 on 24-Apr-15.
 */
@Local
public interface CarDAO {
    void createCar(CarEntity carEntity);
    void updateCar(CarEntity carEntity);
    void deleteCar(CarEntity carEntity);
    List<CarEntity> getCars();
}
