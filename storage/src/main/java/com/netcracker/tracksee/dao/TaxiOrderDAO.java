package com.netcracker.tracksee.dao;

import com.netcracker.tracksee.entities.TaxiOrderEntity;

import javax.ejb.Local;

/**
 * This class work with taxi_order table,
 * now it's insert data into table and
 * return tracking number.
 *
* @author Sharaban Sasha
*/
@Local
public interface TaxiOrderDAO {
    /**
     * This method add order to database
     * into table taxi_order.
     *
     * @param taxiOrder This is order entity which will be insert into database
     * @return int This is  order tracking number which will be returned after adding data.
     *
     */

    int addTaxiOrder(TaxiOrderEntity taxiOrder);

}
