package logic;

import javax.ejb.Local;

import entity.Address;
/**
 * 
 * @author Sasha Avlasov
 * 
 */
@Local
public interface OrderManegerLocal {
	public boolean makeOrder(Address destination,Address origin,long phone);
}
