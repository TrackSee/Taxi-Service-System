package com.netcracker.tracksee.dao;

import com.netcracker.tracksee.entities.TaxiOrder;
import com.netcracker.tracksee.entities.TaxiOrderEntity;

import javax.ejb.Local;

/**
* @author Sharaban Sasha
*/
@Local
public interface TaxiOrderDAO {
    /**
     *
     *
     */

    void addTaxiOrder(TaxiOrder taxiOrder);

}
