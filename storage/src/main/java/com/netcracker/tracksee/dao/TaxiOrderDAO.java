package com.netcracker.tracksee.dao;

import com.netcracker.tracksee.entities.TaxiOrderEntity;

import javax.ejb.Local;

/**
* @author Sharaban Sasha
*/
@Local
public interface TaxiOrderDAO {
    /**
     *
     * @return list, containing the part of drivers(default size of list is 10)
     */

    void addTaxiOrder(TaxiOrderEntity taxiOrderEntity);

}
