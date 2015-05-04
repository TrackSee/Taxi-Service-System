package ua.com.tracksee.dao.postrgresql;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.dao.CarDAO;
import ua.com.tracksee.dao.postrgresql.exceptions.CarNotFoundException;
import ua.com.tracksee.entities.CarEntity;
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
    @PersistenceContext(unitName = "HibernatePU")
    private EntityManager entityManager;


    @Override
    //TODO add car_category when db finished
    public void createCar(CarEntity carEntity) {
        String sql = "INSERT INTO car (car_number, car_model , color,  car_category, animal_transportation_applicable ," +
                " free_wifi, air_conditioner)" +
//
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, carEntity.getCarNumber());
        query.setParameter(2, carEntity.getCarModel());
        query.setParameter(3, carEntity.getColor());
        query.setParameter(4, carEntity.getCarCategory().toString());
        query.setParameter(5, carEntity.getAnimalTransportationApplicable());
        query.setParameter(6, carEntity.getFreeWifi());
        query.setParameter(7, carEntity.getAirConditioner());
        query.executeUpdate();

    }

    @Override
    public void updateCar(CarEntity carEntity) {
        String sql = "UPDATE car SET car_model = ?, color = ?, car_category = ? ,animal_transportation_applicable = ?, " +
                "free_wifi = ? , air_conditioner = ? " +
                " WHERE car_number = ?" ;
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, carEntity.getCarModel());
        query.setParameter(2, carEntity.getColor());
        query.setParameter(3, carEntity.getCarCategory().toString());
        query.setParameter(4, carEntity.getAnimalTransportationApplicable());
        query.setParameter(5, carEntity.getFreeWifi());
        query.setParameter(6, carEntity.getAirConditioner());
        query.setParameter(7, carEntity.getCarNumber());

        query.executeUpdate();
    }

    @Override
    public void deleteCar(String carNumber) {
        if(carNumber == null){
            logger.warn("carNumber can't be Null");
            throw new IllegalArgumentException("carId can't be <= 0");
        }
        String sql = "DELETE from car " +
                "where car_number = ?1";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, carNumber);
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
    public CarEntity getCarByNumber(String carNumber) {
        if(carNumber == null){
            logger.warn("carNumber can't be null!");
            throw new IllegalArgumentException("carNumber can't be null!");
        }
        CarEntity car = entityManager.find(CarEntity.class, carNumber);
        if(car == null){
            logger.warn("There is no car with such id");
            throw new CarNotFoundException("There is no car with such id");
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
