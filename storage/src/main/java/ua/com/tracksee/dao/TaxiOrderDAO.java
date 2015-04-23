package ua.com.tracksee.dao;

import ua.com.tracksee.entities.TaxiOrderEntity;

/**
 * Created by kstes_000 on 23-Apr-15.
 */
public interface TaxiOrderDAO {
    public void addComment(TaxiOrderEntity taxiOrderEntity, String comment);
}
