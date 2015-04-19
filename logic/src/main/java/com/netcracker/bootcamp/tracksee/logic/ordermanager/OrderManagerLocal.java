package com.netcracker.bootcamp.tracksee.logic.ordermanager;

import com.netcracker.bootcamp.tracksee.entity.Address;

import javax.ejb.Local;

/**
 * 
 * @author Sasha Avlasov
 * 
 */

@Local
public interface OrderManagerLocal {
	public boolean makeOrder(Address destination, Address origin, long phone);
}