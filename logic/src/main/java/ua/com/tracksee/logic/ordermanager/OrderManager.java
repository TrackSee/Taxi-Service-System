package ua.com.tracksee.logic.ordermanager;


import javax.ejb.Stateless;

import ua.com.tracksee.entity.Address;
import ua.com.tracksee.enumartion.Role;
import ua.com.tracksee.entity.TaxiOrder;
import ua.com.tracksee.entity.User;

/**
 * @author Sasha Avlasov
 * Session Bean implementation class OrderRegistrator
 */
@Stateless
public class OrderManager {

    /**
     * Default constructor. 
     */
    public OrderManager() {
        // TODO Auto-generated constructor stub
    }
	public boolean makeOrder(Address destination,Address origin,long phone){
    	if(!checkPhone(phone)){
    	User user = new User();
    	TaxiOrder order = new TaxiOrder();
    	user.setRole(Role.NOT_REGISTER_USER);
    	user.setPhone(phone);
    	//TODO order.setCustomer(user);
    	order.setOrigin(origin);
    	order.setDestination(destination);
    	if(checkBlackList(phone)){
    		order.incruasePrice();
    	}
    	return true;
    	}
    	else return false;
    }
    private boolean checkPhone(long phone){
    	/**
    	 * check phone in DB if exist 
    	 * return true;
    	 */
    	return false;
    }
    private boolean checkBlackList(long phone){
    	/**
    	 * Check phone in black list 
    	 * if find return true;
    	 * 
    	 */
    	return false;
    }

}
