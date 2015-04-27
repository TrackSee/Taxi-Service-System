package ua.com.tracksee.dao.postrgresql;

import ua.com.tracksee.dao.TaxiPriceDAO;
import ua.com.tracksee.entities.TaxiPriceEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by kstes_000 on 23-Apr-15.
 */
@Stateless
public class TaxiPriceDAOBean implements TaxiPriceDAO {
    @PersistenceContext(unitName = "HibernatePU")
    private EntityManager entityManager;

    @Override
    public void updateTariff(TaxiPriceEntity priceEntity) {
        entityManager.refresh(priceEntity);
    }
}
