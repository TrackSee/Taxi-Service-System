package com.netcracker.tracksee.dao;

import com.netcracker.tracksee.entities.Address;

import javax.ejb.Local;

/**
* @author Sharaban Sasha
*/
@Local
public interface AddressDAO {
    /**
     *
     *
     */

    void addAddress(Address address);

}
