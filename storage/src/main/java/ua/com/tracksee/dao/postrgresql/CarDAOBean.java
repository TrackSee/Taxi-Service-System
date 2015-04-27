package ua.com.tracksee.dao.postrgresql;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.dao.CarDAO;
import ua.com.tracksee.dao.postrgresql.exceptions.ServiceUserNotFoundException;
import ua.com.tracksee.entities.CarEntity;
import ua.com.tracksee.entities.ServiceUserEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;

/**
 * @author KatiaStetsiuk
 */
@Stateless
public class CarDAOBean implements CarDAO {

    private static final Logger logger = LogManager.getLogger();
    int CARS_PAGE_SIZE =5;

    @PersistenceContext(unitName = "HibernatePU")
    private EntityManager entityManager;


    @Override
    public void createCar(CarEntity carEntity) {
        String sql = "INSERT INTO car (car_model, color, car_category, " +
                "animal_transportation_applicable, free_wifi, air_conditioner " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, carEntity.getCarModel());
        query.setParameter(2, carEntity.getCarCategory());
        query.setParameter(3, carEntity.getAnimalTransportationApplicable());
        query.setParameter(4, carEntity.getFreeWifi());
        query.setParameter(5, carEntity.getAirConditioner());
        query.executeUpdate();
    }

    @Override
    public void updateCar(CarEntity carEntity) {
        String sql = "UPDATE car SET car_model = ?, car_category = ? ,animal_transportation = ?, " +
                "free_wifi = ? , air_conditioner = ?";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, carEntity.getCarCategory());
        query.setParameter(2, carEntity.getCarCategory());
        query.setParameter(3, carEntity.getAnimalTransportationApplicable());
        query.setParameter(4, carEntity.getFreeWifi());
        query.setParameter(5, carEntity.getAirConditioner());
        query.executeUpdate();
    }

    @Override
    public void deleteCar(CarEntity carEntity) {
        String sql = "DELETE from car WHERE car_id = " + carEntity.getCarNumber();
        Query query = entityManager.createNativeQuery(sql);
        query.executeUpdate();
    }

    @Override
    public List<CarEntity> getCars() {
        Query query = entityManager.createNativeQuery("SELECT * FROM car "
                , CarEntity.class);
        return query.getResultList();
    }

    public List<CarEntity> getCarsPart(int partNumber) {

        Query query = entityManager.createNativeQuery("SELECT * FROM car " +
                "LIMIT ?1 OFFSET ?2", CarEntity.class);
        query.setParameter(1, CARS_PAGE_SIZE);
        query.setParameter(2, (partNumber - 1) * CARS_PAGE_SIZE);
        return query.getResultList();
    }

    public int getCarPagesCount() {
        Query q = entityManager.createNativeQuery("SELECT COUNT(*) FROM car");
        Integer carsCount = ((BigInteger) q.getSingleResult()).intValue();
        return (int) (Math.ceil((double) carsCount / CARS_PAGE_SIZE));
    }
    @Override
    public CarEntity getCarByID(int id) {
        if(id <= 0){
            logger.warn("Car id can't be <= 0!");
            throw new IllegalArgumentException("Car id can't be <= 0!");
        }
        CarEntity car = entityManager.find(CarEntity.class, id);
        if(car == null){
            logger.warn("There is no car with such id");
            throw new ServiceUserNotFoundException("There is no car with such id");
        }
        return car;
    }

    //TODO make test for all methods in EJB CarDAOBean
    @Override
    public List<CarEntity> getAllFreeCars() {
        Query q = entityManager.createNativeQuery("SELECT * FROM car where car.car_number NOT IN " +
                "(SELECT service_user.car_number FROM service_user WHERE service_user.car_number " +
                "IS NOT NULL)", CarEntity.class);
        return q.getResultList();
   }

}
