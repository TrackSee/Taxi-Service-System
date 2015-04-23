package ua.com.tracksee.dao.postrgresql;

import ua.com.tracksee.dao.TaxiOrderDAO;
import ua.com.tracksee.entities.TaxiOrderEntity;

/**
 * Created by kstes_000 on 23-Apr-15.
 */
public class TaxiOrderDAOBean implements TaxiOrderDAO {
    @Override
    public void addComment(TaxiOrderEntity entity, String comment) {
        entity.setComment(comment);

    }
}
